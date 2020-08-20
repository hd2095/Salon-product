package org.net.erp.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.AppointmentBO;
import org.net.erp.json.CalendarJson;
import org.net.erp.json.ExtendedPropsJson;
import org.net.erp.model.Appointment;
import org.net.erp.model.AppointmentDetails;
import org.net.erp.model.Client;
import org.net.erp.model.Master;
import org.net.erp.model.Services;
import org.net.erp.model.Staff;
import org.net.erp.repository.AppointmentDetailsRepository;
import org.net.erp.repository.AppointmentRepository;
//import org.net.erp.repository.LastWeekSalesRepository;
import org.net.erp.repository.MasterRepository;
import org.net.erp.services.AppointmentService;
import org.net.erp.services.ClientService;
import org.net.erp.services.ServiceOperations;
import org.net.erp.services.StaffService;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private MasterRepository masterRepo;

	/*@Autowired
	private LastWeekSalesRepository lastWeekSalesRepo; */

	@Autowired
	private StaffService staffService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ServiceOperations serviceOperations;

	@Autowired
	private AppointmentRepository appointmentRepo;

	@Autowired
	private AppointmentDetailsRepository appointmentDetailsRepo;

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

	@RequestMapping("/getAppointmentDetails/{id}")
	public ResponseEntity<?> getAppointmentDetails(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			List<AppointmentDetails> appointments = appointmentDetailsRepo.findByAppointmentId(id);
			jsonValue = appointmentBO.parseFetchAppointmentDetails(appointments);
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/calendarView")
	public String showCalendarView() {
		return "appointment-calendar-view";
	}

	@GetMapping("/getAllAppointmentsForCalendar")
	public ResponseEntity<?> getAllEvents(HttpServletRequest request){
		String value = null;
		try {
			int key = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			List<Appointment> appointments = appointmentRepo.findByMasterId(key);
			value = parseCalendarAppointment(appointments);
		}catch(Exception e) {

		}
		return ResponseEntity.ok(value);
	}

	@RequestMapping("/deleteAppointmentService/{id}")
	public ResponseEntity<?> deleteAppointmentService(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		try {
			AppointmentDetails serviceToDelete = appointmentDetailsRepo.findByAppointmentDetailsId(id);
			serviceToDelete.setServiceDeleteStatus(Constants.INACTIVE_STATUS);
			appointmentDetailsRepo.save(serviceToDelete);
			if(Constants.INACTIVE_STATUS == appointmentDetailsRepo.findByAppointmentDetailsId(id).getServiceDeleteStatus()) {
				jsonValue = appointmentBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = appointmentBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@GetMapping("/add/")
	public String showAddAppointment(Model model) {
		model.addAttribute(Constants.APPOINTMENT_FORM, new Appointment());
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_APPOINTMENT_FORM;
	}

	@PostMapping("/add/")
	public String createAppointment(@Valid @ModelAttribute(Constants.APPOINTMENT_FORM) Appointment appointment,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				String totalElements = request.getParameter("total_elements");
				int repeaterCount = 0;
				if(null == totalElements || Constants.EMPTY.equalsIgnoreCase(totalElements)) {
					repeaterCount = 0;
				}else {
					repeaterCount = Integer.parseInt(totalElements);
				}
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;	
				for(int i  = 0;i <= repeaterCount; i++) {				
					String staff = request.getParameter("["+ i +"][appointment_staff]");
					String service = request.getParameter("["+ i +"][appointment_service]");		
					String appointmentDetailsStartTime = request.getParameter("["+ i +"][appointment_start_time]");
					String appointmentDetailsDuration = request.getParameter("["+ i +"][appointment_duration]");					
					if(i == 0) {
						LocalTime appointment_start_time = LocalTime.parse(appointmentDetailsStartTime, dateTimeFormatter);
						LocalTime appointment_duration = LocalTime.parse(request.getParameter("total_appointment_duration"), dateTimeFormatter);
						LocalTime appointment_end_time  = appointment_start_time.plusHours(appointment_duration.getHour());
						appointment_end_time = appointment_end_time.plusMinutes(appointment_duration.getMinute());
						appointment.setAppointmentStartTime(appointmentDetailsStartTime);
						appointment.setAppointmentEndTime(appointment_end_time);
						Master master = masterRepo.findByMasterId(master_id);
						Date last_modified_date = new Date();
						appointment.setOrganization(master);
						appointment.setLastModifiedDate(last_modified_date);
						appointment.setAppointmentDeleteStatus(Constants.ACTIVE_STATUS);
						if(null == appointment.getAppointmentStatus()) {
							appointment.setAppointmentStatus(Constants.APPOINTMENT_STATUS_BOOKED);
						}
						BigDecimal appointment_expected_total = new BigDecimal(request.getParameter("appointment_cost"));
						appointment.setAppointmentExpectedTotal(appointment_expected_total);
						appointment.setAppointmentDuration(appointment_duration);
						appointmentRepo.saveAndFlush(appointment);
					}
					AppointmentDetails appointmentDetails = new AppointmentDetails();					
					int service_id = Integer.parseInt(service);
					int staff_id = Integer.parseInt(staff);
					Staff staffObj = staffService.getStaffById(staff_id);
					Services serviceById = serviceOperations.getServiceById(service_id);
					appointmentDetails.setAppointmentStartTime(LocalTime.parse(appointmentDetailsStartTime, dateTimeFormatter));
					appointmentDetails.setAppointmentDuration(LocalTime.parse(appointmentDetailsDuration, dateTimeFormatter));
					appointmentDetails.setServiceId(serviceById);
					appointmentDetails.setAppointmentId(appointment);
					appointmentDetails.setServiceCost(serviceById.getServiceCost());
					appointmentDetails.setServiceDeleteStatus(Constants.ACTIVE_STATUS);
					appointmentDetails.setStaff(staffObj);
					appointmentDetailsRepo.save(appointmentDetails);
				}														
			}else {				
				return Constants.FORM_FOLDER + Constants.FORWARD_SLASH +Constants.NEW_APPOINTMENT_FORM;
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
			model.addAttribute(Constants.EDIT_APPOINTMENT_FORM, appointment);	
		}catch(Exception e) {

		}
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.EDIT_APPOINTMENT_FORM_JSP;
	}

	@PostMapping("/editAppointment/{id}")
	public String updateAppointment(@Valid @ModelAttribute(Constants.EDIT_APPOINTMENT_FORM) Appointment appointment,BindingResult bindingResult,@PathVariable(value = "id") int id,Model model,HttpServletRequest request) {
		boolean isComplete = false;
		try {
			if(!bindingResult.hasErrors()) {
				int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);				
				String totalElements = request.getParameter("edit_total_elements");
				int repeaterCount = 0;
				if(null == totalElements || Constants.EMPTY.equalsIgnoreCase(totalElements)) {
					repeaterCount = 0;
				}else {
					repeaterCount = Integer.parseInt(totalElements);
				}
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
				for(int i  = 0;i <= repeaterCount; i++) {
					String staff = request.getParameter("["+ i +"][edit_appointment_staff]");
					String service = request.getParameter("["+ i +"][edit_appointment_service]");		
					String appointmentDetailsStartTime = request.getParameter("["+ i +"][edit_appointment_start_time]");
					String appointmentDetailsDuration = request.getParameter("["+ i +"][edit_appointment_duration]");	
					String recordId = request.getParameter("["+ i +"][edit_appointment_record_id]");
					if(i == 0) {
						LocalTime appointment_start_time = LocalTime.parse(appointmentDetailsStartTime, dateTimeFormatter);
						LocalTime appointment_duration = LocalTime.parse(request.getParameter("edit_total_appointment_duration"), dateTimeFormatter);
						LocalTime appointment_end_time  = appointment_start_time.plusHours(appointment_duration.getHour());
						appointment_end_time = appointment_end_time.plusMinutes(appointment_duration.getMinute());
						appointment.setAppointmentStartTime(appointmentDetailsStartTime);
						appointment.setAppointmentEndTime(appointment_end_time);
						Master master = masterRepo.findByMasterId(master_id);
						Date last_modified_date = new Date();
						appointment.setOrganization(master);
						appointment.setLastModifiedDate(last_modified_date);
						appointment.setAppointmentDeleteStatus(Constants.ACTIVE_STATUS);
						if(appointment.getAppointmentStatus().equalsIgnoreCase(Constants.APPOINTMENT_STATUS_COMPLETED)) {
							isComplete = true;
							int clientId = appointment.getClient().getClientId();
							Client client = clientService.getClientById(clientId);
							float revenue = client.getRevenue_generated() + Float.parseFloat(request.getParameter("edit_appointment_cost"));
							client.setRevenue_generated(revenue);
							client.setClientVisits(client.getClientVisits() + 1);
							if(null != client.getClientLastVisitedDate()) {
								if(appointment.getAppointmentDate().after(client.getClientLastVisitedDate())) {
									client.setClientLastVisitedDate(appointment.getAppointmentDate());
								}
							}else {
								client.setClientLastVisitedDate(appointment.getAppointmentDate());
							}
						}
						BigDecimal appointment_expected_total = new BigDecimal(request.getParameter("edit_appointment_cost"));
						appointment.setAppointmentExpectedTotal(appointment_expected_total);
						appointment.setAppointmentDuration(appointment_duration);
						appointmentRepo.saveAndFlush(appointment);
					}
					AppointmentDetails appointmentDetails = new AppointmentDetails();	
					if(null != recordId && !Constants.EMPTY.equalsIgnoreCase(recordId)) {
						appointmentDetails.setRecordId(Integer.parseInt(recordId));
					}
					int service_id = Integer.parseInt(service);
					int staff_id = Integer.parseInt(staff);
					Staff staffObj = staffService.getStaffById(staff_id);					
					Services serviceById = serviceOperations.getServiceById(service_id);					
					appointmentDetails.setAppointmentStartTime(LocalTime.parse(appointmentDetailsStartTime, dateTimeFormatter));
					appointmentDetails.setAppointmentDuration(LocalTime.parse(appointmentDetailsDuration, dateTimeFormatter));
					appointmentDetails.setServiceId(serviceById);
					appointmentDetails.setAppointmentId(appointment);
					appointmentDetails.setServiceCost(serviceById.getServiceCost());
					appointmentDetails.setServiceDeleteStatus(Constants.ACTIVE_STATUS);
					appointmentDetails.setStaff(staffObj);
					if(isComplete) {
						float staffRevenue = staffObj.getRevenue_generated() + serviceById.getServiceCost().floatValue();
						staffObj.setRevenue_generated(staffRevenue);						
					}
					appointmentDetailsRepo.save(appointmentDetails);
				}
				model.addAttribute(Constants.EDIT_APPOINTMENT_FORM, new Appointment());
			}else {
				return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.EDIT_APPOINTMENT_FORM_JSP;
			}
		}catch(Exception e) {

		}

		return Constants.REDIRECT_APPOINTMENT;
	}

	@GetMapping("/deleteAppointment/{id}")
	public ResponseEntity<?> deleteAppointment(@PathVariable(value = "id") int id) {
		String jsonValue = null;
		try {
			Appointment appointment = appointmentService.getAppointmentById(id);
			appointment.setAppointmentDeleteStatus(Constants.INACTIVE_STATUS);			
			if(Constants.INACTIVE_STATUS == appointmentService.getAppointmentById(id).getAppointmentDeleteStatus()) {
				int clientId = appointment.getClient().getClientId();
				Client client = clientService.getClientById(clientId);
				client.setClientVisits(client.getClientVisits() - 1);
				client.setClientLastVisitedDate(appointmentRepo.getLastClientVisitDate(clientId));
				float revenue = client.getRevenue_generated();
				if(null != appointment.getAppointmentTotal()) {
					float appointmentTotal = appointment.getAppointmentTotal().floatValue();
					client.setRevenue_generated(revenue - appointmentTotal); 
				}else if(null != appointment.getAppointmentExpectedTotal()) {
					float appointmentExpectedTotal = appointment.getAppointmentExpectedTotal().floatValue();
					client.setRevenue_generated(revenue - appointmentExpectedTotal); 
				}	
				List<AppointmentDetails> appointmentDetails = appointmentDetailsRepo.findByAppointmentId(id);
				for(AppointmentDetails details : appointmentDetails) {
					details.setServiceDeleteStatus(Constants.INACTIVE_STATUS);
					Staff staff = details.getStaff();
					float staffRevenue = staff.getRevenue_generated();
					staff.setRevenue_generated(staffRevenue - details.getService().getServiceCost().floatValue());
					details.setStaff(staff);
					appointmentDetailsRepo.save(details);
				}
				jsonValue = appointmentBO.setDeleteOperationStatus(true);
			}else {
				jsonValue = appointmentBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {
			return ResponseEntity.ok(jsonValue);
		}
		return ResponseEntity.ok(jsonValue);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_ONE);
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/*
	 * 
	 * */
	public String parseCalendarAppointment(List<Appointment> appointments) {
		Gson gson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			List<CalendarJson> eventList = new ArrayList<CalendarJson>();
			for(int i = 0;i<appointments.size();i++) {			
				String appointmentDate = appointments.get(i).getAppointmentDate().toString().split(Constants.SPACE)[0];
				List<AppointmentDetails> appointmentDetails = appointmentDetailsRepo.findByAppointmentId(appointments.get(i).getAppointmentId());
				String description = Constants.EMPTY;
				String title = Constants.EMPTY;
				for(AppointmentDetails details : appointmentDetails) {
					CalendarJson calendarJson = new CalendarJson();	
					ExtendedPropsJson extendedPropsJson = new ExtendedPropsJson();	
					title = details.getService().getServiceName();
					description = appointments.get(i).getAppointmentNotes();
					extendedPropsJson.setClient(appointments.get(i).getClient().getFullName());
					String startTime = appointmentDate + "T" +details.getAppointmentStartTime();
					LocalTime appointmentEndTime = details.getAppointmentStartTime().plusHours(details.getAppointmentDuration().getHour());
					appointmentEndTime = appointmentEndTime.plusMinutes(details.getAppointmentDuration().getMinute());
					String endTime = appointmentDate + "T" +appointmentEndTime;
					calendarJson.setStart(startTime);
					calendarJson.setEnd(endTime);
					calendarJson.setDescription(description);
					calendarJson.setExtendedProps(extendedPropsJson);
					calendarJson.setTitle(title);
					calendarJson.setClassName("fc-event-primary");
					calendarJson.setUrl("/appointment/editAppointment/"+appointments.get(i).getAppointmentId());
					eventList.add(calendarJson);
				};
			}
			json = gson.toJson(eventList);
		}catch(Exception e) {

		}
		return json;
	}
}
