package org.net.erp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.model.Appointment;
import org.net.erp.model.Product;
import org.net.erp.services.PlanService;
import org.net.erp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
	 
	
    @Autowired
    private ProductService service;
    
    @Autowired
    private PlanService planService;
       
    @RequestMapping("/side-nav")
    public String showNewProductPage() {
    	return "layout/nav-bar";
    }
    
    @RequestMapping("/metronic")
    public String showMetronicPage() {
    	return "index";
    }
    
    @RequestMapping("/calendar-page")
    public String showCalendarPage() {
    	return "background-events";
    }
    
    @RequestMapping("/list-default")
    public String showDefaultList() {
        return "list-default";
    }
    
    @PostMapping("/register")
    public String registerProduct(@Valid @ModelAttribute("appointment") Appointment appointment,BindingResult result,HttpServletRequest request) {
        return "submit";
    }
    
    @PostMapping("/setAppointment")
    public String setAppointment(@ModelAttribute("appointment") Appointment appointment,HttpServletRequest request) {
        return "submit";
    }
       
    @RequestMapping("/list-datatable")
    public String showDataTableList() {
        return "list-datatable";
    }
     
    @RequestMapping("/dashboard")
    public String viewHomePage() {
    	return "dashboard";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);        
        return "redirect:/";
    }
        
    @RequestMapping("/register-user")
    public String userRegisteration() {
    	return "registeration";
    }
    
    @RequestMapping("/displayOrganizationRegisteration")
    public String displayOrganizationRegistrationForm() {
    	return "display/display-organization-registration-form";
    }
    
    @RequestMapping("/getAllPlans")
    public ResponseEntity<?> getAllPlans() {
        return ResponseEntity.ok(planService.listAll());
    }
  
}