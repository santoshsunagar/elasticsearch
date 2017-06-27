package com.tarento.esearch.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tarento.esearch.model.ResponseStatus;
import com.tarento.esearch.service.EsearchServiceImpl;

@RestController
public class OrderController {

	@RequestMapping(value = "/{index}/{type}/{searchId}", method = RequestMethod.GET)
	public Map<String, Object> getOrder(@PathVariable String index,
			@PathVariable String type, @PathVariable String searchId) {
		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			EsearchServiceImpl serviceImpl = new EsearchServiceImpl();
			map = serviceImpl.getDocument(index, type, searchId);

		} catch (Exception expObj) {
			expObj.printStackTrace();
		} finally {
			// transportClient.close();
		}
		return map;
	}

	@RequestMapping(value = "/{index}/{type}/{searchId}", method = RequestMethod.POST)
	public Map<String, String> createDocument(@PathVariable String index,
			@PathVariable String type, @PathVariable String searchId, @RequestBody String requestData) {
		Map<String, String> mapObj = new HashMap<String, String>();
		try {
			JSONArray jsonarr = new JSONArray(requestData);
			for (int i = 0; i < jsonarr.length(); i++) {
				JSONObject jsonobj = jsonarr.getJSONObject(i);
				mapObj.put("orderId", jsonobj.getString("orderId"));
				mapObj.put("orderName", jsonobj.getString("orderName"));
				mapObj.put("orderDesc", jsonobj.getString("orderDesc"));
				mapObj.put("orderCreated", jsonobj.getString("orderCreated"));
			}
			EsearchServiceImpl serviceImpl = new EsearchServiceImpl();
			serviceImpl.createDocument(index, type, searchId, mapObj);
		} catch (Exception expObj) {
			expObj.printStackTrace();
		} finally {
			// transportClient.close();
		}
		return mapObj;
	}

	@RequestMapping(value = "/{index}/{type}/{searchId}", method = RequestMethod.PUT)
	public Map<String, Object> updateDocument(@PathVariable String index,
			@PathVariable String type, @PathVariable String searchId) {
		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			EsearchServiceImpl serviceImpl = new EsearchServiceImpl();
			map = serviceImpl.getDocument(index, type, searchId);

		} catch (Exception expObj) {
			expObj.printStackTrace();
		} finally {
			// transportClient.close();
		}
		return map;
	}

	@RequestMapping(value = "/{index}/{type}/{searchId}", method = RequestMethod.DELETE)
	public ResponseStatus removeDocument(@PathVariable String index, @PathVariable String type, @PathVariable String searchId) {
		ResponseStatus status = new ResponseStatus();
		try {
			EsearchServiceImpl serviceImpl = new EsearchServiceImpl();
			serviceImpl.removeDocument(index, type, searchId);
		} catch (Exception expObj) {
			expObj.printStackTrace();
		} finally {
			// transportClient.close();
		}
		return status;
	}

}
