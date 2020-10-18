package org.net.erp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.net.erp.bo.BaseBO;
import org.net.erp.bo.UpgradeToProBO;
import org.net.erp.model.Master;
import org.net.erp.model.RegisterMember;
import org.net.erp.model.UpgradeToPro;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.UpgradeToProRepository;
import org.net.erp.services.PlanService;
import org.net.erp.services.RegisterMemberService;
import org.net.erp.util.Constants;
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
	private UpgradeToProBO upgradeToProBO;
	
	@RequestMapping("/dashboard")
	public String viewHomePage() {
		return "dashboard";
	}

	@RequestMapping("/pricing")
	public String viewPricingPage() {
		return "pricing";
	}
	
	@RequestMapping("/upgradeToProRequests")
	public String viewupgradeToProPage() {
		return "upgradeToProRequests";
	}
	

	@RequestMapping("/purchasePlan/{id}")
	public ResponseEntity<?> purchasePlan(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		try {
			int organizationId = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			int memberId = (int) request.getSession().getAttribute(Constants.SESSION_MEMBERID);
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
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getAllRequests")
	public ResponseEntity<?> getAllClients(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String jsonValue = null;
		if(null != request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY)) {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = upgradeToProBO.parseFetchRequest(upgradeToProRepository.findByRecordId(id));
		}
		return ResponseEntity.ok(jsonValue);
	}
	
	@RequestMapping("/getAllPlans")
	public ResponseEntity<?> getAllPlans() {
		return ResponseEntity.ok(planService.listAll());
	}

	@RequestMapping("/")
	public String showLandingPage() {
		return "landing-page";
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
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

}