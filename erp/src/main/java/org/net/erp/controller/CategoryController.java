package org.net.erp.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.CategoryBO;
import org.net.erp.model.Category;
import org.net.erp.model.Master;
import org.net.erp.model.Services;
import org.net.erp.repository.CategoryRepository;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.ServiceRepository;
import org.net.erp.services.CategoryService;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ServiceRepository serviceRepo;

	@Autowired
	private CategoryService categoryService;

	@Autowired 
	private CategoryBO categoryBO;

	@Autowired
	private MasterRepository masterRepo;

	@PostMapping(Constants.EMPTY)
	public String createCategory(@Valid @ModelAttribute(Constants.CATEGORY_FORM) Category category,BindingResult bindingResult,HttpServletRequest request,Model model) {
		int key = 0;
		try {
			if(!bindingResult.hasErrors()) {
				key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(key);
				Category existingCategory = categoryRepo.getCategoryByName(category.getCategoryName(),key);
				if(null == existingCategory) {
					category.setCategoryStatus(Constants.ACTIVE_STATUS);
					category.setOrganization(master);
					categoryRepo.save(category);	
					model.addAttribute(Constants.CATEGORY_FORM,new Category());
				}else {
					String message = "Category " + category.getCategoryName() + " already exists.";
					model.addAttribute(Constants.EXISTING_CATEGORY,message);
				}			
			}
			List<Category> categories = categoryRepo.findByMasterId(key);		
			Map<Category,List<Services>> mapToDisplay = new LinkedHashMap<Category,List<Services>>();
			List<Services> services = serviceRepo.findByMasterId(key);
			for(Category element : categories) {					
				mapToDisplay.put(element,services
						.stream()
						.filter(service -> service.getCategory().getCategoryId() == element.getCategoryId())
						.collect(Collectors.toList()));
			}
			model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());
			model.addAttribute(Constants.SERVICE_FORM,new Services());
			model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR,new Services());	
			model.addAttribute(Constants.SERVICES_MAP, mapToDisplay);
		}catch(Exception e) {

		}
		return Constants.SERVICES_JSP;
	}

	@GetMapping("/getAllCategories")
	public ResponseEntity<?> getAllCategories(HttpServletRequest request){
		String jsonValue = null;
		try {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Gson gson = new Gson();
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			jsonValue = gson.toJson(categoryRepo.findByMasterId(key));
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/deleteCategory/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		try {
			Category category = categoryService.getCategoryById(id);
			category.setCategoryStatus(Constants.INACTIVE_STATUS);
			categoryService.save(category);
			serviceRepo.deletServiceAfterCategory(id);
			if(Constants.INACTIVE_STATUS == categoryService.getCategoryById(id).getCategoryStatus()) {
				jsonValue = categoryBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = categoryBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}	

	@GetMapping("/editCategory/{id}")
	public ResponseEntity<?> editCategory(@PathVariable(value = "id") int id,Model model) {
		String jsonValue = null;
		Category category = null;
		try {
			category = this.categoryService.getCategoryById(id);
			List<Category> categoryDetails = new ArrayList<Category>();
			categoryDetails.add(category);
			jsonValue = categoryBO.parseFetchCategory(categoryDetails);			
		}catch(Exception e) {

		}
		model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR, new Services());
		model.addAttribute(Constants.CATEGORY_FORM,new Category());
		model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,category);
		model.addAttribute(Constants.SERVICE_FORM,new Services());
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/editCategory/{id}")
	public String updateCategory(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_CATEGORY_FORM_ATTR) Category category,RedirectAttributes ra,BindingResult bindingResult,HttpServletRequest request,Model model) {
		String returnString = null;
		try {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			/*
			 * Category existingCategory =
			 * categoryRepo.getCategoryByName(category.getCategoryName(),key); if(null ==
			 * existingCategory) {
			 */			
			Master master = masterRepo.findByMasterId(key);
			category.setOrganization(master);
			category.setCategoryStatus(Constants.ACTIVE_STATUS);
			categoryService.save(category);
			model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());
			returnString = Constants.REDIRECT_SERVICES;
			/*			}else {
				String message = "Category " + category.getCategoryName() + " already exists.";
				ra.addFlashAttribute(Constants.EXISTING_EDIT_CATEGORY,message);
				returnString =  Constants.REDIRECT_SERVICES;
			}*/
		}catch(Exception e) {

		}
		model.addAttribute(Constants.CATEGORY_FORM,new Category());
		model.addAttribute(Constants.SERVICE_FORM,new Services());
		model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR,new Services());
		return returnString;
	}

}
