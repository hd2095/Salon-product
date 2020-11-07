
package org.net.erp.controller;

import java.text.SimpleDateFormat; 
import java.util.Date;
import javax.validation.Valid;
import org.net.erp.model.RegisterMember; 
import org.net.erp.model.RegisterOrganization; 
import org.net.erp.services.MasterService; 
import org.net.erp.services.RegisterMemberService; 
import org.net.erp.services.RegisterOrganizationService; 
import org.net.erp.util.Constants; 
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

	@GetMapping("/member") 
	public String registerMember(Model model) {
		model.addAttribute(Constants.REGISTER_MEMBER_FORM, new RegisterMember());
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_MEMBER_FORM_JSP;
	}

	@PostMapping("/member") 
	public String registerMember(@Valid @ModelAttribute(Constants.REGISTER_MEMBER_FORM)
	RegisterMember registerMember,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) { 
			return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_MEMBER_FORM_JSP; 
		}else {
			registerMember.setMemberPassword(bCryptPasswordEncoder.encode(registerMember.getMemberPassword())); 
			registerMemberServie.save(registerMember); 
			return "success"; 
		} 
	}

	@GetMapping("/organization") 
	public String showRegisterOrgForm(Model model) {
		model.addAttribute(Constants.REGISTER_ORGANIZATION_FORM,new RegisterOrganization()); 
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_ORGANIZATION_FORM_JSP; 
	}

	@PostMapping("/organization") 
	public String registerOrganization(@Valid @ModelAttribute(Constants.REGISTER_ORGANIZATION_FORM) RegisterOrganization registerOrganization,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) { 
			return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_ORGANIZATION_FORM_JSP; 
		}else {
			registerOrganization.setInvoiceNo(1);
			registerOrganizationServie.save(registerOrganization); 
			return "success"; 
		}
	}


	@RequestMapping("/getAllOrganizations") 
	public ResponseEntity<?> getAllOrganizations() { 
		return ResponseEntity.ok(registerOrganizationServie.listAll()); 
	}

	@RequestMapping("/getAllMasters") 
	public ResponseEntity<?> getAllMasters() {
		return ResponseEntity.ok(masterService.listAll()); 
	}

	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_ONE); 
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true)); 
	}
}
