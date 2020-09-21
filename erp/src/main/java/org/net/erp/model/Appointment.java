
package org.net.erp.model;

import java.math.BigDecimal;
import java.time.LocalTime;
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
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;
	
	@NotNull(message = Constants.APPOINTMENT_DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "APPOINTMENT_DATE")
	private Date appointmentDate;
	
	@Column(name = "APPOINTMENT_START_TIME")
	private String appointmentStartTime;
	
	@Column(name = "APPOINTMENT_END_TIME")
	private LocalTime appointmentEndTime;
	
	@Column(name = "APPOINTMENT_DURATION")
	private LocalTime appointmentDuration;
	
	@Column(name = "APPOINTMENT_TOTAL")
	private BigDecimal appointmentTotal;
	
	@Column(name = "APPOINTMENT_EXPECTED_TOTAL")
	private BigDecimal appointmentExpectedTotal;
	
	@Column(name = "APPOINTMENT_DISCOUNT")
	private int appointmentDiscount;
	
	@Column(name = "APPOINTMENT_NOTES")
	private String appointmentNotes;
	
	@Column(name = "APPOINTMENT_STATUS")
	private String appointmentStatus;
	
	@Column(name = "APPOINTMENT_DELETE_STATUS")
	private int appointmentDeleteStatus;
		
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "LAST_MODIFIED_DATE")
	private Date lastModifiedDate;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "client_id",nullable = false)
	private Client client;
	
	@Column(name = "APPOINTMENT_INVOICE_GENERATED")
	private boolean appointmentInvoiceGenerated;
	
	@Transient
	private final String actions = "null";

	public boolean isAppointmentInvoiceGenerated() {
		return appointmentInvoiceGenerated;
	}

	public void setAppointmentInvoiceGenerated(boolean appointmentInvoiceGenerated) {
		this.appointmentInvoiceGenerated = appointmentInvoiceGenerated;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
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

	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public LocalTime getAppointmentEndTime() {
		return appointmentEndTime;
	}

	public void setAppointmentEndTime(LocalTime appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public LocalTime getAppointmentDuration() {
		return appointmentDuration;
	}

	public void setAppointmentDuration(LocalTime appointmentDuration) {
		this.appointmentDuration = appointmentDuration;
	}

	public BigDecimal getAppointmentTotal() {
		return appointmentTotal;
	}

	public void setAppointmentTotal(BigDecimal appointmentTotal) {
		this.appointmentTotal = appointmentTotal;
	}

	public BigDecimal getAppointmentExpectedTotal() {
		return appointmentExpectedTotal;
	}

	public void setAppointmentExpectedTotal(BigDecimal appointmentExpectedTotal) {
		this.appointmentExpectedTotal = appointmentExpectedTotal;
	}

	public int getAppointmentDiscount() {
		return appointmentDiscount;
	}

	public void setAppointmentDiscount(int appointmentDiscount) {
		this.appointmentDiscount = appointmentDiscount;
	}

	public String getAppointmentNotes() {
		return appointmentNotes;
	}

	public void setAppointmentNotes(String appointmentNotes) {
		this.appointmentNotes = appointmentNotes;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public int getAppointmentDeleteStatus() {
		return appointmentDeleteStatus;
	}

	public void setAppointmentDeleteStatus(int appointmentDeleteStatus) {
		this.appointmentDeleteStatus = appointmentDeleteStatus;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
