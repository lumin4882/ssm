package com.ssm.utils;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
	
	@Test
	public void testJson() {
		String jsonStr = "{\"password\":\"123456\",\"username\":\"张三\",\"star_male\": [{ \"nickname\": \"鹿晗\",\"age\": \"26\"}, { \"nickname\": \"李易峰\", \"age\": \"29\"},{ \"nickname\": \"陈赫\", \"age\": \"31\"}]}";
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		String username = jsonObj.getString("username");
		String password = jsonObj.getString("password");
		System.out.println("json--->java\n username=" + username
		 + "\t password=" + password);
		
		// 对象转JSON串
		String jsonString = JSONObject.toJSONString(jsonObj);
		System.out.println(jsonString);
		
		JSONArray jsonarray=jsonObj.getJSONArray("star_male");
		
		for (int i=0;i<jsonarray.size();i++){
		    JSONObject star = jsonarray.getJSONObject(i);
		    String starname = star.getString("nickname");
		    String starnage = star.getString("age");
		    System.out.println(starname+"   "+starnage);

		}
		
		
		
	}

}
