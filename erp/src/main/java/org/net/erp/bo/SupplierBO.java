package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.SupplierJson;
import org.net.erp.model.Meta;
import org.net.erp.model.Supplier;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class SupplierBO extends BaseBO{

	private static final Logger LOGGER = LoggerFactory.getLogger(SupplierBO.class);
	
	/*
	 * 
	 * */
	public String parseFetchSupplier(List<Supplier> supplier) {
		Gson gson = null;
		SupplierJson supplierJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.STAFF_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(supplier.size());
			supplierJson = new SupplierJson();
			supplierJson.setData(supplier);
			supplierJson.setMeta(meta);
			json = gson.toJson(supplierJson);
		}catch(Exception e) {
			LOGGER.error("Exception in parseFetchSupplier :: "+e.getMessage());
		}		
		return json;
	}
	
}
