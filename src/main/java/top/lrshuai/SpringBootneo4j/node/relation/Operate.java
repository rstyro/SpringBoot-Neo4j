package top.lrshuai.SpringBootneo4j.node.relation;

public enum Operate {
	
	DEL_NODE("node"),
	DEL_RELATION("relation"),
	DEL_NODE_AND_RELATION("nodeAndRelation"),
	DEL_CORRELATION_ALL_NODE("all"),
	;
	
	private String type;

	
	public String getType() {
		return type;
	}
	private Operate(String type){
		this.type=type;
	}
}
