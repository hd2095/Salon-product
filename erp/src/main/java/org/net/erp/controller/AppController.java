package org.net.erp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.net.erp.bo.BaseBO;
import org.net.erp.bo.RegisterMemberBO;
import org.net.erp.bo.UpgradeToProBO;
import org.net.erp.model.Master;
import org.net.erp.model.Plan;
import org.net.erp.model.RegisterMember;
import org.net.erp.model.UpgradeToPro;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.UpgradeToProRepository;
import org.net.erp.services.PlanService;
import org.net.erp.services.RegisterMemberService;
import org.net.erp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@Autowired
	private PlanService planService;

	@Autowired
	private UpgradeToProRepository upgradeToProRepository;

	@Autowired
	private RegisterMemberService registerMemberService;

	@Autowired
	private MasterRepository masterRepo;

	@Autowired
	BaseBO baseBO;

	@Autowired
	RegisterMemberBO registerMemberBO;

	@Autowired
	private UpgradeToProBO upgradeToProBO;

	private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);


	@RequestMapping("/")
	public String showLandingPage(HttpServletRequest request) {
		String returnValue = null;
		try {
			returnValue = "landing-page";
		}catch(Exception e) {
			LOGGER.error("Exception in showLandingPage :: "+e.getMessage());
		}
		return returnValue;
	}

	@RequestMapping("/dashboard")
	public String viewHomePage(HttpServletRequest request) {		
		String returnValue = null;
		try {
			returnValue = "dashboard";
		}catch(Exception e) {
			LOGGER.error("Exception in viewHomePage :: "+e.getMessage());
		}
		return returnValue;
	}

	@RequestMapping("/pricing")
	public String viewPricingPage() {
		String returnValue = null;
		try {
			returnValue = "pricing";
		}catch(Exception e) {
			LOGGER.error("Exception in viewPricingPage :: "+e.getMessage());
		}
		return returnValue;
	}

	@RequestMapping("/upgradeToProRequests")
	public String viewupgradeToProPage() {
		String returnValue = null;
		try {
			returnValue = "upgradeToProRequests";
		}catch(Exception e) {
			LOGGER.error("Exception in viewupgradeToProPage :: "+e.getMessage());
		}
		return returnValue;
	}

	@RequestMapping("/clientDetails")
	public String viewClientDetails() {
		String returnValue = null;
		try {
			returnValue = "clientDetails";
		}catch(Exception e) {
			LOGGER.error("Exception in viewClientDetails :: "+e.getMessage());
		}
		return returnValue;
	}

	@RequestMapping("/getAllMembers")
	public ResponseEntity<?> getAllMembers() {
		String json = null;
		try {
			List<RegisterMember> allMembers = registerMemberService.findAll();
			json = registerMemberBO.parseAllMembers(allMembers);	
		}catch(Exception e) {
			LOGGER.error("Exception in getAllMembers :: "+e.getMessage());
		}
		return ResponseEntity.ok(json);
	}

	@RequestMapping("/purchasePlan/{id}")
	public ResponseEntity<?> purchasePlan(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		int organizationId = 0;
		int memberId = 0;
		try {
			organizationId = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			memberId = (int) request.getSession().getAttribute(Constants.SESSION_MEMBERID);
			Master master = masterRepo.findByMasterId(organizationId);
			RegisterMember rm = registerMemberService.findUserByClientId(memberId);
			UpgradeToPro utp = new UpgradeToPro();
			if(null != master && null != rm) {		
				if(id == 1) {
					utp.setPlanName("Standard");
				}else {
					utp.setPlanName("Premium");
				}
				utp.setClientFn(rm.getFirst_name());
				utp.setClientLn(rm.getLast_name());
				utp.setClientNumber(rm.getMobileNumber());
				utp.setClientOrgName(master.getOrganizationName());
				utp.setRequestedDate(new Date());				
				upgradeToProRepository.saveAndFlush(utp);
			}
			if(null == upgradeToProRepository.findById(utp.getRecordId())) {
				jsonValue = baseBO.setDeleteOperationStatus(false);
			}else {
				jsonValue = baseBO.setDeleteOperationStatus(true);
			}
		}catch(Exception e) {
			LOGGER.error("Exception in purchasePlan for organization id " + organizationId + " :: for client id :: " + memberId + " :: "+e.getMessage());
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getAllRequests")
	public ResponseEntity<?> getAllClients(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String jsonValue = null;
		int id = 0;
		try {
			if(null != request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY)) {
				id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				jsonValue = upgradeToProBO.parseFetchRequest(upgradeToProRepository.findByRecordId(id));
			}
		}catch(Exception e) {
			LOGGER.error("Exception in getAllClients for organization id " + id + " :: "+e.getMessage());
		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getAllPlans")
	public ResponseEntity<?> getAllPlans() {
		List<Plan> plans = null;
		try {
			plans = planService.listAll();
		}catch(Exception e) {
			LOGGER.error("Exception in getAllPlans :: "+e.getMessage());
		}
		return ResponseEntity.ok(plans);
	}

	@RequestMapping("/setLocalStorage")
	public ResponseEntity<?> setLocalStorage(HttpServletRequest request) {
		String value = null;
		int memberId = 0;
		try {
			if(null != request.getSession().getAttribute(Constants.SESSION_MEMBERID)) {
				memberId = (int) request.getSession().getAttribute(Constants.SESSION_MEMBERID);
				RegisterMember rm = registerMemberService.findUserByClientId(memberId);
				if(null != rm) 
					value = String.valueOf(rm.getMember_id());
			}
		}catch(Exception e) {
			LOGGER.error("Exception in setLocalStorage for user id :: "+ memberId +" :: "+e.getMessage());
		}
		return ResponseEntity.ok(value);	
	}

	@GetMapping("/deleteRequest/{id}")
	public ResponseEntity<?> deleteRequest(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			Optional<UpgradeToPro> utp = upgradeToProRepository.findById(id);
			if(null != utp) {
				upgradeToProRepository.deleteById(id);
			}
			if(null == upgradeToProRepository.findById(id)) {
				jsonValue = baseBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = baseBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			LOGGER.error("Exception in deleteRequest for request id :: "+ id +" :: "+e.getMessage());
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("upgradeClientPlan/{id}/{plan}")
	public ResponseEntity<?> upgradeClientPlan(@PathVariable(value = "id") int id,@PathVariable(value = "plan") String plan) {
		String jsonValue = null;
		try {
			RegisterMember rm = registerMemberService.findUserByClientId(id);
			Master master = masterRepo.findByMasterId(rm.getRegisterOrganization().getMaster_id());
			master.setOrganizationPlan(plan);
			masterRepo.save(master);
			jsonValue = baseBO.setDeleteOperationStatus(true);
		}catch(Exception e) {
			jsonValue = baseBO.setDeleteOperationStatus(false);
			LOGGER.error("Exception in upgradeClientPlan for user id :: "+ id +" :: "+e.getMessage());
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

}