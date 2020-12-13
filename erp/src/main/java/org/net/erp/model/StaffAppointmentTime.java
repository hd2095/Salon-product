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
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "staff_appointment_time_tbl")
public class StaffAppointmentTime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int recordId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "staff_id",nullable = false)
	private Staff staff;
	
	@OneToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "appointment_details_id",nullable = false)
	private AppointmentDetails appointmentDetails;
	
	@Column(name = "APPOINTMENT_START_TIME")
	private String appointmentStartTime;
	
	@Column(name = "APPOINTMENT_END_TIME")
	private String appointmentEndTime;
	
	@Column(name = "APPOINTMENT_DATE")
	private Date appointmentDate;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public String getAppointmentEndTime() {
		return appointmentEndTime;
	}

	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public AppointmentDetails getAppointmentDetails() {
		return appointmentDetails;
	}

	public void setAppointmentDetails(AppointmentDetails appointmentDetails) {
		this.appointmentDetails = appointmentDetails;
	}
}
