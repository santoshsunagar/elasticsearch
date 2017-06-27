package com.tarento.esearch.service;

import java.util.Map;

public interface EsearchService {

	public void createDocument(String index, String type, String searchId, Map<String, String> mapObj);
	
	public void updateDocument(String index, String type, String searchId, String field, String newValue);
	
	public void removeDocument(String index, String type, String searchId);
	
	public Map<String, Object> getDocument(String index, String type, String searchId);
	
}
