package org.net.erp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.model.Master;
import org.net.erp.model.RegisterMember;
import org.net.erp.repository.MasterRepository;
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
public class ProfileCreationController {

	@Autowired
	private RegisterMemberService registerMemberService;

	@Autowired
	private MasterRepository masterRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/profileCreation")
	public String showAppointments(Model model,HttpServletRequest request) {
		int clientId = (int) request.getSession().getAttribute(Constants.SESSION_CLIENTID);
		RegisterMember rm = registerMemberService.findUserByClientId(clientId);
		model.addAttribute(Constants.UPDATE_PROFILE,rm);
		return Constants.PROFILE_CREATION_JSP;
	}

	@PostMapping("/updateProfile")
	public String updateProfile(@Valid @ModelAttribute(Constants.UPDATE_PROFILE) RegisterMember rm,BindingResult bindingResult,HttpServletRequest request,Model model) {
		if(!bindingResult.hasErrors()) {
			int clientId = (int) request.getSession().getAttribute(Constants.SESSION_CLIENTID);
			RegisterMember member = registerMemberService.findUserByClientId(clientId);
			if(null != request.getParameter("confirmPassword") && Constants.EMPTY != request.getParameter("confirmPassword")) {
				rm.setMemberPassword(bCryptPasswordEncoder.encode(request.getParameter("confirmPassword")));				
			}
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master ro = masterRepo.findByMasterId(key);
			rm.setCreated_on(member.getCreated_on());
			rm.setExpires_on(member.getExpires_on());
			rm.setGstn_percent(member.getGstn_percent());
			rm.setMember_status(member.getMember_status());
			rm.setMember_type(member.getMember_type());
			rm.setMember_photo(member.getMember_photo());
			rm.setMember_id(clientId);
			rm.setRegisterOrganization(member.getRegisterOrganization());
			rm.setRegisterOrganization(ro);
			registerMemberService.save(rm);
			model.addAttribute(Constants.UPDATE_PROFILE,rm);
		}
		return Constants.REDIRECT + Constants.PROFILE_CREATION_JSP;
	}

}
