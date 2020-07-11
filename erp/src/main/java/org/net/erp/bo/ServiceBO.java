package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.ServiceJson;
import org.net.erp.model.Services;
import org.net.erp.model.Meta;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class ServiceBO extends BaseBO{

	/*
	 * 
	 * */
	public String parseFetchService(List<Services> services) {
		Gson gson = null;
		ServiceJson serviceJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.CLIENT_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(services.size());
			serviceJson = new ServiceJson();
			serviceJson.setData(services);
			serviceJson.setMeta(meta);
			json = gson.toJson(serviceJson);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return json;
	}

}
