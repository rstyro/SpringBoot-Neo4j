package top.lrshuai.SpringBootneo4j;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import top.lrshuai.SpringBootneo4j.node.PeopleNode;
import top.lrshuai.SpringBootneo4j.node.relation.NRelation;
import top.lrshuai.SpringBootneo4j.node.relation.Operate;
import top.lrshuai.SpringBootneo4j.service.people.PeopleService;
import top.lrshuai.SpringBootneo4j.util.Tools;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootNeo4jApplicationTests {

	@Autowired
	private PeopleService peopleService;
	
	@Test
	public void testCreateNode() throws Exception {
		PeopleNode node = new PeopleNode();
		node.setLabel("Person");
		node.setId("1");
		node.addProperty("id", "500652901287329792");
		node.addProperty("name", "大佬");
		node.addProperty("age", "18");
		node.addProperty("sex", "man");
		peopleService.createNode(node);
	}
	
	@Test
	public void testdelNode() throws Exception {
		PeopleNode node = new PeopleNode();
		node.setLabel("Person");
		node.addProperty("id", "500652901287329792");
		peopleService.delNode(node, Operate.DEL_NODE);
	}
	
	@Test
	public void testCreateNode2() throws Exception {
		PeopleNode parentNode = new PeopleNode();
		parentNode.setLabel("Person");
		parentNode.setId("1");
		parentNode.addProperty("id", Tools.getId());
		parentNode.addProperty("name", "大佬");
		parentNode.addProperty("age", "18");
		parentNode.addProperty("sex", "man");
		
		
		PeopleNode childNode = new PeopleNode();
		childNode.setLabel("Person");
		childNode.setId("2");
		childNode.addProperty("id", Tools.getId());
		childNode.addProperty("name", "二佬");
		childNode.addProperty("age", "18");
		childNode.addProperty("sex", "man");
		
		NRelation relationships = new NRelation();
		relationships.setLabel("兄弟");
		relationships.addProperty("name", "兄弟");
		relationships.addProperty("type", "关系");
		parentNode.setRelation(relationships);
		
		peopleService.createNode(parentNode);
		peopleService.createNode(childNode);
		peopleService.createRelation(parentNode, childNode);
	}
	
	@Test
	public void testQueryByID() throws Exception {
		PeopleNode people = new PeopleNode();
		people.setLabel("Person");
		people.addProperty("id", "500671443156074496");
		PeopleNode result = peopleService.queryNodeById(people);
		System.out.println("result="+result);
	}
	@Test
	public void testQuery() throws Exception {
		PeopleNode people = new PeopleNode();
		people.setLabel("Person");
		people.addProperty("sex", "man");
		List<PeopleNode> list = peopleService.queryNode(people);
		for(PeopleNode pp:list){
			System.out.println("properties="+pp.getProperties()+","+pp.getRelation());
		}
	}
	
	@Test
	public void testRelation() throws Exception {
		PeopleNode parentNode = new PeopleNode();
		parentNode.setLabel("Language");
		parentNode.addProperty("id", "0");
		
		PeopleNode childNode = new PeopleNode();
		childNode.setLabel("Language");
		childNode.addProperty("id", "1");
		
		NRelation relationships = new NRelation();
		relationships.setLabel("升级");
		
		parentNode.setRelation(relationships);
		
		NRelation result = peopleService.queryRelationById(parentNode, childNode);
		System.out.println("result="+result);
	}
	
	@Test
	public void testQueryRelation() throws Exception {
		PeopleNode parentNode = new PeopleNode();
		parentNode.setLabel("Person");
		parentNode.addProperty("id", "502506283639902208");
		
		PeopleNode childNode = new PeopleNode();
		childNode.setLabel("Person");
		childNode.addProperty("id", "502506283639902209");
		
		NRelation relationships = new NRelation();
		relationships.setLabel("兄弟");
		
		parentNode.setRelation(relationships);
		
		NRelation result = peopleService.queryRelationById(parentNode, childNode);
		System.out.println("result="+result);
	}
	
	@Test
	public void testQueryNodeAndRelation() throws Exception {
		NRelation relation = new NRelation();
		relation.setLabel("兄弟");
		peopleService.queryNodeAndRelation(relation);
	}

}
