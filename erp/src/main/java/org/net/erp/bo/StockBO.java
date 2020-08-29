package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.StockJson;
import org.net.erp.model.Stock;
import org.net.erp.model.Meta;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class StockBO extends BaseBO{
	/*
	 * 
	 * */
	public String parseFetchStock(List<Stock> stock) {
		Gson gson = null;
		StockJson stockJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.STOCK_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(stock.size());
			stockJson = new StockJson();
			stockJson.setData(stock);
			stockJson.setMeta(meta);
			json = gson.toJson(stockJson);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return json;
	}
	/*
	 * 
	 * */
	public String parseStockByProductId(Stock stock) {
		Gson gson = null;
		String json = null;
		try {
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			if(null != stock) {
				json = gson.toJson(stock);	
			}else {
				stock = new Stock();
				stock.setStockQuantity(0);
				json = gson.toJson(stock);
			}			
		}catch(Exception e) {
			
		}
		return json;
	}
	
}
