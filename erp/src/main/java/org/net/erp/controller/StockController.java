package org.net.erp.controller;

import javax.servlet.http.HttpServletRequest;

import org.net.erp.bo.StockBO;
import org.net.erp.model.Stock;
import org.net.erp.repository.StockRepository;
import org.net.erp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("inventory") 
public class StockController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

	@Autowired 
	StockBO stockBO;

	@Autowired 
	StockRepository stockRepo;

	@RequestMapping("/stock") 
	public String showStocksPage() { 
		String returnValue = null;
		try {
			returnValue = "display/display-stock-page";
		}catch(Exception e) {
			LOGGER.error("Exception in showStocksPage :: "+e.getMessage());
		}
		return returnValue;
	}

	@RequestMapping("/stock/getAllStock")
	public ResponseEntity<?> getAllStock(HttpServletRequest request){ 
		String jsonValue = null;
		int id = 0;
		try {
			id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY); 
			jsonValue = stockBO.parseFetchStock(stockRepo.findByMasterId(id)); 
		}catch(Exception e) {
			LOGGER.error("Exception in getAllStock for organization id " +id+ " :: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue); 
	} 

	@RequestMapping("/stock/getStockByProductId/{id}")
	public ResponseEntity<?> getStockByProductId(@PathVariable(value = "id") int id){ 
		String jsonValue = null;
		try {
			Stock stock = stockRepo.findByProductId(id);	
			jsonValue = stockBO.parseStockByProductId(stock);
		}catch(Exception e) {
			LOGGER.error("Exception in getStockByProductId for stock id " +id+ " :: "+e.getMessage());
		}		
		return ResponseEntity.ok(jsonValue); 
	} 


}
