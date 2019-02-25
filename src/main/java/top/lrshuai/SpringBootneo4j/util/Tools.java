package top.lrshuai.SpringBootneo4j.util;

public class Tools {
	private static SnowflakeIdWorker snow = new SnowflakeIdWorker(1, 1);
	
	public static String getId(){
		return snow.nextId()+"";
	}
}
