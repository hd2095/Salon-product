package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.ClientJson;
import org.net.erp.model.Client;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class ClientBO extends BaseBO{

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientBO.class);
	
	/*
	 * 
	 * */
	public String parseFetchClient(List<Client> clients) {
		Gson gson = null;
		ClientJson clientJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			clientJson = new ClientJson();
			clientJson.setData(clients);
			clientJson.setRecordsFiltered(clients.size());
			clientJson.setRecordsTotal(clients.size());
			json = gson.toJson(clientJson);
		}catch(Exception e) {
			LOGGER.error("Exception in parseFetchClient :: "+e.getMessage());
		}		
		return json;
	}
	
	
}
