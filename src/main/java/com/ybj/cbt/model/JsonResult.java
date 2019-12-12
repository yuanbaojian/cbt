package com.ybj.cbt.model;

import java.util.HashMap;
import java.util.Map;


public class JsonResult {
	private int code;
	private Map<String, Object> data;
	
	public JsonResult() {
		this.code = 0;
	}
	
	public JsonResult(int code) {
		this.code = code;
	}
	
	public JsonResult(int code, Map<String, Object> map) {
		this.code = code;
		this.data = map;
	}
	
	public void put(String key, Object val) {
		if(data == null) {
			data = new HashMap<String, Object>();
		}
		data.put(key, val);
	}

	public Object get(String key) {
		if(data != null){
			return data.get(key);
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
