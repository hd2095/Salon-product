package org.net.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileCreation {

	 @RequestMapping("/profile-creation")
	    public String showAppointments() {
	        return "profileCreation";
	    }
}
