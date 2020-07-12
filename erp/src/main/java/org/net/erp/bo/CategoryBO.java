package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.CategoryJson;
import org.net.erp.model.Category;
import org.net.erp.model.Meta;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class CategoryBO extends BaseBO{

	/*
	 * 
	 * */
	public String parseFetchCategory(List<Category> category) {
		Gson gson = null;
		CategoryJson categoryJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.CATEGORY_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(category.size());
			categoryJson = new CategoryJson();
			categoryJson.setData(category);
			categoryJson.setMeta(meta);
			json = gson.toJson(categoryJson);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return json;
	}

}
