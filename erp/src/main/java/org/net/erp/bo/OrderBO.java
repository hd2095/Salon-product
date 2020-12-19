package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.OrderDetailsJson;
import org.net.erp.json.OrderJson;
import org.net.erp.model.Meta;
import org.net.erp.model.Order;
import org.net.erp.model.OrderDetails;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class OrderBO extends BaseBO{

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderBO.class);
	
	/*
	 * 
	 * */
	public String parseFetchOrder(List<Order> orders) {
		Gson gson = null;
		OrderJson orderJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			orderJson = new OrderJson();
			orderJson.setData(orders);
			orderJson.setRecordsFiltered(orders.size());
			orderJson.setRecordsTotal(orders.size());
			json = gson.toJson(orderJson);
		}catch(Exception e) {
			LOGGER.error("Exception in parseFetchOrder :: "+e.getMessage());
		}		
		return json;
	}
	/*
	 * 
	 * */
	public String parseFetchOrderDetails(List<OrderDetails> orderDetails) {
		Gson gson = null;
		OrderDetailsJson orderJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.ORDER_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(orderDetails.size());
			orderJson = new OrderDetailsJson();
			orderJson.setData(orderDetails);
			orderJson.setMeta(meta);
			json = gson.toJson(orderJson);
		}catch(Exception e) {
			LOGGER.error("Exception in parseFetchOrderDetails :: "+e.getMessage());
		}		
		return json;
	}
}
