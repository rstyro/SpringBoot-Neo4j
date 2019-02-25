package top.lrshuai.SpringBootneo4j.service.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;


public class BaseService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public ObjectMapper getObjectMapper(){
		 ObjectMapper mapper = new ObjectMapper();
		 /**
		  * 下面的设置的作用是，比如对象属性字段name="李二明"，正常反序列化json为 == "name":"rstyro"
		  * 如果使用下面的设置后，反序列name就是 == name:"rstyro"
		  */
		 mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
		 return mapper;
	 }
}
