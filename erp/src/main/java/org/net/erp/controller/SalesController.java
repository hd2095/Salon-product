package org.net.erp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.SalesBO;
import org.net.erp.model.Client;
import org.net.erp.model.Master;
import org.net.erp.model.OrderDetails;
import org.net.erp.model.Product;
import org.net.erp.model.SaleDetails;
import org.net.erp.model.Sales;
import org.net.erp.model.Stock;
import org.net.erp.model.Supplier;
import org.net.erp.model.lastSevenDaysSales;
import org.net.erp.repository.ClientRepository;
import org.net.erp.repository.LastWeekSalesRepository;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.SaleDetailsRepository;
import org.net.erp.repository.SalesNotInStockRepo;
import org.net.erp.repository.SalesRepository;
import org.net.erp.repository.StockRepository;
import org.net.erp.repository.SupplierRepository;
import org.net.erp.services.ClientService;
import org.net.erp.services.ProductService;
import org.net.erp.services.SalesService;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sell")
public class SalesController {
	@Autowired
	private MasterRepository masterRepo;

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private ClientService clientService;

	@Autowired
	private SalesRepository salesRepo;

	@Autowired
	private SaleDetailsRepository saleDetailsRepo;

	@Autowired
	private LastWeekSalesRepository lastWeekSalesRepo;

	@Autowired
	private SupplierRepository supplierRepo;

	@Autowired
	private SalesNotInStockRepo salesNotInStockRepo;

	@Autowired
	private StockRepository stockRepo;

	@Autowired
	private SalesService salesService;

	@Autowired
	private ProductService productService;

	@Autowired
	private SalesBO salesBO;

	@GetMapping("/sales")
	public String showSalesPage(Model model) {
		try {
			model.addAttribute(Constants.SALES_FORM, new Sales());
			model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
			/* model.addAttribute("salesNotInStockForm", new SalesNotInStock()); */

		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.SALES_JSP;
	}

	@GetMapping("/createSale")
	public String showSalesForm(Model model) {
		model.addAttribute(Constants.SALES_FORM, new Sales());
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.NEW_SALE_FORM;
	}

	@PostMapping("/createSale")
	public String handleSalesForm(@Valid @ModelAttribute(Constants.SALES_FORM) Sales sales,BindingResult bindingResult,HttpServletRequest request,Model model) {
		Master master = null;
		try {
			int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			String totalElements = request.getParameter("sale_total_elements");
			int repeaterCount = 0;
			if(null == totalElements || Constants.EMPTY.equalsIgnoreCase(totalElements) || Integer.parseInt(totalElements) == 0) {
				repeaterCount = 0;
			}else {
				repeaterCount = Integer.parseInt(totalElements);
			}
			for(int i  = 0;i <= repeaterCount; i++) {	
				String product = request.getParameter("["+ i +"][sale_product]");
				String productSellingPrice = request.getParameter("["+ i +"][product_selling_price]");		
				String productQuantity = request.getParameter("["+ i +"][product_quantity]");
				if(i == 0) {
					master = masterRepo.findByMasterId(master_id);
					sales.setOrganization(master);					
					sales.setSaleDeleteStatuts(Constants.ACTIVE_STATUS);
					Client client = clientService.getClientById(sales.getClient().getClientId());
					client.setRevenue_generated(client.getRevenue_generated() + sales.getSaleTotal());
					sales.setClient(client);
					salesRepo.saveAndFlush(sales);
				}
				SaleDetails saleDetails = new SaleDetails();
				int product_id = Integer.parseInt(product);
				Product productObj = productService.getProductById(product_id);
				saleDetails.setProduct(productObj);
				saleDetails.setSale(sales);
				saleDetails.setQuantity(Integer.parseInt(productQuantity));
				saleDetails.setSellingPrice(Float.parseFloat(productSellingPrice));
				saleDetails.setSaleDetailsDeleteStatuts(Constants.ACTIVE_STATUS);
				saleDetailsRepo.save(saleDetails);
				Stock stock = stockRepo.findByProductId(product_id);
				if(null != stock) {
					stock.setProduct(productObj);
					stock.setOrganization(master);
					stock.setStockId(stock.getStockId());
					stock.setStockQuantity(stock.getStockQuantity() - saleDetails.getQuantity());
					stock.setLastUpdatedDate(new Date());
					stockRepo.save(stock);
				}
			}
		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.SALES_JSP;
	}

	@RequestMapping("/getAllSales")
	public ResponseEntity<?> getAllSales(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = salesBO.parseFetchSales(salesRepo.findByMasterId(id));
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getSalesDetails/{id}")
	public ResponseEntity<?> getSalesDetails(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			List<SaleDetails> saleDetails = saleDetailsRepo.findBySaleId(id);
			jsonValue = salesBO.parseSaleDetails(saleDetails);
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/lastWeekSales")
	public ResponseEntity<?> getLastWeekSales(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);			
			List<lastSevenDaysSales> sales = lastWeekSalesRepo.lastWeekSales(id);
			Comparator<lastSevenDaysSales> sortedList = 
					(lastSevenDaysSales o1,lastSevenDaysSales o2) -> o1.getSellingDate().compareTo(o2.getSellingDate());
					Collections.sort(sales, sortedList);
					jsonValue = salesBO.parseLastWeekSales(sales);
		}catch(Exception e) {

		}

		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getAllSalesNotInStock")
	public ResponseEntity<?> getAllSalesNotInStock(HttpServletRequest request) {
		int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
		String jsonValue = salesBO.parseFetchSalesNotInStock(salesNotInStockRepo.findByMasterId(id));
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/deleteSale/{id}")
	public ResponseEntity<?> deleteSales(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			Sales sale =  salesService.getSalesById(id);
			sale.setSaleDeleteStatuts(Constants.INACTIVE_STATUS);	
			salesRepo.save(sale);
			if(Constants.INACTIVE_STATUS == salesService.getSalesById(id).getSaleDeleteStatuts()) {
				List<SaleDetails> allSaleDetails = saleDetailsRepo.findBySaleId(id);
				for(SaleDetails saleDetails : allSaleDetails) {
					Stock stock = stockRepo.findByProductId(saleDetails.getProduct().getProductId());
					stock.setStockQuantity(stock.getStockQuantity() + saleDetails.getQuantity());
					stock.setLastUpdatedDate(new Date());
					stockRepo.save(stock);
					saleDetails.setSaleDetailsDeleteStatuts(Constants.INACTIVE_STATUS);
					saleDetailsRepo.save(saleDetails);
				}
				Client client = clientService.getClientById(sale.getClient().getClientId());
				client.setRevenue_generated(client.getRevenue_generated() - sale.getSaleTotal());
				clientRepo.save(client);
				jsonValue = salesBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = salesBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/editSale/{id}")
	public String editSales(@PathVariable(value = "id") int id,Model model) {
		try {
			Sales sales = this.salesService.getSalesById(id);
			model.addAttribute(Constants.EDIT_SALES_FORM, sales);	
		}catch(Exception e) {

		}
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.EDIT_SALE_FORM;
	}

	@GetMapping("/generateSaleInvoice/{id}")
	public String generateInvoice(@PathVariable(value = "id") int id,Model model) {
		try {
			Sales sales = this.salesService.getSalesById(id);
			model.addAttribute(Constants.SALE_INVOICE_FORM, sales);	
		}catch(Exception e) {

		}
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.GENERATE_IN_VOICE_FORM;
	}
	
	@PostMapping("/editSale/{id}")
	public String updateSales(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_SALES_FORM) Sales sales,BindingResult bindingResult,HttpServletRequest request,Model model) {
		Master master = null;
		try {
			int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);		
			String totalElements = request.getParameter("edit_sale_total_elements");
			int repeaterCount = 0;
			if(null == totalElements || Constants.EMPTY.equalsIgnoreCase(totalElements) || Integer.parseInt(totalElements) == 0) {
				repeaterCount = 0;
			}else {
				repeaterCount = Integer.parseInt(totalElements);
			}
			master = masterRepo.findByMasterId(master_id);
			for(int i  = 0;i <= repeaterCount; i++) {
				sales.setOrganization(master);
				sales.setSaleDeleteStatuts(Constants.ACTIVE_STATUS);
				String product = request.getParameter("["+ i +"][edit_sale_product]");
				String productSellingPrice = request.getParameter("["+ i +"][edit_product_selling_price]");		
				String productQuantity = request.getParameter("["+ i +"][edit_product_quantity]");
				String recordId = request.getParameter("["+ i +"][edit_sale_record_id]");
				SaleDetails saleDetails = null;
				if(null != recordId && !Constants.EMPTY.equalsIgnoreCase(recordId)) {
					saleDetails = saleDetailsRepo.findBySaleDetailsId(Integer.parseInt(recordId));
				}else {
					saleDetails = new SaleDetails();	
					int product_id = Integer.parseInt(product);
					Product productObj = productService.getProductById(product_id);
					saleDetails.setProduct(productObj);
					saleDetails.setSale(sales);
					saleDetails.setQuantity(Integer.parseInt(productQuantity));
					saleDetails.setSellingPrice(Float.parseFloat(productSellingPrice));
					saleDetails.setSaleDetailsDeleteStatuts(Constants.ACTIVE_STATUS);
					saleDetailsRepo.save(saleDetails);
					Stock stock = stockRepo.findByProductId(product_id);
					if(null != stock) {
						stock.setProduct(productObj);
						stock.setOrganization(master);
						stock.setStockId(stock.getStockId());
						stock.setStockQuantity(stock.getStockQuantity() - saleDetails.getQuantity());
						stock.setLastUpdatedDate(new Date());
						stockRepo.save(stock);
					}					
					Client client = clientService.getClientById(sales.getClient().getClientId());
					client.setRevenue_generated(client.getRevenue_generated() + (saleDetails.getSellingPrice() * saleDetails.getQuantity()));
					sales.setClient(client);					
				}	
				salesRepo.saveAndFlush(sales);
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.SALES_FORM, new Sales());
		model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
		return Constants.REDIRECT_SELL_JSP;
	}

	@RequestMapping("/deleteProductFromSale/{id}")
	public ResponseEntity<?> deleteProductFromSale(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		try {
			SaleDetails productToDelete = saleDetailsRepo.findBySaleDetailsId(id);
			productToDelete.setSaleDetailsDeleteStatuts(Constants.INACTIVE_STATUS);
			Stock stock = stockRepo.findByProductId(productToDelete.getProduct().getProductId());
			stock.setStockQuantity(stock.getStockQuantity() + productToDelete.getQuantity());
			stock.setLastUpdatedDate(new Date());
			stockRepo.save(stock);		
			saleDetailsRepo.save(productToDelete);
			Sales sale = salesService.getSalesById(productToDelete.getSale().getSaleId());
			sale.setSaleTotal(sale.getSaleTotal() - (productToDelete.getSellingPrice() * productToDelete.getQuantity()));
			salesService.save(sale);
			if(Constants.INACTIVE_STATUS == saleDetailsRepo.findBySaleDetailsId(id).getSaleDetailsDeleteStatuts()) {
				jsonValue = salesBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = salesBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

}
