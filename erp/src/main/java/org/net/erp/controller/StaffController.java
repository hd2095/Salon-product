package org.net.erp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.StaffBO;
import org.net.erp.model.Master;
import org.net.erp.model.Staff;
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
			 model.addAttribute(Constants.STAFF_FORM,new Staff());
			 model.addAttribute(Constants.EDIT_STAFF_FORM_ATTR,new Staff());
		 }catch(Exception e) {
			 
		 }
	        return Constants.STAFF_JSP;
	    }
	 
	 @PostMapping("/add")
	 public String createStaff(@Valid @ModelAttribute(Constants.STAFF_FORM) Staff staff,BindingResult bindingResult,HttpServletRequest request,Model model) {
		 try {
			 int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			 Master master = masterRepo.findByMasterId(key);
			 staff.setOrganization(master);
			 staffRepo.save(staff);
			 model.addAttribute(Constants.STAFF_FORM,new Staff());
			 model.addAttribute(Constants.EDIT_STAFF_FORM_ATTR,new Staff());
		 }catch(Exception e) {
			 
		 }
		 return Constants.STAFF_JSP;
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
	 
		@GetMapping("/deleteStaff/{id}")
		public ResponseEntity<?> deleteStaff(@PathVariable(value = "id") int id) {
			String jsonValue = null;
			try {
				staffService.deleteStaff(id);
				if(null == staffService.getStaffById(id)) {
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
		public String updateStaff(@PathVariable(value = "id") int id,@ModelAttribute(Constants.EDIT_STAFF_FORM_ATTR) Staff staff,BindingResult bindingResult,HttpServletRequest request,Model model) {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = masterRepo.findByMasterId(key);
			staff.setOrganization(master);
			staff.setStaffId(id);
			staffRepo.save(staff);
			model.addAttribute(Constants.STAFF_FORM, new Staff());
			model.addAttribute(Constants.EDIT_STAFF_FORM_ATTR,new Staff());
			return Constants.REDIRECT_STAFF;
		}
}
