package org.net.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MarketingController {

	 @RequestMapping("/marketing")
	    public String showAppointments() {
	        return "marketing";
	    }
	 
}
