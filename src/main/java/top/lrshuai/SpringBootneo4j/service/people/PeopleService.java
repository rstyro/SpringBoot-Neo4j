package top.lrshuai.SpringBootneo4j.service.people;

import java.util.List;

import top.lrshuai.SpringBootneo4j.node.PeopleNode;
import top.lrshuai.SpringBootneo4j.node.relation.NRelation;
import top.lrshuai.SpringBootneo4j.node.relation.Operate;

public interface PeopleService {
	public int createNode(PeopleNode node) throws Exception;
	public int createRelation(PeopleNode parentNode,PeopleNode childNode) throws Exception;
	public int delNode(PeopleNode node,Operate operate) throws Exception;
	public PeopleNode updateNode(PeopleNode node) throws Exception;
	public NRelation queryRelationById(PeopleNode parentNode,PeopleNode childNode) throws Exception;	
	public PeopleNode queryNodeById(PeopleNode node) throws Exception;
	public List<PeopleNode> queryNode(PeopleNode node) throws Exception;
	public List<PeopleNode> queryNodeAndRelation(NRelation relation) throws Exception;	
}
