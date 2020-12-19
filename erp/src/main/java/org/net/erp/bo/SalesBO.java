package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.LastWeekSalesJson;
import org.net.erp.json.SaleDetailsJson;
import org.net.erp.json.SalesJson;
import org.net.erp.json.SalesNotInStockJson;
import org.net.erp.model.Meta;
import org.net.erp.model.SaleDetails;
import org.net.erp.model.Sales;
import org.net.erp.model.SalesNotInStock;
import org.net.erp.model.lastSevenDaysSales;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class SalesBO extends BaseBO{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesBO.class);
	
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
			salesJson = new SalesJson();
			salesJson.setData(sales);
			salesJson.setRecordsFiltered(sales.size());
			salesJson.setRecordsTotal(sales.size());
			json = gson.toJson(salesJson);
		}catch(Exception e) {
			LOGGER.error("Exception in parseFetchSales :: "+e.getMessage());
		}		
		return json;
	}
	/*
	 * 
	 * */
	public String parseSaleDetails(List<SaleDetails> saleDetails) {
		Gson gson = null;
		SaleDetailsJson salesJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.SALES_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(saleDetails.size());
			salesJson = new SaleDetailsJson();
			salesJson.setData(saleDetails);
			salesJson.setMeta(meta);
			json = gson.toJson(salesJson);
		}catch(Exception e) {
			LOGGER.error("Exception in parseSaleDetails :: "+e.getMessage());
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
			LOGGER.error("Exception in parseFetchSalesNotInStock :: "+e.getMessage());
		}		
		return json;
	}
	/*
	 * 
	 * */
	public String parseLastWeekSales(List<lastSevenDaysSales> lastSevenDaysJson) {
		String json = null;
		Gson gson = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			LastWeekSalesJson lastWeekSalesJson = new LastWeekSalesJson();
			lastWeekSalesJson.setData(lastSevenDaysJson);
			json = gson.toJson(lastWeekSalesJson);
		}catch(Exception e) {

		}
		return json;
	}

}
