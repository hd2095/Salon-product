package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.ClientJson;
import org.net.erp.model.Client;
import org.net.erp.model.Meta;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class ClientBO extends BaseBO{

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
			Meta meta = new Meta();
			meta.setField(Constants.CLIENT_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(clients.size());
			clientJson = new ClientJson();
			clientJson.setData(clients);
			clientJson.setMeta(meta);
			json = gson.toJson(clientJson);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return json;
	}
	
	
}
