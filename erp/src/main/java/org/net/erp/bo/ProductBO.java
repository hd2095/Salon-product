package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.ProductJson;
import org.net.erp.model.Meta;
import org.net.erp.model.Product;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class ProductBO extends BaseBO{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductBO.class);
	
	/*
	 * 
	 * */
	public String parseFetchProduct(List<Product> product) {
		Gson gson = null;
		ProductJson productJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.PRODUCT_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(product.size());
			productJson = new ProductJson();
			productJson.setData(product);
			productJson.setMeta(meta);
			json = gson.toJson(productJson);
		}catch(Exception e) {
			LOGGER.error("Exception in parseFetchProduct :: "+e.getMessage());
		}		
		return json;
	}

}
