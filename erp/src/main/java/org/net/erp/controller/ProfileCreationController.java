
package org.net.erp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.BaseBO;
import org.net.erp.model.ClientPlan;
import org.net.erp.model.Master;
import org.net.erp.model.RegisterMember;
import org.net.erp.repository.ClientPlanRepository;
import org.net.erp.repository.MasterRepository;
import org.net.erp.services.ClientPlanService;
import org.net.erp.services.RegisterMemberService;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller 
public class ProfileCreationController {

	@Autowired 
	private RegisterMemberService registerMemberService;

	@Autowired 
	private BaseBO baseBO;

	@Autowired 
	private ClientPlanRepository clientPlanRepository;

	@Autowired 
	private ClientPlanService clientPlanService;

	@Autowired 
	private MasterRepository masterRepo;

	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/profileCreation")
	public String showProfile(Model model,HttpServletRequest request) {
		int clientId = (int) request.getSession().getAttribute(Constants.SESSION_CLIENTID);
		RegisterMember rm = registerMemberService.findUserByClientId(clientId);
		model.addAttribute(Constants.UPDATE_PROFILE,rm);
		return Constants.PROFILE_CREATION_JSP; 
	}

	@GetMapping("/membership")
	public String showMembership(Model model,HttpServletRequest request) {
		int masterId = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
		List<ClientPlan> allPlans = clientPlanRepository.findByMasterId(masterId);
		model.addAttribute(Constants.CLIENT_PLAN_LIST, allPlans);
		model.addAttribute(Constants.MEMBERSHIP_FORM, new ClientPlan());
		model.addAttribute(Constants.EDIT_MEMBERSHIP_FORM, new ClientPlan());
		return Constants.MEMBERSHIP_CREATION_JSP; 
	}

	@GetMapping("/editMembership/{id}")
	public ResponseEntity<?> editMembership(@PathVariable(value = "id") int id,Model model) {
		String jsonValue = null;		
		ClientPlan plan = clientPlanService.getClientPlanById(id);
		Gson gson = null;
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		gson = gb.setPrettyPrinting().create();
		jsonValue = gson.toJson(plan);
		return ResponseEntity.ok(jsonValue); 
	}
	
	@PostMapping("/editMembership/{id}")
	public String updateMembership(@ModelAttribute(Constants.EDIT_MEMBERSHIP_FORM) ClientPlan clientPlan,@PathVariable(value = "id") int id,Model model,HttpServletRequest request) {
		int masterId = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
		Master ro = masterRepo.findByMasterId(masterId);
		clientPlan.setOrganization(ro);
		clientPlan.setPlanStatus(Constants.ACTIVE_STATUS);
		clientPlanRepository.save(clientPlan);
		List<ClientPlan> allPlans = clientPlanRepository.findByMasterId(masterId);
		model.addAttribute(Constants.CLIENT_PLAN_LIST, allPlans);
		model.addAttribute(Constants.MEMBERSHIP_FORM, new ClientPlan());
		model.addAttribute(Constants.EDIT_MEMBERSHIP_FORM, new ClientPlan());
		return Constants.REDIRECT + Constants.MEMBERSHIP_CREATION_JSP;
	}
	
	@GetMapping("/deleteClientPlan/{id}")
	public ResponseEntity<?> deleteMembership(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			ClientPlan clientProduct = clientPlanService.getClientPlanById(id);
			clientProduct.setPlanStatus(Constants.INACTIVE_STATUS);
			clientPlanRepository.save(clientProduct);
			if(Constants.INACTIVE_STATUS == clientPlanService.getClientPlanById(id).getPlanStatus()) {
				jsonValue = baseBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = baseBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@PostMapping("/addMembership")
	public String addMembership(@ModelAttribute(Constants.MEMBERSHIP_FORM) ClientPlan clientPlan,Model model,HttpServletRequest request) {
		int masterId = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
		Master ro = masterRepo.findByMasterId(masterId);
		clientPlan.setOrganization(ro);
		clientPlan.setPlanStatus(Constants.ACTIVE_STATUS);
		clientPlanRepository.save(clientPlan);
		List<ClientPlan> allPlans = clientPlanRepository.findByMasterId(masterId);
		model.addAttribute(Constants.CLIENT_PLAN_LIST, allPlans);
		model.addAttribute(Constants.MEMBERSHIP_FORM, new ClientPlan());
		model.addAttribute(Constants.EDIT_MEMBERSHIP_FORM, new ClientPlan());
		return Constants.REDIRECT + Constants.MEMBERSHIP_CREATION_JSP; 
	}

	@PostMapping("/updateProfile")
	public String updateProfile(@Valid @ModelAttribute(Constants.UPDATE_PROFILE) RegisterMember rm,RedirectAttributes ra,BindingResult bindingResult,HttpServletRequest request,Model model) {
		int clientId = (int) request.getSession().getAttribute(Constants.SESSION_CLIENTID);
		RegisterMember	member = registerMemberService.findUserByClientId(clientId); 
		if(null != request.getParameter("confirmPassword") && Constants.EMPTY != request.getParameter("confirmPassword")) {
			rm.setMemberPassword(bCryptPasswordEncoder.encode(request.getParameter("confirmPassword")));
		} 
		int key = (int)	request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY); 
		Master ro = masterRepo.findByMasterId(key);
		rm.setCreated_on(member.getCreated_on());
		rm.setExpires_on(member.getExpires_on());
		rm.setEmailId(member.getEmailId());
		rm.setMobileNumber(member.getMobileNumber());
		rm.setGstn_percent(member.getGstn_percent());
		rm.setMember_status(member.getMember_status());
		rm.setMember_type(member.getMember_type());
		rm.setMember_photo(member.getMember_photo()); 
		rm.setMember_id(clientId);
		rm.setRegisterOrganization(member.getRegisterOrganization());
		rm.setRegisterOrganization(ro); 
		registerMemberService.save(rm);
		model.addAttribute(Constants.UPDATE_PROFILE,rm); 
		String message = "profile updated successfully";
		ra.addFlashAttribute(Constants.SUCCESSFULLY_UPDATED, message); 
		return Constants.REDIRECT + Constants.PROFILE_CREATION_JSP; 
	}

	@GetMapping("/getAllClientPlan")
	public ResponseEntity<?> getAllCategories(HttpServletRequest request){
		String jsonValue = null;
		try {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Gson gson = new Gson();
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			jsonValue = gson.toJson(clientPlanRepository.findByMasterId(key));
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

}