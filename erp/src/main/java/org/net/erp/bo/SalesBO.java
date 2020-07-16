package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.SalesJson;
import org.net.erp.json.SalesNotInStockJson;
import org.net.erp.model.Meta;
import org.net.erp.model.Sales;
import org.net.erp.model.SalesNotInStock;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class SalesBO extends BaseBO{
	/*
	 * 
	 * */
	public String parseFetchSales(List<Sales> sales) {
		Gson gson = null;
		SalesJson salesJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.SALES_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(sales.size());
			salesJson = new SalesJson();
			salesJson.setData(sales);
			salesJson.setMeta(meta);
			json = gson.toJson(salesJson);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return json;
	}
	/*
	 * 
	 * */
	public String parseFetchSalesNotInStock(List<SalesNotInStock> sales) {
		Gson gson = null;
		SalesNotInStockJson salesJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.SALES_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(sales.size());
			salesJson = new SalesNotInStockJson();
			salesJson.setData(sales);
			salesJson.setMeta(meta);
			json = gson.toJson(salesJson);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return json;
	}

}
