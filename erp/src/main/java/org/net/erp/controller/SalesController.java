package org.net.erp.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.SalesBO;
import org.net.erp.model.Client;
import org.net.erp.model.Master;
import org.net.erp.model.Product;
import org.net.erp.model.Sales;
import org.net.erp.model.Stock;
import org.net.erp.model.Supplier;
import org.net.erp.model.lastSevenDaysSales;
import org.net.erp.repository.ClientRepository;
import org.net.erp.repository.LastWeekSalesRepository;
import org.net.erp.repository.MasterRepository;
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
@RequestMapping("inventory")
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

	@PostMapping("/sales")
	public String handleSalesForm(@Valid @ModelAttribute(Constants.SALES_FORM) Sales sales,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
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
				Stock stock = stockRepo.findByStockId(sales.getStock().getStockId());
				int quantity = stock.getStockQuantity() - sales.getQuantity();
				stock.setStockQuantity(quantity);
				int supplierId = stock.getSupplier().getSupplierId();
				Supplier supplier = supplierRepo.findBySupplierId(supplierId);
				sales.setSupplier(supplier);
				Client client = clientService.getClientById(sales.getClient().getClientId());
				float clientRevenue = client.getRevenue_generated();
				float sellingPrice = sales.getSellingPrice();
				clientRevenue = clientRevenue + sellingPrice;
				client.setRevenue_generated(clientRevenue);
				clientRepo.save(client);
				salesRepo.save(sales);
				stockRepo.save(stock);
				model.addAttribute(Constants.SALES_FORM, new Sales());
				model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
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
		float totalSales = 0;
		int counter = 0;
		LinkedHashMap<Date, Float> finalMap = new LinkedHashMap<Date,Float>();
		List<Integer> idsToSkip = new ArrayList<Integer>();
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);			
			List<lastSevenDaysSales> sales = lastWeekSalesRepo.lastWeekSales(id);					
			for(int i = 0;i < sales.size(); i++) {
				if(!idsToSkip.contains(sales.get(i).getSalesId())) {
					for(int j = i+1; j < sales.size(); j++) {
						if(sales.get(i).getSellingDate().equals(sales.get(j).getSellingDate())) {	
							idsToSkip.add(sales.get(j).getSalesId());
							if(counter == 0) {
								totalSales = totalSales + (sales.get(i).getSellingPrice() + sales.get(j).getSellingPrice());
							}else {
								totalSales = totalSales + sales.get(j).getSellingPrice();
							}
							counter++;						
						}else {
							if(counter == 0) {
								totalSales = sales.get(i).getSellingPrice();
								break;
							}
						}				
					}
					finalMap.put(sales.get(i).getSellingDate(), totalSales);
					totalSales = 0;
					counter = 0;
				}
			}
			jsonValue = salesBO.parseLastWeekSales(finalMap);
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

	@GetMapping("/sales/deleteSales/{id}")
	public ResponseEntity<?> deleteSales(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			salesService.deleteSales(id);
			if(null == salesService.getSalesById(id)) {
				jsonValue = salesBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = salesBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/sales/editSales/{id}")
	public ResponseEntity<?> editSales(@PathVariable(value = "id") int id,Model model) {
		String jsonValue = null;
		try {
			Sales sales = this.salesService.getSalesById(id);
			List<Sales> salesDetails = new ArrayList<Sales>();
			salesDetails.add(sales);
			jsonValue = salesBO.parseFetchSales(salesDetails);
			model.addAttribute(Constants.EDIT_SALES_FORM, sales);	
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/sales/editSales/{id}")
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
