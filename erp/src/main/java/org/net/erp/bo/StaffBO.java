package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.StaffJson;
import org.net.erp.model.Staff;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class StaffBO extends BaseBO{

	private static final Logger LOGGER = LoggerFactory.getLogger(StaffBO.class);
	
	/*
	 * 
	 * */
	public String parseFetchStaff(List<Staff> staff) {
		Gson gson = null;
		StaffJson staffJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			staffJson = new StaffJson();
			staffJson.setData(staff);
			staffJson.setRecordsFiltered(staff.size());
			staffJson.setRecordsTotal(staff.size());
			json = gson.toJson(staffJson);
		}catch(Exception e) {
			LOGGER.error("Exception in parseFetchStaff :: "+e.getMessage());
		}		
		return json;
	}
	

}
