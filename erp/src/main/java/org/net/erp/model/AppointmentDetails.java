
package org.net.erp.model;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment_details_tbl")
public class AppointmentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int recordId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "appointment_id",nullable = false)	
	private Appointment appointment;

	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "service_id",nullable = false)	
	private Services service; 
	
	@Column(name = "APPOINTMENT_START_TIME")
	private String appointmentStartTime;
	
	@Column(name = "APPOINTMENT_DURATION")
	private LocalTime appointmentDuration;
	
	@Column(name = "SERVICE_COST")
	private BigDecimal serviceCost;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "staff_id",nullable = false)
	private Staff staff;

	@Column(name = "SERVICE_DELETE_STATUS")
	private int serviceDeleteStatus;

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
	}

	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public LocalTime getAppointmentDuration() {
		return appointmentDuration;
	}

	public void setAppointmentDuration(LocalTime appointmentDuration) {
		this.appointmentDuration = appointmentDuration;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Appointment getAppointmentId() {
		return appointment;
	}

	public void setAppointmentId(Appointment appointment) {
		this.appointment = appointment;
	}

	public Services getServiceId() {
		return service;
	}

	public void setServiceId(Services service) {
		this.service = service;
	}

	public BigDecimal getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(BigDecimal serviceCost) {
		this.serviceCost = serviceCost;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getServiceDeleteStatus() {
		return serviceDeleteStatus;
	}

	public void setServiceDeleteStatus(int serviceDeleteStatus) {
		this.serviceDeleteStatus = serviceDeleteStatus;
	}
	
}
