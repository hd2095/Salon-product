package org.net.erp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.SalesBO;
import org.net.erp.model.Master;
import org.net.erp.model.Sales;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.SalesRepository;
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
	private SalesRepository salesRepo;
	
	@Autowired
	private SalesService salesService;
	
	@Autowired
	private SalesBO salesBO;
	
	@GetMapping("/sales")
	public String showSalesPage(Model model) {
		try {
			model.addAttribute(Constants.SALES_FORM, new Sales());
			model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
		}catch(Exception e) {
			
		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.PRODUCT_JSP;
	}
	
	@PostMapping("/sales")
	public String handleSalesForm(@Valid @ModelAttribute(Constants.SALES_FORM) Sales sales,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(key);
				sales.setOrganization(master);
				salesRepo.save(sales);
				model.addAttribute(Constants.SALES_FORM, new Sales());
				model.addAttribute(Constants.EDIT_SALES_FORM, new Sales());
			}
		}catch(Exception e) {
			
		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.PRODUCT_JSP;
	}
	
	@RequestMapping("/getAllSales")
	public ResponseEntity<?> getAllSaless(HttpServletRequest request) {
		int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
		String jsonValue = salesBO.parseFetchSales(salesRepo.findByMasterId(id));
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
