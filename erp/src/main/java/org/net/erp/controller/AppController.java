package org.net.erp.controller;

import org.net.erp.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	 
    @Autowired
    private PlanService planService;
         
    @RequestMapping("/dashboard")
    public String viewHomePage() {
    	return "dashboard";
    }
    
    @RequestMapping("/getAllPlans")
    public ResponseEntity<?> getAllPlans() {
        return ResponseEntity.ok(planService.listAll());
    }
  
    @RequestMapping("/")
    public String showLandingPage() {
    	return "landing-page";
    }
}