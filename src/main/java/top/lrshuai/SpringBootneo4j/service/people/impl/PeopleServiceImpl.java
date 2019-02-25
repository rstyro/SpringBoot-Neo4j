package top.lrshuai.SpringBootneo4j.service.people.impl;

import java.util.ArrayList;
import java.util.List;




import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Entity;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.lrshuai.SpringBootneo4j.node.PeopleNode;
import top.lrshuai.SpringBootneo4j.node.relation.NRelation;
import top.lrshuai.SpringBootneo4j.node.relation.Operate;
import top.lrshuai.SpringBootneo4j.service.base.BaseService;
import top.lrshuai.SpringBootneo4j.service.people.PeopleService;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PeopleServiceImpl extends BaseService implements PeopleService{

	@Autowired
	private Session session;
	
	private ObjectMapper mapper= getObjectMapper();
	
	@Override
	public int createNode(PeopleNode node) throws Exception {
		PeopleNode repeat = queryNodeById(node);
		if(repeat != null){
			logger.info("this node is exist");
			return 0;
		}
		String propertiesString = mapper.writeValueAsString(node.getProperties());
		String cypherSql = String.format("create (:%s%s)", node.getLabel(), propertiesString);
		logger.info("createNode sql={}",cypherSql);
		session.run(cypherSql);
		return 1;
	}

	@Override
	public int createRelation(PeopleNode parentNode, PeopleNode childNode) throws Exception{
		NRelation relation = queryRelationById(parentNode, childNode);
		if(relation != null){
			logger.info("this relation is exist");
			return 0;
		}
		relation = parentNode.getRelation();
		String propertiesJsonString = mapper.writeValueAsString(parentNode.getRelation().getProperties());
		String cypherSql = String.format("match(a),(b) where a.id='%s' and b.id='%s' create (a)-[r:%s %s]->(b)",childNode.getProperty("id"),parentNode.getProperty("id"),relation.getLabel(), propertiesJsonString);
		logger.info("create createRelation sql={}",cypherSql);
		session.run(cypherSql);
		return 1;
	}

	@Override
	public int delNode(PeopleNode node,Operate operate) throws Exception{
		String sql = "match (n:%s %s)-[r]-(m:%s)";
		if(Operate.DEL_NODE.equals(operate)){//删除该节点
			sql = "match (n:%s %s) delete n";
		}else if(Operate.DEL_RELATION.equals(operate)){//删除该节点所有关系链
			sql = sql+"delete r";
		}else if(Operate.DEL_NODE_AND_RELATION.equals(operate)){//删除所有关系链,和其本身节点
			sql = sql+"delete r,n";
		}else if(Operate.DEL_CORRELATION_ALL_NODE.equals(operate)){//删除所有关系链与本身节点和相关联的节点
			sql = sql+"delete r,n,m";
		}
		String jsonString = mapper.writeValueAsString(node.getProperties());
		String cypherSql = String.format(sql, node.getLabel(),jsonString,node.getLabel());
		logger.info("create delNode sql={}",cypherSql);
		session.run(cypherSql);
		return 1;
	}

	@Override
	public PeopleNode updateNode(PeopleNode node) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NRelation queryRelationById(PeopleNode parentNode, PeopleNode childNode) throws Exception{
		NRelation relation = parentNode.getRelation();
		String cypherSql =String.format("match(n)-[r:%s]-(b) where n.id = '%s' and b.id = '%s' return r", 
				relation.getLabel(),parentNode.getProperty("id"),childNode.getProperty("id"));
		logger.info("queryRelation sql={}",cypherSql);
		StatementResult result = session.run(cypherSql);
		NRelation resultRelation = null;
		if(result.hasNext()){
			Record record = result.next();
			for (Value value : record.values()) {
				if(value.type().name().equals("RELATIONSHIP")){
					resultRelation=new NRelation();
					Relationship relationship= value.asRelationship();
					resultRelation.setLabel(relationship.type());
					resultRelation.setProperties(relationship.asMap());
				}
			}
		}
		return resultRelation;
	}
	
	@Override
	public List<PeopleNode> queryNodeAndRelation(NRelation relation) throws Exception {
		String cypherSql = String.format("MATCH p=()-[r:%s%s]->() RETURN p,r", relation.getLabel(), "");
		logger.info("queryNodeById sql={}",cypherSql);
		StatementResult result = session.run(cypherSql);
		System.out.println("result=="+result);
		List<PeopleNode> resultNode = new ArrayList<PeopleNode>();
		while (result.hasNext()) {
			Record record = result.next();
			for (Value value : record.values()) {
				System.out.println("value="+value);
				if (value.type().name().equals("NODE")) {
					 Node neo4jNode = value.asNode();
					 System.out.println("===============neo4jNode="+neo4jNode.labels().spliterator());
					 PeopleNode itemNode = new PeopleNode();
					 itemNode.setId(neo4jNode.id()+"");
					 itemNode.setProperties(neo4jNode.asMap());
					 resultNode.add(itemNode);
				}else if(value.type().name().equals("RELATIONSHIP")){
					Relationship relationship= value.asRelationship();
					System.out.println("=================relationship="+relationship);
				}else if(value.type().name().equals("PATH")){
					Path path = value.asPath();
					path.nodes();
					System.out.println("=====================path="+path);
				}
			}
		}
		return resultNode;
	}

	//通过属性ID 查询节点
	@Override
	public PeopleNode queryNodeById(PeopleNode node) throws Exception{
		System.out.println("node="+node);
		String cypherSql = String.format("match(n:%s) where n.id='%s'  return n", node.getLabel(), node.getProperty("id"));
		logger.info("queryNodeById sql={}",cypherSql);
		StatementResult result = session.run(cypherSql);
		PeopleNode resultNode = null;
		if (result.hasNext()) {
			Record record = result.next();
			for (Value value : record.values()) {
				if (value.type().name().equals("NODE")) {
					 Node neo4jNode = value.asNode();
					 resultNode = new PeopleNode();
					 resultNode.setId(neo4jNode.id()+"");
					 resultNode.setLabel(node.getLabel());
					 resultNode.setProperties(neo4jNode.asMap());
				}
			}
		}
		return resultNode;
	}

	@Override
	public List<PeopleNode> queryNode(PeopleNode node) throws Exception{
		String cypherSql = String.format("match(n:%s%s) return n", node.getLabel(), mapper.writeValueAsString(node.getProperties()));
		logger.info("queryNode sql={}",cypherSql);
		StatementResult result = session.run(cypherSql);
		List<PeopleNode> resultNode = new ArrayList<PeopleNode>();
		while (result.hasNext()) {
			Record record = result.next();
			for (Value value : record.values()) {
				if (value.type().name().equals("NODE")) {
					 Node neo4jNode = value.asNode();
					 System.out.println("neo4jNode="+neo4jNode.labels().spliterator());
					 PeopleNode itemNode = new PeopleNode();
					 itemNode.setId(neo4jNode.id()+"");
					 itemNode.setLabel(node.getLabel());
					 itemNode.setProperties(neo4jNode.asMap());
					 resultNode.add(itemNode);
				}
			}
		}
		return resultNode;
	}



}
