package org.net.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaffController {

	 @RequestMapping("/staff")
	    public String showAppointments() {
	        return "staff";
	    }
	 
	 @RequestMapping("/staff/getAllStaff")
	    public ResponseEntity<?> getAllStaff() {
		 	String value = "";
	        return ResponseEntity.ok(value);
	    }
}
