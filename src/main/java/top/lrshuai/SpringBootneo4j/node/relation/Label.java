package top.lrshuai.SpringBootneo4j.node.relation;

public enum Label {
	OTHER_FRIEND("朋友"),
	OTHER_GAY_FRIEND("基友"),
	OTHER_GIRL_FRIEND("女朋友"),
	OTHER_BOY_FRIEND("男朋友"),
	OTHER_LOVERS("情人"),
	OTHER_ENEMY("敌人"),
	OTHER_CONFIDANT("知己"),
	OTHER_BESTIE("闺蜜"),
	OTHER_MISTRESS("小三"),
	OTHER_LANDLORD("房东"),
	
	SCHOOL_MASTER("校长"),
	SCHOOL_TEACHER("老师"),
	SCHOOL_CLASSMATE("同学"),
	
	HOME_GRANDFATHER("爷爷"),
	HOME_GRANDMOTHER("奶奶"),
	HOME_FATHER("父亲"),
	HOME_MOTHER("母亲"),
	HOME_SISTERS("姐妹"),
	HOME_BROTHERS("兄弟"),
	HOME_UNCLE("叔叔"),
	HOME_AUNT("阿姨"),
	HOME_WIFE("妻子"),
	HOME_HUSBAND("丈夫"),
	
	WORK_COLLEAGUE("同事"),
	WORK_BOSS("上司"),
	WORK_UNDERLING("下属"),
	WORK_BUSINESS_PARTNER("生意伙伴"),
	WORK_COMPETITOR("竞争对手"),
	;
	
	private String name;
	
	private Label(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
}
