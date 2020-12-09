package org.net.erp.controller;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.net.erp.bo.AppointmentBO;
import org.net.erp.bo.InvoiceBO;
import org.net.erp.json.CalendarJson;
import org.net.erp.json.ExtendedPropsJson;
import org.net.erp.model.Appointment;
import org.net.erp.model.AppointmentDetails;
import org.net.erp.model.Client;
import org.net.erp.model.Invoice;
import org.net.erp.model.InvoiceDetails;
import org.net.erp.model.Master;
import org.net.erp.model.MessagesSent;
import org.net.erp.model.RegisterMember;
import org.net.erp.model.Services;
import org.net.erp.model.Staff;
import org.net.erp.model.lastSevenDaysSales;
import org.net.erp.reports.GeneratePdfReport;
import org.net.erp.repository.AppointmentDetailsRepository;
import org.net.erp.repository.AppointmentRepository;
import org.net.erp.repository.InvoiceDetailsRepository;
import org.net.erp.repository.InvoiceRepository;
import org.net.erp.repository.LastWeekSalesRepository;
import org.net.erp.repository.MasterRepository;
import org.net.erp.repository.MessagesSentRepository;
import org.net.erp.services.AppointmentService;
import org.net.erp.services.ClientService;
import org.net.erp.services.RegisterMemberService;
import org.net.erp.services.ServiceOperations;
import org.net.erp.services.StaffService;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private MessagesSentRepository messagesSentRepository;

	@Autowired
	private MasterRepository masterRepo;

	@Autowired
	private RegisterMemberService registerMemberService;

	@Autowired
	private LastWeekSalesRepository lastWeekSalesRepo;

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

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepo;

	@Autowired
	private InvoiceBO invoiceBO;

	@GetMapping(Constants.EMPTY)
	public String showAppointments(HttpServletRequest request,Model model) {
		String returnValue = null;
		try {
			int memberId = 0;
			if(null != request.getSession().getAttribute(Constants.SESSION_MEMBERID)) {
				memberId = (int) request.getSession().getAttribute(Constants.SESSION_MEMBERID);
				RegisterMember rm = registerMemberService.findUserByClientId(memberId);
				if(null != rm && !rm.isVerified()) {
					returnValue = "complete-registration";	
				}else if(null != rm && null == rm.getRegisterOrganization()) {
					returnValue = "complete-organization-registration";
				}else {
					int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
					Master master = masterRepo.findByMasterId(id);
					int entries = appointmentService.checkAppointmentEntries(id);
					if(master.getOrganizationPlan().equalsIgnoreCase("Basic")) {
						if(entries < 200) {
							model.addAttribute("showAddBtn", true);
						}
					}else if(master.getOrganizationPlan().equalsIgnoreCase("Standard")) {
						if(entries < 2000) {
							model.addAttribute("showAddBtn", true);
						}
					}
					returnValue = Constants.APPOINMENTS_JSP;
				}
			}
		}catch(Exception e) {
			System.out.println("Exception in showAppointments :: "+e.getMessage());
		}
		return returnValue;
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

	@GetMapping("/generateAppointmentInvoice/{id}")
	public String previewInvoice(@PathVariable(value = "id") int id,Model model) {
		try {
			Appointment appointment = this.appointmentService.getAppointmentById(id);
			List<AppointmentDetails> allAppointmentDetails = appointmentDetailsRepo.findByAppointmentId(id);
			String initials = "INV-";
			int length = String.valueOf(appointment.getOrganization().getInvoiceNo()).length();
			if(length == 3 && appointment.getOrganization().getInvoiceNo() < 999) {
				initials += "0"+String.valueOf(appointment.getOrganization().getInvoiceNo());
			}else if(length == 2 && appointment.getOrganization().getInvoiceNo() < 99) {
				initials += "00"+String.valueOf(appointment.getOrganization().getInvoiceNo());
			}else if(length == 1 && appointment.getOrganization().getInvoiceNo() < 9) {
				initials += "000"+String.valueOf(appointment.getOrganization().getInvoiceNo());
			}
			model.addAttribute(Constants.INVOICE_NO,initials);
			model.addAttribute(Constants.APPOINTMENT_INVOICE_FORM, appointment);	
			model.addAttribute(Constants.APPOINTMENT_DETAILS_INVOICE_FORM, allAppointmentDetails);
			model.addAttribute(Constants.INVOICE_DATE, DateFormat.getDateInstance().format(new Date()));
			model.addAttribute(Constants.INVOICE_DETAILS_FORM, new InvoiceDetails());
		}catch(Exception e) {

		}
		return Constants.INVOICE_FOLDER + Constants.FORWARD_SLASH +Constants.GENERATE_APPOINTMNET_IN_VOICE_FORM;
	}

	@PostMapping("/generateAppointmentInvoice/{id}")
	public String generateInvoice(@PathVariable(value = "id") int id,HttpServletRequest request,@ModelAttribute(Constants.INVOICE_DETAILS_FORM) InvoiceDetails invoiceDetails,ModelMap model) {
		int master_id = 0;
		Appointment appointment = null;
		float cgstAmount = 0;
		float discountAmount = 0;
		float sgstAmount = 0;
		int totalSaleQuantity = 0;
		float totalAfterTax = 0;
		float totalTax = 0;
		List<AppointmentDetails> allAppointmentDetails = null;
		try {
			master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = masterRepo.findByMasterId(master_id);
			String invoiceNo = request.getParameter(Constants.INVOICE_NO);
			String clientId = request.getParameter("clientId");
			appointment = appointmentService.getAppointmentById(id);
			appointment.setAppointmentInvoiceGenerated(true);
			appointmentService.save(appointment);
			Invoice invoice = new Invoice();
			invoice.setInvoiceNo(invoiceNo);
			invoice.setClient(clientService.getClientById(Integer.parseInt(clientId)));
			invoice.setMaster(master);
			invoice.setAppointment(appointment);
			invoice.setInvoiceDate(DateFormat.getDateInstance().format(new Date()));
			invoiceRepo.saveAndFlush(invoice);
			master.setInvoiceNo(master.getInvoiceNo()+1);
			masterRepo.save(master);
			invoiceDetails.setInvoice(invoice);			
			invoiceDetailsRepo.save(invoiceDetails);
			allAppointmentDetails = appointmentDetailsRepo.findByAppointmentId(id);
			if(invoiceDetails.getCgst() > 0) {
				cgstAmount = (invoiceDetails.getCgst() * appointment.getAppointmentTotal().floatValue())/100;
			} 
			if(invoiceDetails.getSgst() > 0) {
				sgstAmount = (invoiceDetails.getSgst() * appointment.getAppointmentTotal().floatValue())/100;
			} 
			if(invoiceDetails.getDiscount() > 0) {
				discountAmount = (invoiceDetails.getDiscount() * appointment.getAppointmentTotal().floatValue())/100;
			} 
			totalTax = cgstAmount + sgstAmount;			
			if(totalTax > 0 && discountAmount > 0) {
				totalAfterTax = appointment.getAppointmentTotal().floatValue() - (totalTax + discountAmount);
			}else if(totalTax > 0) {
				totalAfterTax = appointment.getAppointmentTotal().floatValue() - totalTax;
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.APPOINTMENT_INVOICE_FORM, appointment);	
		model.addAttribute(Constants.APPOINTMENT_DETAILS_INVOICE_FORM, allAppointmentDetails);
		model.addAttribute(Constants.INVOICE_DATE, DateFormat.getDateInstance().format(new Date()));
		model.addAttribute(Constants.TOTAL_TAX,totalTax);
		model.addAttribute(Constants.DISCOUNT,discountAmount);
		model.addAttribute(Constants.TOTAL_AFTER_TAX,totalAfterTax);
		model.addAttribute(Constants.CGST_AMT,cgstAmount);
		model.addAttribute(Constants.SGST_AMT,sgstAmount);
		model.addAttribute(Constants.TOTAL_SALE_QTY, totalSaleQuantity);
		model.addAttribute(Constants.INVOICE_DATE, DateFormat.getDateInstance().format(new Date()));
		model.addAttribute(Constants.INVOICE_DETAILS_FORM, invoiceDetails);
		return Constants.INVOICE_FOLDER + Constants.FORWARD_SLASH +Constants.FINAL_APPOINTMENT_IN_VOICE_FORM;
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
		boolean redirect = false;
		try {
			int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			AppointmentDetails serviceToDelete = appointmentDetailsRepo.findByAppointmentDetailsId(id);
			serviceToDelete.setServiceDeleteStatus(Constants.INACTIVE_STATUS);
			appointmentDetailsRepo.save(serviceToDelete);
			Appointment appointment = appointmentService.getAppointmentById(serviceToDelete.getAppointment().getAppointmentId());
			appointment.setAppointmentExpectedTotal(appointment.getAppointmentExpectedTotal().subtract(serviceToDelete.getServiceCost()));
			LocalTime appoinmentDuration = appointment.getAppointmentDuration().minusHours(serviceToDelete.getAppointmentDuration().getHour());
			appoinmentDuration = appoinmentDuration.minusMinutes(serviceToDelete.getAppointmentDuration().getMinute());
			appointment.setAppointmentDuration(appoinmentDuration);
			if(null != appointment.getAppointmentTotal()) {
				appointment.setAppointmentTotal(appointment.getAppointmentExpectedTotal().subtract(serviceToDelete.getServiceCost()));
			}
			if(appointmentDetailsRepo.findByAppointmentId(serviceToDelete.getAppointment().getAppointmentId()).size() == 0) {
				appointment.setAppointmentDeleteStatus(Constants.INACTIVE_STATUS);
				redirect = true;
			}
			appointmentService.save(appointment);
			lastSevenDaysSales existingSale = this.lastWeekSalesRepo.checkIfSaleExists(master_id, appointment.getAppointmentDate());
			if(null != existingSale) {
				float newSaleTotal = existingSale.getSellingPrice() - serviceToDelete.getServiceCost().floatValue();
				if(newSaleTotal == 0) {
					this.lastWeekSalesRepo.deleteById(existingSale.getSaleId());
				}else {
					this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSaleId(), newSaleTotal);
				}		
			}	
			if(Constants.INACTIVE_STATUS == appointmentDetailsRepo.findByAppointmentDetailsId(id).getServiceDeleteStatus()) {
				if(redirect) {
					jsonValue = "{\r\n" + 
							"	\"redirect\": true\r\n" + 
							"}";
				}else {
					jsonValue = appointmentBO.setDeleteOperationStatus(true);	
				}				
			}else {
				jsonValue = appointmentBO.setDeleteOperationStatus(false);
			}
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);
	}

	@RequestMapping("/showAppointmentInvoices")
	public String showAppointmentInvoices() {
		return Constants.INVOICE + Constants.FORWARD_SLASH +Constants.SHOW_APPOINTMENT_IN_VOICE;		
	}

	@RequestMapping("/getAllAppointmentInvoices")
	public ResponseEntity<?> allAppointmentInvoices(HttpServletRequest request) {
		String jsonValue = null;
		try{
			int id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			jsonValue = invoiceBO.parseFetchSaleInvoice(invoiceRepo.findByMasterIdForAppointments(id));	
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);		
	}

	@GetMapping("/add")
	public String showAddAppointment(Model model) {
		model.addAttribute(Constants.APPOINTMENT_FORM, new Appointment());
		return Constants.FORM_FOLDER + Constants.FORWARD_SLASH + Constants.NEW_APPOINTMENT_FORM;
	}

	@PostMapping("/add")
	public String createAppointment(@Valid @ModelAttribute(Constants.APPOINTMENT_FORM) Appointment appointment,RedirectAttributes ra,BindingResult bindingResult,HttpServletRequest request,Model model) {
		try {
			if(!bindingResult.hasErrors()) {
				int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				Master master = this.masterRepo.findByMasterId(master_id);
				String totalElements = request.getParameter("total_elements");
				int repeaterCount = 0;
				if(null == totalElements || Constants.EMPTY.equalsIgnoreCase(totalElements)) {
					repeaterCount = 0;
				}else {
					repeaterCount = Integer.parseInt(totalElements);
				}
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
				String notifyUser = request.getParameter("notifyClient");
				if(null != notifyUser && !notifyUser.equalsIgnoreCase("")) {
					if(Integer.parseInt(notifyUser) == 1) {
						String messageSentTo = appointment.getClient().getMobileNumber();
						String messageContents = "Dear User, Your appointment has been booked at "+ request.getParameter("[0][appointment_start_time]")+ " kindly be on time.\nRegards,\n"+master.getOrganizationName();
						MessagesSent msgSent = new MessagesSent();
						msgSent.setMaster(master);
						msgSent.setMessageContents(messageContents);	
						msgSent.setMessageSentTo(messageSentTo);
						msgSent.setMessageCount(1);
						boolean isMsgSent = appointmentBO.sendMessage(messageContents, messageSentTo);
						if(isMsgSent) {
							ra.addFlashAttribute("userNotifiedSuccess", "Client sent notification success");
						}else {
							ra.addFlashAttribute("userNotifiedFailure", "Client sent notification failure");
						}
						messagesSentRepository.save(msgSent);
					}
				}
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
						appointment.setAppointmentInvoiceGenerated(false);						
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
						Appointment fetchedAppointment = appointmentService.getAppointmentById(id);
						if(!fetchedAppointment.getAppointmentStatus().equalsIgnoreCase(Constants.APPOINTMENT_STATUS_COMPLETED)) {
							if(appointment.getAppointmentStatus().equalsIgnoreCase(Constants.APPOINTMENT_STATUS_COMPLETED)) {
								isComplete = true;
								int clientId = appointment.getClient().getClientId();
								Client client = clientService.getClientById(clientId);
								float revenue = client.getRevenue_generated() + appointment.getAppointmentExpectedTotal().floatValue();
								client.setRevenue_generated(revenue);
								client.setClientVisits(client.getClientVisits() + 1);
								if(null != client.getClientLastVisitedDate()) {
									if(appointment.getAppointmentDate().after(client.getClientLastVisitedDate())) {
										client.setClientLastVisitedDate(appointment.getAppointmentDate());
									}
								}else {
									client.setClientLastVisitedDate(appointment.getAppointmentDate());
								}
								clientService.save(client);
								lastSevenDaysSales existingSale = this.lastWeekSalesRepo.checkIfSaleExists(master_id, appointment.getAppointmentDate());
								if (null == existingSale) {
									final lastSevenDaysSales lastSevenDaysSales = new lastSevenDaysSales();
									lastSevenDaysSales.setSellingDate(appointment.getAppointmentDate());
									float appointmentTotal = appointment.getAppointmentExpectedTotal().floatValue();
									lastSevenDaysSales.setSellingPrice(appointmentTotal);
									lastSevenDaysSales.setOrganization(master);
									this.lastWeekSalesRepo.save(lastSevenDaysSales);
								}
								else {
									float newSaleTotal = existingSale.getSellingPrice() + appointment.getAppointmentExpectedTotal().floatValue();
									this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSaleId(), newSaleTotal);
								}
								appointment.setAppointmentTotal(appointment.getAppointmentExpectedTotal());
							}												
							appointment.setAppointmentExpectedTotal(appointment.getAppointmentExpectedTotal());	
						}
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
			e.printStackTrace();
		}

		return Constants.REDIRECT_APPOINTMENT;
	}

	@RequestMapping("/updateAppointment/{id}")
	public ResponseEntity<?> changeAppointmentStatus(@PathVariable(value = "id") int id,HttpServletRequest request){
		String jsonValue = null;
		try {
			int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);	
			Master master = masterRepo.findByMasterId(master_id);
			Appointment fetchedAppointment = appointmentService.getAppointmentById(id);
			Client client = clientService.getClientById(fetchedAppointment.getClient().getClientId());
			float revenue = client.getRevenue_generated() + fetchedAppointment.getAppointmentExpectedTotal().floatValue();
			client.setRevenue_generated(revenue);
			client.setClientVisits(client.getClientVisits() + 1);
			if(null != client.getClientLastVisitedDate()) {
				if(fetchedAppointment.getAppointmentDate().after(client.getClientLastVisitedDate())) {
					client.setClientLastVisitedDate(fetchedAppointment.getAppointmentDate());
				}
			}else {
				client.setClientLastVisitedDate(fetchedAppointment.getAppointmentDate());
			}
			clientService.save(client);
			lastSevenDaysSales existingSale = this.lastWeekSalesRepo.checkIfSaleExists(master_id, fetchedAppointment.getAppointmentDate());
			if (null == existingSale) {
				final lastSevenDaysSales lastSevenDaysSales = new lastSevenDaysSales();
				lastSevenDaysSales.setSellingDate(fetchedAppointment.getAppointmentDate());
				float appointmentTotal = fetchedAppointment.getAppointmentExpectedTotal().floatValue();
				lastSevenDaysSales.setSellingPrice(appointmentTotal);
				lastSevenDaysSales.setOrganization(master);
				this.lastWeekSalesRepo.save(lastSevenDaysSales);
			}
			else {
				float newSaleTotal = existingSale.getSellingPrice() + fetchedAppointment.getAppointmentExpectedTotal().floatValue();
				this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSaleId(), newSaleTotal);
			}
			fetchedAppointment.setAppointmentTotal(fetchedAppointment.getAppointmentExpectedTotal());
			fetchedAppointment.setAppointmentStatus(Constants.APPOINTMENT_STATUS_COMPLETED);
			appointmentRepo.save(fetchedAppointment);	
			List<AppointmentDetails> appointmentDetails = appointmentDetailsRepo.findByAppointmentId(id);
			for(AppointmentDetails ad : appointmentDetails) {
				float staffRevenue = ad.getStaff().getRevenue_generated() + ad.getService().getServiceCost().floatValue();
				ad.getStaff().setRevenue_generated(staffRevenue);
				staffService.save(ad.getStaff());
				ad.setServiceDeleteStatus(Constants.ACTIVE_STATUS);
				appointmentDetailsRepo.save(ad);
			}
			jsonValue = appointmentBO.setDeleteOperationStatus(true);
		}catch(Exception e) {
			jsonValue = appointmentBO.setDeleteOperationStatus(false);
			System.out.println("Exception in updateAppointment :: "+e.getMessage());
		}finally {
			
		}
		return ResponseEntity.ok(jsonValue);
	}
	
	@GetMapping("/deleteAppointment/{id}")
	public ResponseEntity<?> deleteAppointment(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		try {
			Appointment appointment = appointmentService.getAppointmentById(id);
			appointment.setAppointmentDeleteStatus(Constants.INACTIVE_STATUS);			
			if(Constants.INACTIVE_STATUS == appointmentService.getAppointmentById(id).getAppointmentDeleteStatus()) {
				int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
				int clientId = appointment.getClient().getClientId();
				Client client = clientService.getClientById(clientId);
				client.setClientVisits(client.getClientVisits() - 1);
				if(appointmentRepo.getLastClientVisitDate(clientId).equals(client.getClientLastVisitedDate())) {
					client.setClientLastVisitedDate(null);	
				}else {
					client.setClientLastVisitedDate(appointmentRepo.getLastClientVisitDate(clientId));
				}
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
				lastSevenDaysSales existingSale = this.lastWeekSalesRepo.checkIfSaleExists(master_id, appointment.getAppointmentDate());
				if(null != existingSale) {
					float newSaleTotal = existingSale.getSellingPrice() - appointment.getAppointmentExpectedTotal().floatValue();
					if(newSaleTotal == 0) {
						this.lastWeekSalesRepo.deleteById(existingSale.getSaleId());
					}else {
						this.lastWeekSalesRepo.updateSaleTotal(existingSale.getSaleId(), newSaleTotal);
					}		
				}			
				if(appointment.isAppointmentInvoiceGenerated()) {
					Invoice invoice = invoiceRepo.findInvoiceByAppointmentId(id);
					invoiceRepo.deleteById(invoice.getInvoiceId());
					Master master = masterRepo.findByMasterId(master_id);
					master.setInvoiceNo(master.getInvoiceNo() - 1);
					masterRepo.save(master);
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
					calendarJson.setUrl("/appointment/viewAppointmentDetails/"+appointments.get(i).getAppointmentId());
					eventList.add(calendarJson);
				};
			}
			json = gson.toJson(eventList);
		}catch(Exception e) {

		}
		return json;
	}

	@RequestMapping("/deleteInvoice/{id}")
	public ResponseEntity<?> deleteAppointmentInvoice(@PathVariable(value = "id") int id,HttpServletRequest request) {
		String jsonValue = null;
		try {
			int master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			Master master = masterRepo.findByMasterId(master_id);
			master.setInvoiceNo(master.getInvoiceNo() - 1);
			Optional<Invoice> invoiceDetails = invoiceRepo.findById(id);
			Appointment appointment = appointmentService.getAppointmentById(invoiceDetails.get().getAppointment().getAppointmentId());
			appointment.setAppointmentInvoiceGenerated(false);			
			appointmentService.save(appointment);
			masterRepo.save(master);
			invoiceRepo.deleteById(id);			
			if(invoiceRepo.findById(id).isPresent()) {
				jsonValue = invoiceBO.setDeleteOperationStatus(false);
			}else {
				jsonValue = invoiceBO.setDeleteOperationStatus(true);
			}
		}catch(Exception e) {

		}
		return ResponseEntity.ok(jsonValue);		
	}
		
	@RequestMapping("/viewInvoiceDetails/{id}")
	public String viewInvoiceDetails(@PathVariable(value = "id") int id,HttpServletRequest request,Model model) {
		float cgstAmount = 0;
		float discountAmount = 0;
		float sgstAmount = 0;		
		float totalAfterTax = 0;
		float totalTax = 0;
		Appointment appointment = null;
		List<AppointmentDetails> allAppointmentDetails = null;
		Optional<Invoice> invoice = null;
		InvoiceDetails invoiceDetails = null;
		try {
			invoice = invoiceRepo.findById(id);
			invoiceDetails = invoiceDetailsRepo.findByInvoiceId(id);
			int appointmentId = invoice.get().getAppointment().getAppointmentId(); 
			appointment = appointmentService.getAppointmentById(appointmentId);
			allAppointmentDetails = appointmentDetailsRepo.findByAppointmentId(appointmentId);
			if(invoiceDetails.getCgst() > 0) {
				cgstAmount = (invoiceDetails.getCgst() * appointment.getAppointmentTotal().floatValue())/100;
			} 
			if(invoiceDetails.getSgst() > 0) {
				sgstAmount = (invoiceDetails.getSgst() * appointment.getAppointmentTotal().floatValue())/100;
			} 
			if(invoiceDetails.getDiscount() > 0) {
				discountAmount = (invoiceDetails.getDiscount() * appointment.getAppointmentTotal().floatValue())/100;
			} 
			totalTax = cgstAmount + sgstAmount;			
			if(totalTax > 0 && discountAmount > 0) {
				totalAfterTax = appointment.getAppointmentTotal().floatValue() - (totalTax + discountAmount);
			}else if(totalTax > 0) {
				totalAfterTax = appointment.getAppointmentTotal().floatValue() - totalTax;
			}
		}catch(Exception e) {

		}
		model.addAttribute(Constants.TOTAL_TAX,totalTax);
		model.addAttribute(Constants.DISCOUNT,discountAmount);
		model.addAttribute(Constants.TOTAL_AFTER_TAX,totalAfterTax);
		model.addAttribute(Constants.CGST_AMT,cgstAmount);
		model.addAttribute(Constants.SGST_AMT,sgstAmount);
		model.addAttribute(Constants.APPOINTMENT_INVOICE_FORM, appointment);	
		model.addAttribute(Constants.APPOINTMENT_DETAILS_INVOICE_FORM, allAppointmentDetails);
		model.addAttribute(Constants.INVOICE_DATE, invoice.get().getInvoiceDate());
		model.addAttribute(Constants.INVOICE_DETAILS_FORM, invoiceDetails);
		return Constants.INVOICE + Constants.FORWARD_SLASH +Constants.FINAL_APPOINTMENT_IN_VOICE_FORM;
	}

	@RequestMapping(value = "/saveAppointmentInvoice/appointmentId/{id}/invoiceId/{iId}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> saveInvoice(@PathVariable(value = "id") int id,@PathVariable(value = "iId") int iId,HttpServletRequest request) {
		Master master = null;
		HashMap<String,String> pdfContents = new HashMap<String,String>();
		ByteArrayInputStream bis = null;
		HttpHeaders headers = new HttpHeaders();
		float cgstAmount = 0;
		float discountAmount = 0;
		float sgstAmount = 0;
		int master_id = 0;
		float totalAfterTax = 0;
		float totalTax = 0;
		Appointment appointment = null;
		List<AppointmentDetails> appointmentDetails = null;
		try {
			appointment = appointmentService.getAppointmentById(id);
			InvoiceDetails invoiceDetails = invoiceDetailsRepo.findByInvoiceId(iId);
			master_id = (int) request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY);
			master = masterRepo.findByMasterId(master_id);	
			appointmentDetails = appointmentDetailsRepo.findByAppointmentId(id);
			if(invoiceDetails.getCgst() > 0) {
				cgstAmount = (invoiceDetails.getCgst() * appointment.getAppointmentTotal().floatValue())/100;
			} 
			if(invoiceDetails.getSgst() > 0) {
				sgstAmount = (invoiceDetails.getSgst() * appointment.getAppointmentTotal().floatValue())/100;
			} 
			if(invoiceDetails.getDiscount() > 0) {
				discountAmount = (invoiceDetails.getDiscount() * appointment.getAppointmentTotal().floatValue())/100;
			} 
			totalTax = cgstAmount + sgstAmount;			
			if(totalTax > 0 && discountAmount > 0) {
				totalAfterTax = appointment.getAppointmentTotal().floatValue() - (totalTax + discountAmount);
			}else if(totalTax > 0) {
				totalAfterTax = appointment.getAppointmentTotal().floatValue() - totalTax;
			}
			pdfContents.put("title", invoiceDetails.getInvoice().getInvoiceNo());
			pdfContents.put("orgName", master.getOrganizationName());
			pdfContents.put("orgAddr",master.getOrganizationAddress());
			pdfContents.put("cgstPercent", String.valueOf(invoiceDetails.getCgst()));
			pdfContents.put("sgstPercent", String.valueOf(invoiceDetails.getSgst()));
			pdfContents.put("discountPercent", String.valueOf(invoiceDetails.getDiscount()));
			pdfContents.put("cgstAmt", String.valueOf(cgstAmount));
			pdfContents.put("sgstAmt", String.valueOf(sgstAmount));
			pdfContents.put("discountAmt", String.valueOf(discountAmount));
			pdfContents.put("totalTax", String.valueOf(totalTax));
			pdfContents.put("appointmentTotal",String.valueOf(appointment.getAppointmentExpectedTotal()));
			pdfContents.put("totalAfterTax", String.valueOf(totalAfterTax));
			pdfContents.put("invoiceDate", invoiceDetails.getInvoice().getInvoiceDate());
			pdfContents.put("invoiceNo", invoiceDetails.getInvoice().getInvoiceNo());
			pdfContents.put("invoiceTo", invoiceDetails.getInvoice().getClient().getFullName());
			pdfContents.put("invoiceToNum", invoiceDetails.getInvoice().getClient().getMobileNumber());
			pdfContents.put("invoiceToEmail", invoiceDetails.getInvoice().getClient().getEmailId());
			pdfContents.put("invoiceToPin", invoiceDetails.getInvoice().getClient().getClientPincode());
			pdfContents.put("invoiceToAddr", invoiceDetails.getInvoice().getClient().getClient_address());
			bis = GeneratePdfReport.invoiceAppointmentPdf(pdfContents,appointmentDetails);		
			headers.add("Content-Disposition", "inline; filename="+invoiceDetails.getInvoice().getInvoiceNo()+".pdf");
		}catch(Exception e) {

		}	
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@RequestMapping("/viewAppointmentDetails/{id}")
	public String viewAppointmentDetails(@PathVariable(value = "id") int id,Model model) {		
		try {
			Appointment appointment =  appointmentService.getAppointmentById(id);
			List<AppointmentDetails> appointmentDetails = appointmentDetailsRepo.findByAppointmentId(id);
			model.addAttribute("appointmentDate", DateFormat.getDateInstance().format(appointment.getAppointmentDate()));
			model.addAttribute("appointment", appointment);
			model.addAttribute("appointmentDetails", appointmentDetails);
		}catch(Exception e) {

		}
		return Constants.VIEW_DETAILS_FOLDER + Constants.FORWARD_SLASH + Constants.VIEW_APPOINTMENT_DETAILS;
	}
}
