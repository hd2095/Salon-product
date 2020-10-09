package org.net.erp.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.ServiceBO;
import org.net.erp.model.Category;
import org.net.erp.model.Master;
import org.net.erp.model.Services;
import org.net.erp.repository.AppointmentDetailsRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@Autowired
	private AppointmentDetailsRepository appointmentDetailsRepo;
	
	@GetMapping(Constants.EMPTY)
	public String showServices(Model model,HttpServletRequest request) {
		try {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);		
			Map<Category,List<Services>> mapToDisplay = serviceBO.serviceWithCategory(key);
			model.addAttribute(Constants.CATEGORY_FORM,new Category());
			model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());
			model.addAttribute(Constants.SERVICE_FORM,new Services());
			model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR,new Services());
			model.addAttribute(Constants.SERVICES_MAP, mapToDisplay);
			if(null != request.getParameter("showDetails")) {
				model.addAttribute("showServiceDetails",request.getParameter("showDetails"));
			}	
		}catch(Exception e) {
			System.out.print("Error in showServices :: "+e.getMessage());
		}
		return Constants.SERVICES_JSP;
	}

	@RequestMapping("/getAllServices")
	public ResponseEntity<?> getServices(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Map<String,List<Services>> mapToDisplay = serviceBO.serviceWithCategoryJson(key);
			jsonValue = serviceBO.parseFetchService(mapToDisplay);
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getMostAvailedService")
	public ResponseEntity<?> getTopServices(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			List<String> details = appointmentDetailsRepo.getMostUsedService(id);		
			List<Services> topServices = new LinkedList<Services>();
			for(String temp : details) {
				topServices.add(service.getServiceById(Integer.parseInt(temp.split(",")[0])));
			}
			jsonValue = serviceBO.parseFetchService(topServices);
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/create")
	public String createService(@Valid @ModelAttribute(Constants.SERVICE_FORM) Services service,RedirectAttributes ra,BindingResult bindingResult,HttpServletRequest request, Model model) {		
		String returnString = Constants.EMPTY;
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				List<String> existingService = serviceRepo.getServiceByCategoryName(service.getCategory().getCategoryId(),key);
				if(null != existingService) {
					for(String temp : existingService) {
						if(temp.equalsIgnoreCase(service.getServiceName())) {
							String message = "Service " + service.getServiceName() + " already exists under category "+service.getCategory().getCategoryName();
							ra.addFlashAttribute(Constants.EXISTING_SERVICE,message);					
							return Constants.REDIRECT_SERVICES;
						}
					}
				}	
				Master master = masterRepo.findByMasterId(key);
				service.setOrganization(master);
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
			Map<String,Services> serviceMap = new HashMap<String,Services>();
			serviceMap.put(Constants.DATA_KEY, services);
			jsonValue = serviceBO.parseService(serviceMap);			
		}catch(Exception e) {

		}
		model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR, services);
		model.addAttribute(Constants.CATEGORY_FORM,new Category());
		model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());
		model.addAttribute(Constants.SERVICE_FORM,new Services());
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/editService/{id}")
	public String updateService(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_SERVICE_FORM_ATTR) Services services,RedirectAttributes ra,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try{
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);		
			Services fetchedService = serviceRepo.getServiceByName(services.getServiceName(), key);
			if(null == fetchedService) {
				Master master = masterRepo.findByMasterId(key);
				services.setOrganization(master);
				services.setServiceStatus(Constants.ACTIVE_STATUS);
				int categoryId = Integer.parseInt(request.getParameter("edit_service_categoryId"));
				Category category = categoryService.getCategoryById(categoryId);
				services.setCategory(category);
				service.save(services);
			}else {
				if(fetchedService.getServiceId() == services.getServiceId()) {
					services.setOrganization(fetchedService.getOrganization());
					services.setServiceStatus(Constants.ACTIVE_STATUS);
					services.setCategory(fetchedService.getCategory());
					service.save(services);
				}else {
					String message = "Service " + services.getServiceName() + " already exists under category "+fetchedService.getCategory().getCategoryName();
					ra.addFlashAttribute(Constants.EXISTING_EDIT_SERVICE,message);	
					ra.addFlashAttribute("editServiceId",services.getServiceId());	
					return Constants.REDIRECT_SERVICES;
				}				
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.CATEGORY_FORM,new Category());
		model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());
		model.addAttribute(Constants.SERVICE_FORM,new Services());
		return Constants.REDIRECT_SERVICES;
	}
}
