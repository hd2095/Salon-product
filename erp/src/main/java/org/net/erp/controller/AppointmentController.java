package org.net.erp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.AppointmentBO;
import org.net.erp.model.Appointment;
import org.net.erp.model.Client;
import org.net.erp.model.Master;
import org.net.erp.model.Staff;
import org.net.erp.repository.AppointmentRepository;
import org.net.erp.repository.MasterRepository;
import org.net.erp.services.AppointmentService;
import org.net.erp.services.ClientService;
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
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private MasterRepository masterRepo;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private AppointmentBO appointmentBO;
	
	@GetMapping(Constants.EMPTY)
    public String showAppointments() {
        return Constants.APPOINMENTS_JSP;
    }
    
	@RequestMapping("/getAllAppointments")
	public ResponseEntity<?> getAllAppointments(HttpServletRequest request) {
		String jsonValue = null;
		try {
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			List<Appointment> appointments = appointmentRepo.findByMasterId(id);
			for(Appointment appointment : appointments) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_ONE);
				appointment.setAppointmentDate(simpleDateFormat.parse(simpleDateFormat.format(appointment.getAppointmentDate())));
			}
			jsonValue = appointmentBO.parseFetchAppointment(appointments);
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}
	
	@GetMapping("/add")
	public String showAddAppointment(Model model) {
		model.addAttribute(Constants.APPOINTMENT_FORM, new Appointment());
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.NEW_APPOINTMENT_FORM;
	}
	
	@PostMapping("/add")
	public String createAppointment(@Valid @ModelAttribute(Constants.APPOINTMENT_FORM) Appointment appointment,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(key);
				appointment.setOrganization(master);
				if(null == appointment.getAppointmentStatus()) {
					appointment.setAppointmentStatus(Constants.APPOINTMENT_STATUS_BOOKED);
				}
				if(!(appointment.getAppointmentStartTime().split(Constants.COLON)[0].length() > 1)) {
					String formattedTime = appointment.getAppointmentStartTime();
					String tokens[] = formattedTime.split(Constants.COLON);
					for(int i=0;i<tokens.length;i++) {
						if(i == 0) {
							formattedTime = "0" + tokens[i] + Constants.COLON + tokens[i+1];
						}
					}
					appointment.setAppointmentStartTime(formattedTime);
				}
				appointment.setAppointmentDeleteStatus(Constants.ACTIVE_STATUS);
				appointmentRepo.save(appointment);
			}
		}catch(Exception e) {
			
		}
		model.addAttribute(Constants.APPOINTMENT_FORM, new Appointment());
		return Constants.REDIRECT_APPOINTMENT;
	}
	
	@GetMapping("/editAppointment/{id}")
	public String editAppointment(@PathVariable(value = "id") int id,Model model) {
		try {
			Appointment appointment = this.appointmentService.getAppointmentById(id);
			List<Appointment> appointmentDetails = new ArrayList<Appointment>();
			appointmentDetails.add(appointment);
			appointmentBO.parseFetchAppointment(appointmentDetails);
			model.addAttribute(Constants.EDIT_APPOINTMENT_FORM, appointment);	
		}catch(Exception e) {

		}
		 return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.EDIT_APPOINTMENT_FORM_JSP;
	}
	
	@PostMapping("/editAppointment/{id}")
	public String updateAppointment(@Valid @ModelAttribute(Constants.EDIT_APPOINTMENT_FORM) Appointment appointment,BindingResult bindingResult,@PathVariable(value = "id") int id,Model model,HttpServletRequest request) {
		try {
			if(!bindingResult.hasErrors()) {
				int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(key);
				appointment.setOrganization(master);
				if(appointment.getAppointmentStatus().equalsIgnoreCase(Constants.APPOINTMENT_STATUS_COMPLETED)) {
					float cost = appointment.getService().getServiceCost();
					int staffId = appointment.getStaff().getStaffId();
					int clientId = appointment.getClient().getClientId();
					Client client = clientService.getClientById(clientId);
					float clientRevenue = client.getRevenue_generated() + cost;
					client.setRevenue_generated(clientRevenue);
					clientService.save(client);
					Staff staff = staffService.getStaffById(staffId);
					float revenueGenerated = staff.getRevenue_generated() + cost;
					staff.setRevenue_generated(revenueGenerated);
					staffService.save(staff);
				}
				appointment.setAppointmentDeleteStatus(Constants.ACTIVE_STATUS);
				appointmentRepo.save(appointment);
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.EDIT_APPOINTMENT_FORM, new Appointment());
		return Constants.REDIRECT_APPOINTMENT;
	}
	
	@GetMapping("/deleteAppointment/{id}")
	public ResponseEntity<?> deleteAppointment(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			Appointment appointment = appointmentService.getAppointmentById(id);
			appointment.setAppointmentDeleteStatus(Constants.INACTIVE_STATUS);			
			if(Constants.INACTIVE_STATUS == appointmentService.getAppointmentById(id).getAppointmentDeleteStatus()) {
				jsonValue = appointmentBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = appointmentBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}
}
