package org.net.erp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.StaffBO;
import org.net.erp.model.Master;
import org.net.erp.model.Staff;
import org.net.erp.model.StaffDetails;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.StaffRepository;
import org.net.erp.services.StaffService;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private MasterRepository masterRepo;

	@Autowired
	private StaffService staffService;

	@Autowired
	private StaffRepository staffRepo;

	@Autowired
	private StaffBO staffBO;

	@GetMapping(Constants.EMPTY)
	public String showStaffPage(Model model) {
		try {
			model.addAttribute(Constants.STAFF_DETAILS_FORM,new StaffDetails());
		}catch(Exception e) {

		}
		return Constants.STAFF_JSP;
	}

	@PostMapping("/add")
	public String createStaff(@Valid @ModelAttribute(Constants.STAFF_FORM) Staff staff,RedirectAttributes ra,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Staff existingStaff = staffRepo.checkIfStaffExists(key, staff.getMobileNumber());
				if(null == existingStaff) {
					Master master = masterRepo.findByMasterId(key);
					staff.setOrganization(master);
					staff.setStaffStatus(Constants.ACTIVE_STATUS);
					staffRepo.save(staff);
					model.addAttribute(Constants.STAFF_FORM,new Staff());
				}else {
					String message = "Staff "+existingStaff.getFullName() + " has the same phone number "+staff.getMobileNumber();
					model.addAttribute(Constants.EXISTING_STAFF, message);
					return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_STAFF_FORM;
				}
			}else {
				return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_STAFF_FORM;
			}			 
		}catch(Exception e) {

		}
		return Constants.REDIRECT_STAFF;
	}

	@GetMapping("/add")
	public String displayCreateForm(Model model) {
		model.addAttribute(Constants.STAFF_FORM,new Staff());
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_STAFF_FORM;
	}

	@RequestMapping("/getAllStaff")
	public ResponseEntity<?> getAllStaff(HttpServletRequest request) {
		int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
		String jsonValue = staffBO.parseFetchStaff(staffRepo.findByMasterId(id));
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/getStaffByRevenue")
	public ResponseEntity<?> getStaffByRevenue(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = staffBO.parseFetchStaff(staffRepo.findByRevenue(id));
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/calculateCommission/{id}")
	public String calculateCommission(@ModelAttribute(Constants.STAFF_DETAILS_FORM) StaffDetails staffDetails,RedirectAttributes ra,HttpServletRequest request,Model model) {
		float commission = 0;
		try {
			commission = (staffDetails.getCommissionPercent() * staffDetails.getStaff().getRevenue_generated())/100;
		}catch(Exception e) {

		}
		model.addAttribute(Constants.STAFF_DETAILS_FORM,new StaffDetails());
		ra.addFlashAttribute(Constants.CALCULATED_COMMISSION,commission);
		return Constants.REDIRECT_STAFF;
	}

	@GetMapping("/deleteStaff/{id}")
	public ResponseEntity<?> deleteStaff(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			Staff staff = staffService.getStaffById(id);
			staff.setStaffStatus(Constants.INACTIVE_STATUS);
			staffService.save(staff);
			if(Constants.INACTIVE_STATUS == staffService.getStaffById(id).getStaffStatus()) {
				jsonValue = staffBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = staffBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/editStaff/{id}")
	public String editStaff(@PathVariable(value = "id") int id,Model model) {
		try {
			Staff staff = this.staffService.getStaffById(id);
			List<Staff> staffDetails = new ArrayList<Staff>();
			staffDetails.add(staff);
			staffBO.parseFetchStaff(staffDetails);
			model.addAttribute(Constants.EDIT_STAFF_FORM_ATTR, staff);	
		}catch(Exception e) {

		}
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.EDIT_STAFF_FORM;
	}

	@PostMapping("/editStaff/{id}")
	public String updateStaff(@PathVariable(value = "id") int id,RedirectAttributes ra,@Valid @ModelAttribute(Constants.EDIT_STAFF_FORM_ATTR) Staff staff,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Staff existingStaff = staffRepo.checkIfStaffExists(key, staff.getMobileNumber());
				if(null == existingStaff) {
					Master master = masterRepo.findByMasterId(key);
					Staff fetchedStaff = staffService.getStaffById(id);
					if(fetchedStaff.getRevenue_generated() > 0) {
						staff.setRevenue_generated(fetchedStaff.getRevenue_generated());
					}
					staff.setOrganization(master);
					staff.setStaffId(id);
					staff.setStaffStatus(Constants.ACTIVE_STATUS);
					staffRepo.save(staff);
				}else { 
					if(existingStaff.getStaffId() == staff.getStaffId()) {
						if(existingStaff.getRevenue_generated() > 0) {
							staff.setRevenue_generated(existingStaff.getRevenue_generated());
						}
						staff.setOrganization(existingStaff.getOrganization());
						staff.setStaffId(id);
						staff.setStaffStatus(Constants.ACTIVE_STATUS);
						staffRepo.save(staff);
					}else {
						String message = "Staff "+existingStaff.getFullName() + " has the same phone number "+staff.getMobileNumber();
						model.addAttribute(Constants.EXISTING_EDIT_STAFF, message); 
						return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.EDIT_STAFF_FORM;	
					}					
				}

			}
		}catch(Exception e) {

		}
		return Constants.REDIRECT_STAFF;
	}
}
