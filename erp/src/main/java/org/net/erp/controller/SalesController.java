package org.net.erp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		/* try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Stock stock = stockRepo.findByStockId(sales.getStock().getStockId());
				if(stock.getStockQuantity() >= sales.getQuantity()) {
					Master master = masterRepo.findByMasterId(key);
					sales.setOrganization(master);
					if(null != request.getParameter("salesProductId")) {
						int id = Integer.parseInt(request.getParameter("salesProductId"));
						Product product = productService.getProductById(id);
						sales.setProduct(product);
					}
					if(null != request.getParameter("sales_CostPrice")){
						float costPrice = Float.parseFloat(request.getParameter("sales_CostPrice"));
						sales.setCostPrice(costPrice);
					}
					float saleTotal = sales.getSellingPrice() * sales.getQuantity();
					sales.setSaleTotal(saleTotal);					
					int quantity = stock.getStockQuantity() - sales.getQuantity();
					stock.setStockQuantity(quantity);
					int supplierId =  0;//stock.getSupplier().getSupplierId();
					Supplier supplier = supplierRepo.findBySupplierId(supplierId);
					sales.setSupplier(supplier);
					Client client = clientService.getClientById(sales.getClient().getClientId());
					float clientRevenue = client.getRevenue_generated();
					float sellingPrice = sales.getSellingPrice();
					sellingPrice = sellingPrice * sales.getQuantity();
					clientRevenue = clientRevenue + sellingPrice;
					client.setRevenue_generated(clientRevenue);
					clientRepo.save(client);					
					stockRepo.save(stock);
					salesRepo.save(sales);
					lastSevenDaysSales existingSale = lastWeekSalesRepo.checkIfSaleExists(key,sales.getSaleDate());
					if(null == existingSale) {	
						lastSevenDaysSales lastSevenDaysSales = new lastSevenDaysSales();
						lastSevenDaysSales.setSellingDate(sales.getSaleDate());
						lastSevenDaysSales.setSellingPrice(sales.getSaleTotal());
						lastSevenDaysSales.setOrganization(master);
						lastWeekSalesRepo.save(lastSevenDaysSales);
					} 
					else { 
						float newSaleTotal = existingSale.getSellingPrice() + saleTotal;
						lastWeekSalesRepo.updateSaleTotal(existingSale.getSalesId(),newSaleTotal); 
					}
					model.addAttribute(Constants.SALES_FORM, new Sales());
				}else {
					String message = null;
					if(null != request.getParameter("salesProductId")) {
						int id = Integer.parseInt(request.getParameter("salesProductId"));
						Product product = productService.getProductById(id);
						sales.setProduct(product);
						if(null != sales.getProduct()) {
							message = "You dont have enough stock of "+sales.getProduct().getProductName();
						}else {
							message = "You dont have enough stock";
						}
					}					
					model.addAttribute(Constants.OUT_OF_STOCK, message);
				}				
				model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
			}
		}catch(Exception e) {

		} */
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

	@PostMapping("/editSales/{id}")
	public String updateSales(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_SALES_FORM) Sales sales,BindingResult bindingResult,HttpServletRequest request,Model model) {
		int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
		Master master = masterRepo.findByMasterId(key);
		sales.setOrganization(master);
		salesRepo.save(sales);
		model.addAttribute(Constants.SALES_FORM, new Sales());
		model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
		return Constants.REDIRECT+Constants.INVENTORY_FOLDER + Constants.FORWARD_SLASH +Constants.PRODUCTS_JSP;
	}

}
