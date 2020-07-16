package org.net.erp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.model.LoginMember;
import org.net.erp.model.RegisterMember;
import org.net.erp.services.RegisterMemberService;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RegisterMemberService registerMemberService;


	@GetMapping("/")
	public String showLoginPage(Model model) {
		model.addAttribute(Constants.LOGIN_MEMBER, new LoginMember());      
		return Constants.LOGIN_JSP;
	}

	@PostMapping("/")
	public String handleLogin(@Valid @ModelAttribute(Constants.LOGIN_MEMBER) LoginMember loginMember,BindingResult bindingResult,HttpServletRequest request) {
		RegisterMember registerMember = null;
		if(bindingResult.hasErrors()) {
			return Constants.LOGIN_JSP;	
		}else {
			if(loginMember.getUsername().contains(Constants.AT) && loginMember.getUsername().contains(Constants.DOT)) {
				registerMember = registerMemberService.findUserByEmail(loginMember.getUsername());
			}else {
				registerMember = registerMemberService.findUserByMobileNumber(loginMember.getUsername());	
			}	 
			if(registerMember != null) {
				if(bCryptPasswordEncoder.matches(loginMember.getPassword(), registerMember.getMemberPassword())) {
					request.getSession().setAttribute(Constants.SESSION_FIRTSNAME,registerMember.getFirst_name());
					request.getSession().setAttribute(Constants.SESSION_ORGANIZATION_KEY,registerMember.getRegisterOrganization().getMaster_id());
					return Constants.DASHBOARD_JSP	;
				}
				else {
					bindingResult.rejectValue(Constants.PASSWORD, null, Constants.INCORRECT_MEMBER_PASSWORD);
					return Constants.LOGIN_JSP;
				}
			}else {
				bindingResult.rejectValue(Constants.USERNAME, null, Constants.MEMBER_NOT_FOUND);
				return Constants.LOGIN_JSP;
			}    		
		}	
	}
	
	@GetMapping("/invalidate")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

}
