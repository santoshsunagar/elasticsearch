package com.tarento.esearch.service;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.tarento.esearch.constants.EsearchConstants;
import com.tarento.esearch.utils.EsearchMockUtil;
import com.tarento.esearch.utils.EsearchUtils;

public class EsearchServiceImpl implements EsearchService {

	public void createDocument(String index, String type, String searchId, Map<String, String> mapObj){
		try {
			Settings clusterSettings = Settings.builder().put(EsearchConstants.CLUSTER_NAME, EsearchConstants.CLUSTER_NAME_VALUE).build();
			TransportClient transportClient = new PreBuiltTransportClient(clusterSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsearchConstants.CLUSTER_NODE_HOST), EsearchConstants.CLUSTER_NODE_PORT));
			mapObj = EsearchMockUtil.prepareESJson();
			transportClient.prepareIndex(index, type, searchId)
            .setSource(EsearchUtils.prepareESJson(mapObj)).execute().actionGet();
		} catch (Exception expObj) {
			expObj.printStackTrace();
		}  finally {
            //transportClient.close();
        }
	}
	
	public void updateDocument(String index, String type, String searchId, String field, String newValue) {
		try {
			Settings clusterSettings = Settings.builder().put(EsearchConstants.CLUSTER_NAME, EsearchConstants.CLUSTER_NAME_VALUE).build();
			TransportClient transportClient = new PreBuiltTransportClient(clusterSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsearchConstants.CLUSTER_NODE_HOST), EsearchConstants.CLUSTER_NODE_PORT));
			Map<String, Object> updateObject = new HashMap<String, Object>();
	        updateObject.put(field, newValue);
	        /*transportClient.prepareUpdate(index, type, searchId)
	              .setScript("ctx._source." + field + "=" + field)
	              .setScriptParams(updateObject).execute().actionGet();*/
		} catch (Exception expObj) {
			expObj.printStackTrace();
		} finally {
            //transportClient.close();
        }
	}
	
	public void removeDocument(String index, String type, String searchId) {
		try {
			Settings clusterSettings = Settings.builder().put(EsearchConstants.CLUSTER_NAME, EsearchConstants.CLUSTER_NAME_VALUE).build();
			TransportClient transportClient = new PreBuiltTransportClient(clusterSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsearchConstants.CLUSTER_NODE_HOST), EsearchConstants.CLUSTER_NODE_PORT));
			DeleteResponse response = transportClient.prepareDelete(index, type, searchId).execute().actionGet();
		} catch (Exception expObj) {
			expObj.printStackTrace();
		} finally {
            //transportClient.close();
        }
	}
	
	public Map<String, Object> getDocument(String index, String type, String searchId) {
		Map<String, Object> source = null;
		try {
			Settings clusterSettings = Settings.builder().put(EsearchConstants.CLUSTER_NAME, EsearchConstants.CLUSTER_NAME_VALUE).build();
			TransportClient transportClient = new PreBuiltTransportClient(clusterSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsearchConstants.CLUSTER_NODE_HOST), EsearchConstants.CLUSTER_NODE_PORT));
			GetResponse getResponse = transportClient
					.prepareGet(index, type, searchId).execute().actionGet();
			source= new HashMap<String, Object>();
			source = getResponse.getSource();
		} catch (Exception expObj) {
			expObj.printStackTrace();
		} finally {
            //transportClient.close();
        }
		return source;
	}

}
