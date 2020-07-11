package org.net.erp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.ServiceBO;
import org.net.erp.model.Category;
import org.net.erp.model.Master;
import org.net.erp.model.Services;
import org.net.erp.repository.CategoryRepository;
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
		int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
		String jsonValue = serviceBO.parseFetchService(serviceRepo.findByMasterId(id));
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/create")
	public String createService(@Valid @ModelAttribute(Constants.SERVICE_FORM) Services service,BindingResult bindingResult,HttpServletRequest request, Model model) {
		String hours = "h";
		String minutes = "min";
		String seconds = "secs";
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
				service.setServiceCost(Constants.RUPPEE_SYMBOL+service.getServiceCost());
				serviceRepo.save(service);	
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.CATEGORY_FORM,new Category());
		model.addAttribute(Constants.EDIT_CATEGORY_FORM_ATTR,new Category());
		model.addAttribute(Constants.SERVICE_FORM,new Services());
		model.addAttribute(Constants.EDIT_SERVICE_FORM_ATTR,new Services());
		return Constants.REDIRECT + Constants.SERVICES_JSP;
	}
	
	@GetMapping("/deleteService/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			service.deleteService(id);
			if(null == service.getServiceById(id)) {
				jsonValue = serviceBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = serviceBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}	
}
