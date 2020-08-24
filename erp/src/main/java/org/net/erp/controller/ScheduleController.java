package org.net.erp.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.model.Appointment;
import org.net.erp.model.Master;
import org.net.erp.model.Schedule;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.ScheduleRepository;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/schedule")
@Controller
public class ScheduleController {

	@Autowired
	ScheduleRepository scheduleRepo;
	
	@Autowired
	private MasterRepository masterRepo;
	
	@GetMapping(Constants.EMPTY)
	public String showSchedule() {
		return Constants.SCHEDULE_JSP;
	}
	
	@GetMapping("/add/")
	public String showAddSchedule(Model model) {
		model.addAttribute(Constants.SCHEDULE_FORM, new Schedule());
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_SCHEDULE_FORM;
	}

	@PostMapping("/add/")
	public String addSchedule(@Valid @ModelAttribute(Constants.SCHEDULE_FORM) Schedule schedule,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = masterRepo.findByMasterId(master_id);
				schedule.setOrganization(master);
				scheduleRepo.save(schedule);
			}
		}catch(Exception e) {
			
		}
		return Constants.REDIRECT_SCHDEULE;
	}

	@GetMapping("/editSchedule/{id}")
	public String editSchedule(@PathVariable(value = "id") int id,Model model) {
		try {
			Optional<Schedule> schedule = scheduleRepo.findById(id);
			model.addAttribute(Constants.EDIT_SCHEDULE_FORM, schedule);	
		}catch(Exception e) {

		}
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.EDIT_APPOINTMENT_FORM_JSP;
	}
	
}
