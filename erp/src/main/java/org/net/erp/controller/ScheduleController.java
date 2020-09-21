package org.net.erp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.BaseBO;
import org.net.erp.json.CalendarJson;
import org.net.erp.model.Master;
import org.net.erp.model.Schedule;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.ScheduleRepository;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RequestMapping("/schedule")
@Controller
public class ScheduleController {

	@Autowired
	ScheduleRepository scheduleRepo;

	@Autowired
	BaseBO baseBO;
	
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
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.EDIT_SCHEDULE_FORM_JSP;
	}

	@PostMapping("/editSchedule/{id}")
	public String updateSchedule(@Valid @ModelAttribute(Constants.EDIT_SCHEDULE_FORM) Schedule schedule,BindingResult bindingResult,@PathVariable(value = "id") int id,Model model,HttpServletRequest request) {
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
	
	@RequestMapping("/deleteSchedule/{id}")
	public ResponseEntity<?> deleteSchedule(@PathVariable(value = "id") int id){
		String jsonValue = null;
		try {
			scheduleRepo.deleteById(id);
			if(null == scheduleRepo.findById(id)) {
				jsonValue = baseBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = baseBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}
	
	@GetMapping("/getSchedule")
	public ResponseEntity<?> getSchedule(HttpServletRequest request){
		String value = null;
		try {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			List<Schedule> allSchedule = scheduleRepo.findByMasterId(key,start.split("T")[0],end.split("T")[0]);
			value = parseCalendarSchedule(allSchedule);
		}catch(Exception e) {

		}
		return ResponseEntity.ok(value);
	}

	/*
	 * 
	 * */
	public String parseCalendarSchedule(List<Schedule> allSchedule) {
		Gson gson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			List<CalendarJson> eventList = new ArrayList<CalendarJson>();			
			for(Schedule schedule : allSchedule) {
				String date = schedule.getScheduleDate().toString().split(Constants.SPACE)[0];
				CalendarJson calendarJson = new CalendarJson();	
				calendarJson.setTitle(schedule.getScheduleTopic());
				calendarJson.setDescription(schedule.getScheduleNotes());
				calendarJson.setStart(date+"T"+schedule.getScheduleFrom());
				calendarJson.setEnd(date+"T"+schedule.getScheduleTo());
				calendarJson.setClassName("fc-event-primary");
				calendarJson.setUrl("/schedule/editSchedule/"+schedule.getScheduleId());
				eventList.add(calendarJson);
			}
			json = gson.toJson(eventList);
		}catch(Exception e) {

		}
		return json;
	}
}