package org.net.erp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.model.Category;
import org.net.erp.model.Master;
import org.net.erp.model.Services;
import org.net.erp.repository.CategoryRepository;
import org.net.erp.repository.MasterRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private CategoryService categoryService;

	/*
	 * @Autowired private ClientBO clientBO;
	 */

	@Autowired
	private MasterRepository masterRepo;
	
	@PostMapping(Constants.EMPTY)
	public String createCategory(@Valid @ModelAttribute(Constants.CATEGORY_FORM) Category category,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(key);
				category.setOrganization(master);
				categoryRepo.save(category);				
			}
			model.addAttribute(Constants.CATEGORY_FORM,new Category());
			model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());
			model.addAttribute(Constants.SERVICE_FORM,new Services());
			model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR,new Services());
		}catch(Exception e) {
			
		}
		return Constants.SERVICES_JSP;
	}
	
	@GetMapping("/getAllCategories")
	public ResponseEntity<?> getAllCategories(HttpServletRequest request){
		int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
		Gson gson = new Gson();
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		gson = gb.setPrettyPrinting().create();
		String jsonValue = gson.toJson(categoryRepo.findByMasterId(key));
		return ResponseEntity.ok(jsonValue);
	}
}
