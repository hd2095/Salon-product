
package org.net.erp.controller;

import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.net.erp.model.Master;
import org.net.erp.model.RegisterMember; 
import org.net.erp.model.RegisterOrganization; 
import org.net.erp.services.MasterService; 
import org.net.erp.services.RegisterMemberService; 
import org.net.erp.services.RegisterOrganizationService; 
import org.net.erp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.propertyeditors.CustomDateEditor; 
import org.springframework.http.ResponseEntity; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.WebDataBinder; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.InitBinder; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class RegisterController {

	@Autowired 
	private RegisterOrganizationService registerOrganizationServie;

	@Autowired 
	private MasterService masterService;

	@Autowired 
	private RegisterMemberService registerMemberServie;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

	@GetMapping("/member") 
	public String registerMember(Model model) {
		String returnValue = null;
		try {
			returnValue = Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_MEMBER_FORM_JSP;
			model.addAttribute(Constants.REGISTER_MEMBER_FORM, new RegisterMember());
		}catch(Exception e) {
			LOGGER.error("Exception in registerMember :: "+e.getMessage());
		}
		return returnValue;
	}

	@PostMapping("/member") 
	public String registerMember(@Valid @ModelAttribute(Constants.REGISTER_MEMBER_FORM) RegisterMember registerMember,BindingResult bindingResult) {
		String returnValue = null;
		try {
			if(bindingResult.hasErrors()) { 
				returnValue = Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_MEMBER_FORM_JSP; 
			}else {
				registerMember.setMemberPassword(bCryptPasswordEncoder.encode(registerMember.getMemberPassword())); 
				registerMemberServie.save(registerMember); 
				returnValue = "success"; 
			} 	
		}catch(Exception e) {
			LOGGER.error("Exception in registerMember overloaded :: "+e.getMessage());
		}
		return returnValue;
	}

	@GetMapping("/organization") 
	public String showRegisterOrgForm(Model model) {
		String returnValue = null;
		try {
			returnValue = Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_ORGANIZATION_FORM_JSP;
			model.addAttribute(Constants.REGISTER_ORGANIZATION_FORM,new RegisterOrganization()); 
		}catch(Exception e) {
			LOGGER.error("Exception in showRegisterOrgForm :: "+e.getMessage());
		}	
		return returnValue;
	}

	@PostMapping("/organization") 
	public String registerOrganization(@Valid @ModelAttribute(Constants.REGISTER_ORGANIZATION_FORM) RegisterOrganization registerOrganization,BindingResult bindingResult) {
		String returnValue = null;
		try {
			if(bindingResult.hasErrors()) { 
				returnValue = Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_ORGANIZATION_FORM_JSP; 
			}else {
				registerOrganization.setInvoiceNo(1);
				registerOrganizationServie.save(registerOrganization); 
				returnValue = "success"; 
			}	
		}catch(Exception e) {
			LOGGER.error("Exception in registerOrganization :: "+e.getMessage());
		}
		return returnValue;
	}


	@RequestMapping("/getAllOrganizations") 
	public ResponseEntity<?> getAllOrganizations() { 
		List<RegisterOrganization> regOrg = null;
		try {
			regOrg = registerOrganizationServie.listAll();
		}catch(Exception e) {
			LOGGER.error("Exception in getAllOrganizations :: "+e.getMessage());
		}
		return ResponseEntity.ok(regOrg); 
	}

	@RequestMapping("/getAllMasters") 
	public ResponseEntity<?> getAllMasters() {
		List<Master> allMaster = null;
		try {
			allMaster = masterService.listAll();
		}catch(Exception e) {
			LOGGER.error("Exception in getAllMasters :: "+e.getMessage());
		}
		return ResponseEntity.ok(allMaster); 
	}

	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_ONE); 
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true)); 
	}
}
