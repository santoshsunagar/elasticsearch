package com.tarento.esearch.test;

import java.net.InetAddress;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.tarento.esearch.service.EsearchServiceImpl;


public class Main {

	public Main() {
		try {
			//testEsearch();
			esearchGetTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testEsearch() throws Exception {
		System.out.println("Esearch demo");
		Settings clusterSettings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();

        TransportClient transportClient = new PreBuiltTransportClient(clusterSettings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        try {
        	GetResponse responseget = transportClient.prepareGet("customers", "customer", 
            		"1").execute().actionGet(); 
            System.out.println("result:"+responseget);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transportClient.close();
        }
	}
	
	public void esearchGetTest() throws Exception {
		EsearchServiceImpl esearchServiceImpl = new EsearchServiceImpl();
		esearchServiceImpl.getDocument("orders", "order", "1");
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	
}
