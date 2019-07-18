package com.qa.util;

import com.google.gson.Gson;

public class JSONUtil {

	private Gson gson = new Gson();
	
	public String getJSONForObject(Object obj) {
//		JSONObject json = new JSONObject();
//		json.put("accountNumber", a.getAccountNumber());
//		json.put("firstName", a.getFirstName());
//		json.put("lastName", a.getLastName());
//		return json.toString();
		return gson.toJson(obj);
	}

	public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
//		JSONObject json = new JSONObject(jsonString);
//		return new Account(json.getInt("accountNumber"), json.getString("firstName"), json.getString("lastName"));
		return gson.fromJson(jsonString, clazz);
	}

} 
