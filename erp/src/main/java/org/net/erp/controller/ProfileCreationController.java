
package org.net.erp.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.BaseBO;
import org.net.erp.model.ClientPlan;
import org.net.erp.model.Master;
import org.net.erp.model.RegisterMember;
import org.net.erp.model.RegisterOrganization;
import org.net.erp.repository.ClientPlanRepository;
import org.net.erp.repository.MasterRepository;
import org.net.erp.services.ClientPlanService;
import org.net.erp.services.RegisterMemberService;
import org.net.erp.services.RegisterOrganizationService;
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
	private RegisterOrganizationService registerOrganizationService;

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
		try {
			int clientId = (int) request.getSession().getAttribute(Constants.SESSION_MEMBERID);
			RegisterMember rm = registerMemberService.findUserByClientId(clientId);
			model.addAttribute(Constants.UPDATE_PROFILE,rm);	
		}catch(Exception e) {
			System.out.println("Exception in showProfile :: "+e.getMessage());
		}
		return Constants.PROFILE_CREATION_JSP; 
	}

	@GetMapping("/membership")
	public String showMembership(Model model,HttpServletRequest request) {
		try {
			int masterId = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			List<ClientPlan> allPlans = clientPlanRepository.findByMasterId(masterId);
			model.addAttribute(Constants.CLIENT_PLAN_LIST, allPlans);	
		}catch(Exception e) {
			System.out.println("Exception in showMembership :: "+e.getMessage());
		}
		model.addAttribute(Constants.MEMBERSHIP_FORM, new ClientPlan());
		model.addAttribute(Constants.EDIT_MEMBERSHIP_FORM, new ClientPlan());
		return Constants.MEMBERSHIP_CREATION_JSP; 
	}

	@GetMapping("/organization")
	public String showOrganization(Model model,HttpServletRequest request) {
		try {
			int masterId = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Optional<RegisterOrganization> org = registerOrganizationService.getOrganization(masterId);	
			model.addAttribute(Constants.CLIENT_ORGANIZATION_FORM, org);
		}catch(Exception e) {
			System.out.println("Exception in showOrganization :: "+e.getMessage());
		}
		return Constants.ORGANIZATION_CREATION_JSP; 
	}

	@PostMapping("/updateClientOrganization")
	public String updateClientOrganization(@Valid @ModelAttribute(Constants.CLIENT_ORGANIZATION_FORM) RegisterOrganization ro,RedirectAttributes ra,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int masterId = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				registerOrganizationService.save(ro);
				Optional<RegisterOrganization> org = registerOrganizationService.getOrganization(masterId);
				model.addAttribute(Constants.CLIENT_ORGANIZATION_FORM, org);	
				String message = "Organization updated successfully";
				ra.addFlashAttribute(Constants.SUCCESSFULLY_UPDATED, message); 
			}		
		}catch(Exception e) {
			System.out.println("Exception in updateClientOrganization :: "+e.getMessage());
		}	
		return Constants.REDIRECT_ORGANIZATION_CREATION_JSP; 
	}
	@GetMapping("/editMembership/{id}")
	public ResponseEntity<?> editMembership(@PathVariable(value = "id") int id,Model model) {
		String jsonValue = null;	
		try {
			ClientPlan plan = clientPlanService.getClientPlanById(id);
			Gson gson = null;
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			jsonValue = gson.toJson(plan);	
		}catch(Exception e) {
			System.out.println("Exception in editMembership :: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue); 
	}

	@PostMapping("/editMembership/{id}")
	public String updateMembership(@ModelAttribute(Constants.EDIT_MEMBERSHIP_FORM) ClientPlan clientPlan,@PathVariable(value = "id") int id,Model model,HttpServletRequest request) {
		try {
			int masterId = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master ro = masterRepo.findByMasterId(masterId);
			clientPlan.setOrganization(ro);
			clientPlan.setPlanStatus(Constants.ACTIVE_STATUS);
			clientPlanRepository.save(clientPlan);
			List<ClientPlan> allPlans = clientPlanRepository.findByMasterId(masterId);
			model.addAttribute(Constants.CLIENT_PLAN_LIST, allPlans);	
		}catch(Exception e) {
			System.out.println("Exception in updateMembership :: "+e.getMessage());
		}
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
		try {
			int masterId = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master ro = masterRepo.findByMasterId(masterId);
			clientPlan.setOrganization(ro);
			clientPlan.setPlanStatus(Constants.ACTIVE_STATUS);
			clientPlanRepository.save(clientPlan);
			List<ClientPlan> allPlans = clientPlanRepository.findByMasterId(masterId);
			model.addAttribute(Constants.CLIENT_PLAN_LIST, allPlans);	
		}catch(Exception e) {
			System.out.println("Exception in addMembership :: "+e.getMessage());
		}
		model.addAttribute(Constants.MEMBERSHIP_FORM, new ClientPlan());
		model.addAttribute(Constants.EDIT_MEMBERSHIP_FORM, new ClientPlan());
		return Constants.REDIRECT + Constants.MEMBERSHIP_CREATION_JSP; 
	}

	@PostMapping("/updateProfile")
	public String updateProfile(@Valid @ModelAttribute(Constants.UPDATE_PROFILE) RegisterMember rm,RedirectAttributes ra,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			int clientId = (int) request.getSession().getAttribute(Constants.SESSION_MEMBERID);
			RegisterMember	member = registerMemberService.findUserByClientId(clientId); 
			if(null != request.getParameter("confirmPassword") && Constants.EMPTY != request.getParameter("confirmPassword")) {
				member.setMemberPassword(bCryptPasswordEncoder.encode(request.getParameter("confirmPassword")));
			} 
			member.setFirst_name(rm.getFirst_name());
			member.setLast_name(rm.getLast_name());
			registerMemberService.save(member);
			model.addAttribute(Constants.UPDATE_PROFILE,member); 
			String message = "profile updated successfully";
			ra.addFlashAttribute(Constants.SUCCESSFULLY_UPDATED, message); 
		}catch(Exception e) {
			System.out.println("Exception in updateProfile :: "+e.getMessage());
		}
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
			System.out.println("Exception in getAllCategories :: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue);
	}

}