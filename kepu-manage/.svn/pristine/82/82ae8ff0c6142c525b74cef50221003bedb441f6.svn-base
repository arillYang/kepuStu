package com.kepu.pojo;


import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kepu.util.StringUtil;

public class KePuResult {
	private Integer code;
	private String message;
	private Object data;
	 private static final ObjectMapper MAPPER = new ObjectMapper();
	 
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public KePuResult(){
		
	}
	
	public KePuResult(Integer code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public static KePuResult build(Integer status,String msg,Object data){
		if(status!=0)
			data=null;
		/*if(data instanceof String){
			if(StringUtil.isEmpty((String)data))
				data=null;
		}*/
		return new KePuResult(status, msg, data);
	}
	public static KePuResult ok(Integer status,String msg,Object data){
		if(status!=0)
			data=null;
		/*if(data instanceof String){
			if(StringUtil.isEmpty((String)data))
				data=null;
		}*/
		return new KePuResult(status,msg,data);
	}
	
	public static KePuResult formatStaro2Result(String jsonData,Class<?> clazz){
		try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, KePuResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
	}
	
	 /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static KePuResult format(String json) {
        try {
            return MAPPER.readValue(json, KePuResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static KePuResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
