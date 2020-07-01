package org.net.erp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.model.Client;
import org.net.erp.model.Master;
import org.net.erp.repository.ClientRepository;
import org.net.erp.repository.MasterRepository;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private MasterRepository masterRepo;
	
	@GetMapping(Constants.EMPTY)
	public String showAppointments(Model model) {
		model.addAttribute(Constants.CLIENT_FORM, new Client());
		return Constants.CLIENT_JSP;
	}
	
	@PostMapping(Constants.EMPTY)
	public String handleClientForm(@Valid @ModelAttribute(Constants.CLIENT_FORM) Client client,BindingResult bindingResult,HttpServletRequest request) {
		if(bindingResult.hasErrors()) {
			return Constants.CLIENT_JSP;	
		}else {			
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = masterRepo.findByMasterId(key);
			client.setRegisterOrganization(master);
			clientRepo.save(client);
			return Constants.CLIENT_JSP;
		}	
	}

	@RequestMapping("/getAllClients")
	public ResponseEntity<?> getAllClients() {		
		String value = "{\r\n" + 
				"	\"meta\": {\r\n" + 
				"		\"page\": 1,\r\n" + 
				"		\"pages\": 1,\r\n" + 
				"		\"perpage\": -1,\r\n" + 
				"		\"total\": 350,\r\n" + 
				"		\"sort\": \"asc\",\r\n" + 
				"		\"field\": \"RecordID\"\r\n" + 
				"	},\r\n" + 
				"	\"data\": [{\r\n" + 
				"			\"RecordID\": 1,\r\n" + 
				"			\"ClientName\": \"Hardik Desai\",\r\n" + 
				"			\"MobileNumber\": \"9702809177\",\r\n" + 
				"			\"Email\": \"hardik.desai@atos.net\",\r\n" + 
				"			\"Gender\": \"Male\",\r\n" + 
				"			\"RevenueGenerated\": \"10000\",\r\n" + 
				"			\"Actions\": null\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"RecordID\": 2,\r\n" + 
				"			\"ClientName\": \"Vinay Chavhan\",\r\n" + 
				"			\"MobileNumber\": \"898521111\",\r\n" + 
				"			\"Email\": \"vincee@atos.net\",\r\n" + 
				"			\"Gender\": \"Male\",\r\n" + 
				"			\"RevenueGenerated\": \"10000\",\r\n" + 
				"			\"Actions\": null\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"RecordID\": 3,\r\n" + 
				"			\"ClientName\": \"Kv Aj\",\r\n" + 
				"			\"MobileNumber\": \"9711544949\",\r\n" + 
				"			\"Email\": \"kv@atos.net\",\r\n" + 
				"			\"Gender\": \"Male\",\r\n" + 
				"			\"RevenueGenerated\": \"1000\",\r\n" + 
				"			\"Actions\": null\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}";
		return ResponseEntity.ok(value);
	}
}
