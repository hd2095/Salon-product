package org.net.erp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.SupplierBO;
import org.net.erp.model.Master;
import org.net.erp.model.Supplier;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.SupplierRepository;
import org.net.erp.services.SupplierService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("buy")
public class SupplierController {
	@Autowired
	private MasterRepository masterRepo;

	@Autowired
	private SupplierRepository supplierRepo;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private SupplierBO supplierBO;

	@GetMapping("/addSupplier")
	public String showSupplierPage(Model model) {
		try {
			model.addAttribute(Constants.SUPPLIER_FORM, new Supplier());
			model.addAttribute(Constants.EDIT_SUPPLIER_FORM, new Supplier());
		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.SUPPLIER_JSP;
	}
	@PostMapping("/addSupplier")
	public String createSupplier(@Valid @ModelAttribute(Constants.SUPPLIER_FORM) Supplier supplier,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Supplier existingSupplier = supplierRepo.checkIfSupplierWithSameNumberExists(key, supplier.getSupplierNumber());
				if(null == existingSupplier) {
					Master master = masterRepo.findByMasterId(key);
					supplier.setOrganization(master);
					supplier.setSupplierStatus(Constants.ACTIVE_STATUS);
					if(null == supplier.getSupplierGstnNo() || supplier.getSupplierGstnNo().equalsIgnoreCase(Constants.EMPTY)) {
						supplier.setSupplierGstnNo(Constants.EMPTY_GSTN_NO);
					}
					model.addAttribute(Constants.SUPPLIER_FORM, new Supplier());
					supplierRepo.save(supplier);	
				}else {
					String message = "Supplier with number "+existingSupplier.getSupplierNumber() + " exists";
					model.addAttribute(Constants.EXISTING_SUPPLIER, message);
					model.addAttribute(Constants.SUPPLIER_FORM,supplier);
					model.addAttribute(Constants.EDIT_SUPPLIER_FORM, new Supplier());
					return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.SUPPLIER_JSP;
				}			
			}
			model.addAttribute(Constants.SUPPLIER_FORM, new Supplier());
			model.addAttribute(Constants.EDIT_SUPPLIER_FORM, new Supplier());
		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.SUPPLIER_JSP;
	}

	@RequestMapping("/getAllSuppliers")
	public ResponseEntity<?> getAllProducts(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = supplierBO.parseFetchSupplier(supplierRepo.findByMasterId(id));
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/supplier/deleteSupplier/{id}")
	public ResponseEntity<?> deleteSupplier(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			Supplier supplier = supplierService.getSupplierById(id);
			supplier.setSupplierStatus(Constants.INACTIVE_STATUS);
			supplierService.save(supplier);
			if(Constants.INACTIVE_STATUS == supplierService.getSupplierById(id).getSupplierStatus()) {
				jsonValue = supplierBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = supplierBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/supplier/editSupplier/{id}")
	public ResponseEntity<?> editSupplier(@PathVariable(value = "id") int id,Model model) {
		String jsonValue = null;
		try {
			Supplier supplier = this.supplierService.getSupplierById(id);
			List<Supplier> supplierDetails = new ArrayList<Supplier>();
			supplierDetails.add(supplier);
			jsonValue = supplierBO.parseFetchSupplier(supplierDetails);
			model.addAttribute(Constants.EDIT_SUPPLIER_FORM, supplier);	
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/supplier/editSupplier/{id}")
	public String updateSupplier(@PathVariable(value = "id") int id,RedirectAttributes ra,@ModelAttribute(Constants.EDIT_SUPPLIER_FORM) Supplier supplier,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Supplier existingSupplier = supplierRepo.checkIfSupplierWithSameNumberExists(key, supplier.getSupplierNumber());
			if(null == existingSupplier) {
				Master master = masterRepo.findByMasterId(key);
				supplier.setOrganization(master);
				supplier.setSupplierId(id);
				if(null == supplier.getSupplierGstnNo() || supplier.getSupplierGstnNo().equalsIgnoreCase(Constants.EMPTY)) {
					supplier.setSupplierGstnNo(Constants.EMPTY_GSTN_NO);
				}
				supplier.setSupplierStatus(Constants.ACTIVE_STATUS);
				supplierRepo.save(supplier);
			}else { 
				if(existingSupplier.getSupplierId() == supplier.getSupplierId()) {
					supplier.setOrganization(existingSupplier.getOrganization());
					supplier.setSupplierId(id);
					if(null == supplier.getSupplierGstnNo() || supplier.getSupplierGstnNo().equalsIgnoreCase(Constants.EMPTY)) {
						supplier.setSupplierGstnNo(Constants.EMPTY_GSTN_NO);
					}
					supplier.setSupplierStatus(Constants.ACTIVE_STATUS);
					supplierRepo.save(supplier);
				}else {
					String message = "Supplier "+existingSupplier.getSupplierName() +" has the same number "+supplier.getSupplierNumber();
					ra.addFlashAttribute(Constants.EXISTING_EDIT_SUPPLIER, message);
					ra.addFlashAttribute("editSupplierId", supplier.getSupplierId());
				}
			}

		}catch(Exception e) {

		}
		model.addAttribute(Constants.SUPPLIER_FORM, new Supplier());
		model.addAttribute(Constants.EDIT_SUPPLIER_FORM, new Supplier());
		return Constants.REDIRECT+Constants.BUY_FOLDER + Constants.FORWARD_SLASH +Constants.SUPPLIERS_JSP;
	}


}
