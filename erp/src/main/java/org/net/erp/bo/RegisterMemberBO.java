package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.RegisterMemberJson;
import org.net.erp.model.Meta;
import org.net.erp.model.RegisterMember;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class RegisterMemberBO extends BaseBO{
	/*
	 * 
	 * */
	public String parseAllMembers(List<RegisterMember> allMembers) {
		Gson gson = null;
		RegisterMemberJson RegisterMemberJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.MEMBER_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(allMembers.size());
			RegisterMemberJson = new RegisterMemberJson();
			RegisterMemberJson.setData(allMembers);
			RegisterMemberJson.setMeta(meta);
			json = gson.toJson(RegisterMemberJson);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return json;
	}
}
