package top.lrshuai.SpringBootneo4j.node.relation;

import top.lrshuai.SpringBootneo4j.node.PeopleNode;
import top.lrshuai.SpringBootneo4j.node.base.NObject;
/**
 * 关系节点
 * @author rstyro
 *
 */
public class NRelation extends NObject{
	private String rID;
	private PeopleNode people;
	
	public String getrID() {
		return rID;
	}
	public void setrID(String rID) {
		this.rID = rID;
	}
	public PeopleNode getPeople() {
		return people;
	}
	public void setPeople(PeopleNode people) {
		this.people = people;
	}
	@Override
	public String toString() {
		return "NRelation [rID=" + rID
				+ ", people=" + people +",properties="+this.getProperties()+ "]";
	}
	
	
}
