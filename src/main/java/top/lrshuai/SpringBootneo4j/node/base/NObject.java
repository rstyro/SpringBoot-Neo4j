package top.lrshuai.SpringBootneo4j.node.base;

import java.util.HashMap;
import java.util.Map;

public class NObject {

	/**
	 * 节点标签名称 == Node Labels
	 */
	private String label;
	
	/**
	 * 节点属性键值对 == Property Keys
	 */
    private Map<String, Object> properties;
    
    
    public NObject(){
    	properties = new HashMap<>();
    }

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	public Map<String, Object> getProperties() {
		return properties;
	}
	
	/**
	 * 添加属性
	 * @param key
	 * @param value
	 */
	public void addProperty(String key,Object value){
		properties.put(key, value);
	}
	
	/**
	 * 移除属性
	 * @param key
	 */
	public void removeProperty(String key){
		properties.remove(key);
	}
	
	/**
	 * 获取属性
	 * @param key
	 * @return
	 */
	public Object getProperty(String key){
		return properties.get(key);
	}
	
	
}
