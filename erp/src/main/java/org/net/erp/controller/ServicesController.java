package org.net.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServicesController {

	 @RequestMapping("/services")
	    public String showAppointments() {
	        return "services";
	    }
	 
	 @RequestMapping("/services/getAllServices")
	    public ResponseEntity<?> getServices() {
	    	String value = "{\r\n" + 
	    			"	\"Hair\": [\"Massage\", \"Haircut\"],\r\n" + 
	    			"	\"Body\": [\"Massage\"]\r\n" + 
	    			"}";    	
	        return ResponseEntity.ok(value);
	    }
	 
}
