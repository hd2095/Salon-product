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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("/products")
	public String showProductPage(Model model,HttpServletRequest request) {
		int id = 0;
		try {
			id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			model.addAttribute(Constants.PRODUCT_FORM, new Product());
			model.addAttribute(Constants.EDIT_PRODUCT_FORM, new Product());
			Master master = masterRepo.findByMasterId(id);
			int entries = productService.checkProductEntries(id);
			if(master.getOrganizationPlan().equalsIgnoreCase("Basic")) {
				if(entries < 10) {
					model.addAttribute("showAddBtn", true);
				}
			}else if(master.getOrganizationPlan().equalsIgnoreCase("Standard")) {
				if(entries < 100) {
					model.addAttribute("showAddBtn", true);
				}
			}
		}catch(Exception e) {
			LOGGER.error("Exception in showProductPage for organization id :: "+id+" :: "+e.getMessage());
		}
		return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.PRODUCT_JSP;		
	}

	@PostMapping("/products")
	public String createProduct(@Valid @ModelAttribute(Constants.PRODUCT_FORM) Product product,BindingResult bindingResult,HttpServletRequest request,Model model) {
		int key = 0;
		try {
			if(!bindingResult.hasErrors()) {
				key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				List<String> existingProducts = productRepo.fetchByCategory(key, product.getProductBrand());
				if(null != existingProducts) {
					for(String temp : existingProducts) {
						if(temp.equalsIgnoreCase(product.getProductName())) {
							if(product.getProductBrand().contains("'")) {
								product.setProductBrand(product.getProductBrand().replaceAll("'", "\\'"));
							}
							String message = product.getProductName() + " already exists under brand " +product.getProductBrand();
							model.addAttribute(Constants.EXISTING_PRODUCT,message);
							model.addAttribute(Constants.EDIT_PRODUCT_FORM, new Product());
							return Constants.DISPLAY_FOLDER + Constants.FORWARD_SLASH +Constants.PRODUCT_JSP;	
						}
					}
				}
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
			LOGGER.error("Exception in createProduct for organization id :: "+key+" :: "+e.getMessage());
		}
		return "redirect:/inventory/products";
	}

	@RequestMapping("/getAllProducts")
	public ResponseEntity<?> getAllProducts(HttpServletRequest request) {
		String jsonValue = null;
		int id = 0;
		try{
			id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = productBO.parseFetchProduct(productRepo.findByMasterId(id));	
		}catch(Exception e) {
			LOGGER.error("Exception in getAllProducts for organization id :: "+id+" :: "+e.getMessage());
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
			LOGGER.error("Exception in deleteProduct for product id :: "+id+" :: "+e.getMessage());
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
			LOGGER.error("Exception in editProduct for product id :: "+id+" :: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/products/editProduct/{id}")
	public String updateProduct(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_PRODUCT_FORM) Product product,RedirectAttributes ra,BindingResult bindingResult,HttpServletRequest request,Model model) {
		int key = 0;
		try {
			key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			List<String> existingProducts = productRepo.fetchByCategory(key, product.getProductBrand());
			if(null != existingProducts) {
				for(String temp : existingProducts) {
					if(temp.equalsIgnoreCase(product.getProductName())) {
						if(product.getProductBrand().contains("'")) {
							product.setProductBrand(product.getProductBrand().replaceAll("'", "\\'"));
						}
						String message = product.getProductName() + " already exists under brand " +product.getProductBrand();
						ra.addFlashAttribute(Constants.EXISTING_PRODUCT_EDIT,message);
						ra.addFlashAttribute("editProductId",id);
						return Constants.REDIRECT+Constants.INVENTORY_FOLDER + Constants.FORWARD_SLASH +Constants.PRODUCTS_JSP;		
					}
				}
			}
			Master master = masterRepo.findByMasterId(key);
			product.setOrganization(master);
			product.setProductId(id);
			if(null == product.getProductBarcode() || product.getProductBarcode().equalsIgnoreCase(Constants.EMPTY)) {
				product.setProductBarcode(Constants.EMPTY_PRODUCT_BARCODE);
			}
			product.setProductStatus(Constants.ACTIVE_STATUS);
			productRepo.save(product);
		}catch(Exception e) {
			LOGGER.error("Exception in updateProduct for organization id :: " +key+" product id :: "+id+" :: "+e.getMessage());
		}
		model.addAttribute(Constants.PRODUCT_FORM, new Product());
		model.addAttribute(Constants.EDIT_PRODUCT_FORM, new Product());
		return Constants.REDIRECT+Constants.INVENTORY_FOLDER + Constants.FORWARD_SLASH +Constants.PRODUCTS_JSP;
	}

}
