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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
 

	 
    @Autowired
    private ProductService service;
    
    @Autowired
    private PlanService planService;
       
    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }
    
    @RequestMapping("/list-default")
    public String showDefaultList() {
        return "list-default";
    }
    
    @PostMapping("/register")
    public String registerProduct(@Valid @ModelAttribute("appointment") Appointment appointment,BindingResult result,HttpServletRequest request) {
    	System.out.println(appointment.toString());
    	System.out.println(request.getParameter("InputDate"));
        return "submit";
    }
    
    @PostMapping("/setAppointment")
    public String setAppointment(@ModelAttribute("appointment") Appointment appointment,HttpServletRequest request) {
    	System.out.println(appointment.toString());
    	System.out.println(request.getParameter("InputDate"));
        return "submit";
    }
    
	/*
	 * @RequestMapping("/dashboard") public String showDashboard() { return
	 * "dashboard"; }
	 */

/*    @RequestMapping("/services")
    public ResponseEntity<?> getServices() {
    	String value = "{\r\n" + 
    			"	\"Hair\": [\"Massage\", \"Haircut\"],\r\n" + 
    			"	\"Body\": [\"Massage\"]\r\n" + 
    			"}";    	
        return ResponseEntity.ok(value);
    }*/
    
   
    
    @RequestMapping("/getClients")
    private ResponseEntity<?> getAllClients(){    	    	
    	String value = "{\r\n" + 
    			"	\"meta\": {\r\n" + 
    			"		\"page\": 1,\r\n" + 
    			"		\"pages\": 1,\r\n" + 
    			"		\"perpage\": -1,\r\n" + 
    			"		\"total\": 350,\r\n" + 
    			"		\"sort\": \"asc\",\r\n" + 
    			"		\"field\": \"ClientName\"\r\n" + 
    			"	},\r\n" + 
    			"	\"data\": [{\r\n" + 
    			"		\"ClientName\": \"Hardik Desai\",\r\n" + 
    			"		\"MobileNumber\": \"9702809177\",\r\n" + 
    			"		\"Email\": \"hardik.desai@atos.net\",\r\n" + 
    			"		\"Gender\": \"Male\",\r\n" + 
    			"		\"RevenueGenerated\": \"10000\"\r\n" + 
    			"	}, {\r\n" + 
    			"		\"ClientName\": \"Vinay Chavhan\",\r\n" + 
    			"		\"MobileNumber\": \"9819261259\",\r\n" + 
    			"		\"Email\": \"vincee7496@atos.net\",\r\n" + 
    			"		\"Gender\": \"Male\",\r\n" + 
    			"		\"RevenueGenerated\": \"10000\"\r\n" + 
    			"	}, {\r\n" + 
    			"		\"ClientName\": \"Kaivalya Ajgaonkar\",\r\n" + 
    			"		\"MobileNumber\": \"9167042245\",\r\n" + 
    			"		\"Email\": \"kv@atos.net\",\r\n" + 
    			"		\"Gender\": \"Male\",\r\n" + 
    			"		\"RevenueGenerated\": \"100\"\r\n" + 
    			"	}]\r\n" + 
    			"}";    	
        return ResponseEntity.ok(value);
    }
    

/*    @RequestMapping("/services")
    public String showServices() {
        return "services";
    }
*/    
    
   /* @RequestMapping("/client")
    public String showClient() {
    //public ResponseEntity<?> showClient() {
    	System.out.println("called");  
    	String calue = "{\r\n" + 
    			"	\"meta\": {\r\n" + 
    			"		\"page\": 1,\r\n" + 
    			"		\"pages\": 1,\r\n" + 
    			"		\"perpage\": -1,\r\n" + 
    			"		\"total\": 999,\r\n" + 
    			"		\"sort\": \"asc\",\r\n" + 
    			"		\"field\": \"RecordID\"\r\n" + 
    			"	},\r\n" + 
    			"	\"data\": [{\r\n" + 
    			"		\"RecordID\": 1,\r\n" + 
    			"		\"OrderID\": \"10819-4075\",\r\n" + 
    			"		\"ShipCountry\": \"CN\",\r\n" + 
    			"		\"ShipAddress\": \"9 Oxford Circle\",\r\n" + 
    			"		\"ShipName\": \"Cormier-Wolf\",\r\n" + 
    			"		\"TotalPayment\": \"$71700.74\",\r\n" + 
    			"		\"Status\": 4,\r\n" + 
    			"		\"Type\": 2,\r\n" + 
    			"		\"CustomerID\": 27\r\n" + 
    			"	}]\r\n" + 
    			"}";
    	//return ResponseEntity.ok(calue);
    return "/display/display-client-page";
    }*/
    
    @RequestMapping("/list-datatable")
    public String showDataTableList() {
        return "list-datatable";
    }
     
    @RequestMapping("/dashboard")
    public String viewHomePage() {
    	//model.addAttribute("appointment", new Appointment());      
      //  return "forms/new-appointment-form";
    	//return "display-client-page";
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
    
	/*
	 * @PostMapping("/createOrganization") public String createOrganization() {
	 * RegisterOrganization registerOrganization = new RegisterOrganization();
	 * this.registerOrganizationServie.save(registerOrganization); return
	 * "registerOrganization"; }
	 */
    
	/*
	 * @PostMapping("register-organizatiobn") public String
	 */
    
    @RequestMapping("/getAllPlans")
    public ResponseEntity<?> getAllPlans() {
        return ResponseEntity.ok(planService.listAll());
    }
  
}