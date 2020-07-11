package org.net.erp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.net.erp.bo.CalendarBO;
import org.net.erp.json.CalendarJson;
import org.net.erp.model.Appointment;
import org.net.erp.repository.AppointmentRepository;
import org.net.erp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RequestMapping("/calendar")
@Controller
public class CalendarController {

	@Autowired
	AppointmentRepository appointmentRepo;
	
	@Autowired
	CalendarBO calendarBO;
	
   @GetMapping(Constants.EMPTY)
    public String showAppointments() {
        return "calendar";
    }
   
   @GetMapping("/getAllEvents/{id}")
   public ResponseEntity<?> getAllEvents(@PathVariable(value = "id") int id,HttpServletResponse response){
	   String value = null;
	   try {
		   List<Appointment> appointments = appointmentRepo.findByStaffId(id);
		   value = calendarBO.parseFetchAppointment(appointments);
	   }catch(Exception e) {
		   
	   }
	   return ResponseEntity.ok(value);
   }
}
