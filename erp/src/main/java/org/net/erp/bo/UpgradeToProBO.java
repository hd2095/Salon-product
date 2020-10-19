package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.UpgradeToProJson;
import org.net.erp.model.UpgradeToPro;
import org.net.erp.model.Meta;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class UpgradeToProBO {
	/*
	 * 
	 * */
	public String parseFetchRequest(List<UpgradeToPro> requests) {
		Gson gson = null;
		UpgradeToProJson upgradeToProJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.CLIENT_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(requests.size());
			upgradeToProJson = new UpgradeToProJson();
			upgradeToProJson.setData(requests);
			upgradeToProJson.setMeta(meta);
			json = gson.toJson(upgradeToProJson);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return json;
	}
	
}
