package org.net.erp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.ProductBO;
import org.net.erp.model.Master;
import org.net.erp.model.Product;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.ProductRepository;
import org.net.erp.services.ProductService;
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
public class ProductController{

	@Autowired
	private MasterRepository masterRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductBO productBO;

	@GetMapping("/products")
	public String showProductPage(Model model) {
		try {
			model.addAttribute(Constants.PRODUCT_FORM, new Product());
			model.addAttribute(Constants.EDIT_PRODUCT_FORM, new Product());
		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.PRODUCT_JSP;
	}

	@PostMapping("/products")
	public String createProduct(@Valid @ModelAttribute(Constants.PRODUCT_FORM) Product product,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(key);
				product.setProductStatus(Constants.ACTIVE_STATUS);
				product.setOrganization(master);
				if(null == product.getProductBarcode() || product.getProductBarcode().equalsIgnoreCase(Constants.EMPTY)) {
					product.setProductBarcode(Constants.EMPTY_PRODUCT_BARCODE);
				}
				productRepo.save(product);
				model.addAttribute(Constants.PRODUCT_FORM, new Product());
				model.addAttribute(Constants.EDIT_PRODUCT_FORM, new Product());
			}
		}catch(Exception e) {

		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.PRODUCT_JSP;
	}

	@RequestMapping("/getAllProducts")
	public ResponseEntity<?> getAllProducts(HttpServletRequest request) {
		String jsonValue = null;
		try{
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = productBO.parseFetchProduct(productRepo.findByMasterId(id));	
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/products/deleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			Product product = productService.getProductById(id);
			product.setProductStatus(Constants.INACTIVE_STATUS);
			productService.save(product);
			if(Constants.INACTIVE_STATUS == productService.getProductById(id).getProductStatus()) {
				jsonValue = productBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = productBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/products/editProduct/{id}")
	public ResponseEntity<?> editProduct(@PathVariable(value = "id") int id,Model model) {
		String jsonValue = null;
		try {
			Product product = this.productService.getProductById(id);
			List<Product> productDetails = new ArrayList<Product>();
			productDetails.add(product);
			jsonValue = productBO.parseFetchProduct(productDetails);
			model.addAttribute(Constants.EDIT_PRODUCT_FORM, product);	
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/products/editProduct/{id}")
	public String updateProduct(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_PRODUCT_FORM) Product product,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = masterRepo.findByMasterId(key);
			product.setOrganization(master);
			product.setProductId(id);
			if(null == product.getProductBarcode() || product.getProductBarcode().equalsIgnoreCase(Constants.EMPTY)) {
				product.setProductBarcode(Constants.EMPTY_PRODUCT_BARCODE);
			}
			product.setProductStatus(Constants.ACTIVE_STATUS);
			productRepo.save(product);
		}catch(Exception e) {

		}
		model.addAttribute(Constants.PRODUCT_FORM, new Product());
		model.addAttribute(Constants.EDIT_PRODUCT_FORM, new Product());
		return Constants.REDIRECT+Constants.INVENTORY_FOLDER + Constants.FORWARD_SLASH +Constants.PRODUCTS_JSP;
	}

}
