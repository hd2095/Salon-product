package org.net.erp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.net.erp.util.Constants;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "appointment_tbl")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int appointmentId;
	
	@NotNull(message = Constants.APPOINTMENT_DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "APPOINTMENT_DATE")
	private Date appointmentDate;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "service_id",nullable = false)
	private Services service;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "client_id",nullable = false)
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "staff_id",nullable = false)
	private Staff staff;
	
	@Column(name = "APPOINTMENT_STATUS")
	private String appointmentStatus;
	
	@Column(name = "APPOINTMENT_DELETE_STATUS")
	private int appointmentDeleteStatus;
	
	@Column(name = "APPOINTMENT_START_TIME")
	private String appointmentStartTime;
		
	@Column(name = "APPOINTMENT_INVOICE")
	private String appointmentInvoice;
	
	@Column(name = "APPOINTMENT_NOTES")
	private String appointmentNotes;
	
	@Transient
	private final String actions = "null";

	public int getAppointmentDeleteStatus() {
		return appointmentDeleteStatus;
	}

	public void setAppointmentDeleteStatus(int appointmentDeleteStatus) {
		this.appointmentDeleteStatus = appointmentDeleteStatus;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
	}

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public String getAppointmentInvoice() {
		return appointmentInvoice;
	}

	public void setAppointmentInvoice(String appointmentInvoice) {
		this.appointmentInvoice = appointmentInvoice;
	}

	public String getAppointmentNotes() {
		return appointmentNotes;
	}

	public void setAppointmentNotes(String appointmentNotes) {
		this.appointmentNotes = appointmentNotes;
	}
	
	
	
}
