package org.net.erp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "upgrade_to_pro_tbl") 
public class UpgradeToPro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int recordId;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "REQUESTED_DATE")
	private Date requestedDate;
	
	@Column(name = "CLIENT_FIRST_NAME")
	private String clientFn;
	
	@Column(name = "CLIENT_LAST_NAME")
	private String clientLn;
	
	@Column(name = "ORGANIZATION_NAME")
	private String clientOrgName;
	
	@Column(name = "CLIENT_NUMBER")
	private String clientNumber;
	
	@Column(name = "PLAN_NAME")
	private String planName;

	@Transient
	private final String actions = "null";
	
	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date date) {
		this.requestedDate = date;
	}

	public String getClientFn() {
		return clientFn;
	}

	public void setClientFn(String clientFn) {
		this.clientFn = clientFn;
	}

	public String getClientLn() {
		return clientLn;
	}

	public void setClientLn(String clientLn) {
		this.clientLn = clientLn;
	}

	public String getClientOrgName() {
		return clientOrgName;
	}

	public void setClientOrgName(String clientOrgName) {
		this.clientOrgName = clientOrgName;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	
	
}
