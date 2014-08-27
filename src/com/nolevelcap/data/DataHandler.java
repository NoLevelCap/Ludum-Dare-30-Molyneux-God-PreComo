package com.nolevelcap.data;

import java.util.HashMap;

public class DataHandler<E> {
	
	private HashMap<String, E> data;
	
	public DataHandler() {
		data = new HashMap<String, E>();
	}
	
	public void addData(String key, E value) {
		data.put(key, value);
	}
	
	public E addData(String key) {
		if(data.containsKey(key)) {
			return data.get(key);
		} else {
			new Exception();
		}
		return null;
	}
	
	public void clearTextures() {
		data.clear();
	}
}

