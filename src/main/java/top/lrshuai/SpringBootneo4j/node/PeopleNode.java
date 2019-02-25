package top.lrshuai.SpringBootneo4j.node;

import top.lrshuai.SpringBootneo4j.node.base.NObject;
import top.lrshuai.SpringBootneo4j.node.relation.NRelation;

public class PeopleNode extends NObject{
	private String id;
	private NRelation relation;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public NRelation getRelation() {
		return relation;
	}
	public void setRelation(NRelation relation) {
		this.relation = relation;
	}
	@Override
	public String toString() {
		return "People [id=" + id + ", relation=" + relation +",properties="+this.getProperties()+ "]";
	}
	
}
