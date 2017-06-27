package com.tarento.esearch.utils;

import java.util.HashMap;
import java.util.Map;


public class EsearchUtils {

	public static Map<String, Object> prepareESJson(Map<String, String> mapObj) throws Exception {
		Map<String, Object> jsonDocument = new HashMap<String, Object>();
		try {
			for (Map.Entry<String, String> entry : mapObj.entrySet()) {
			    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			    jsonDocument.put(entry.getKey(), entry.getValue());
			}
		} catch (Exception expObj) {
			expObj.printStackTrace();
		}
		return jsonDocument;
	}
	
}
