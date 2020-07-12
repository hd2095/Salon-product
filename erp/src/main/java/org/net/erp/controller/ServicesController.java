package org.net.erp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.ServiceBO;
import org.net.erp.model.Category;
import org.net.erp.model.Master;
import org.net.erp.model.Services;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.ServiceRepository;
import org.net.erp.services.CategoryService;
import org.net.erp.services.ServiceOperations;
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
@RequestMapping("/services")
public class ServicesController {

	@Autowired
	private ServiceRepository serviceRepo;

	@Autowired
	private ServiceOperations service;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ServiceBO serviceBO;

	@Autowired
	private MasterRepository masterRepo;

	@GetMapping(Constants.EMPTY)
	public String showAppointments(Model model) {
		model.addAttribute(Constants.CATEGORY_FORM,new Category());
		model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());
		model.addAttribute(Constants.SERVICE_FORM,new Services());
		model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR,new Services());
		return Constants.SERVICES_JSP;
	}

	@RequestMapping("/getAllServices")
	public ResponseEntity<?> getServices(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = serviceBO.parseFetchService(serviceRepo.findByMasterId(id));
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/create")
	public String createService(@Valid @ModelAttribute(Constants.SERVICE_FORM) Services service,BindingResult bindingResult,HttpServletRequest request, Model model) {		
		String hours = "h";
		String minutes = "min";
		String seconds = "secs";
		String returnString = Constants.EMPTY;
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(key);
				service.setOrganization(master);
				String[] formatDuration = service.getServiceDuration().split(Constants.COLON);
				String formattedDuration = "";
				for(int i = 0;i<formatDuration.length;i++) {
					if(i == 0) {
						if(!formatDuration[i].equalsIgnoreCase("0")) {
							formattedDuration += formatDuration[i] + Constants.SPACE + hours + Constants.SPACE;
						}
					}else if(i == 1) {
						if(!formatDuration[i].equalsIgnoreCase("00")) {
							formattedDuration += formatDuration[i] + Constants.SPACE + minutes + Constants.SPACE;
						}
					}else {
						if(!formatDuration[i].equalsIgnoreCase("00")) {
							formattedDuration += formatDuration[i] + Constants.SPACE + seconds;
						}
					}
				}
				service.setServiceDuration(formattedDuration);
				service.setServiceStatus(Constants.ACTIVE_STATUS);
				serviceRepo.save(service);	
				model.addAttribute(Constants.SERVICE_FORM,new Services());
				returnString = Constants.REDIRECT_SERVICES;
			}else {
				returnString = Constants.SERVICES_JSP;				
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.CATEGORY_FORM,new Category());
		model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());		
		model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR,new Services());
		return returnString;
	}

	@GetMapping("/deleteService/{id}")
	public ResponseEntity<?> deleteService(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		try {
			Services services = service.getServiceById(id);
			services.setServiceStatus(Constants.INACTIVE_STATUS);
			service.save(services);
			if(Constants.INACTIVE_STATUS == service.getServiceById(id).getServiceStatus()) {
				jsonValue = serviceBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = serviceBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}	

	@GetMapping("/editService/{id}")
	public ResponseEntity<?> editService(@PathVariable(value = "id") int id,Model model) {
		String jsonValue = null;
		Services services = null;
		try {
			services = this.service.getServiceById(id);
			List<Services> serviceDetails = new ArrayList<Services>();
			serviceDetails.add(services);
			jsonValue = serviceBO.parseFetchService(serviceDetails);			
		}catch(Exception e) {

		}
		model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR, services);
		model.addAttribute(Constants.CATEGORY_FORM,new Category());
		model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());
		model.addAttribute(Constants.SERVICE_FORM,new Services());
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/editService/{id}")
	public String updateService(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_SERVICE_FORM_ATTR) Services services,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try{
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = masterRepo.findByMasterId(key);
			services.setOrganization(master);
			services.setServiceStatus(Constants.ACTIVE_STATUS);
			int categoryId = Integer.parseInt(request.getParameter("edit_service_categoryId"));
			Category category = categoryService.getCategoryById(categoryId);
			services.setCategory(category);
			service.save(services);
		}catch(Exception e) {

		}
		model.addAttribute(Constants.CATEGORY_FORM,new Category());
		model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());
		model.addAttribute(Constants.SERVICE_FORM,new Services());
		model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR,new Services());
		return Constants.REDIRECT_SERVICES;
	}
}
