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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.net.erp.util.Constants;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "client_tbl")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int clientId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;
	
	@NotBlank(message = Constants.CLIENT_FULLNAME)
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Size(max = 10)
	@NotBlank(message = Constants.CLIENT_PHONENUMBER)
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name = "CLIENT_ADDRESS")
	private String client_address;

	@NotBlank(message = Constants.CLIENT_PINCODE)
	@Column(name = "CLIENT_PINCODE")
	private String clientPincode;

	@Email
	@Column(name = "EMAIL_ID")
	private String emailId;
	
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "CLIENT_START_DATE")
	private Date client_start_date;
	
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "CLIENT_END_DATE")
	private Date client_end_date;
    
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "BIRTHDAY")
	private Date birthday;
	
	@Column(name = "REVENUE_GENERATED")
	private float revenue_generated;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "IS_MEMBER")
	private int isMember;
	
	@Column(name = "CLIENT_STATUS")
	private int clientStatus;
	
	@Transient
	private final String actions = "null";
	
	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
	}

	public int getClientStatus() {
		return clientStatus;
	}

	public void setClientStatus(int clientStatus) {
		this.clientStatus = clientStatus;
	}

	public String getClientPincode() {
		return clientPincode;
	}

	public void setClientPincode(String clientPincode) {
		this.clientPincode = clientPincode;
	}

	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int client_id) {
		this.clientId = client_id;
	}

	public Master getRegisterOrganization() {
		return organization;
	}

	public void setRegisterOrganization(Master registerOrganization) {
		this.organization = registerOrganization;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getClient_address() {
		return client_address;
	}

	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getClient_start_date() {
		return client_start_date;
	}

	public void setClient_start_date(Date client_start_date) {
		this.client_start_date = client_start_date;
	}

	public Date getClient_end_date() {
		return client_end_date;
	}

	public void setClient_end_date(Date client_end_date) {
		this.client_end_date = client_end_date;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public float getRevenue_generated() {
		return revenue_generated;
	}

	public void setRevenue_generated(float revenue_generated) {
		this.revenue_generated = revenue_generated;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getIsMember() {
		return isMember;
	}

	public void setMember(int isMember) {
		this.isMember = isMember;
	}
	
	
}
