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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.net.erp.util.Constants;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "member_tbl")
public class RegisterMember {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int member_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private RegisterOrganization registerOrganization;
	
	@NotBlank(message = Constants.MEMBER_FIRSTNAME)
	@Column(name = "FIRST_NAME")
	private String first_name;
	
	@NotBlank(message = Constants.MEMBER_LASTNAME)
	@Column(name = "LAST_NAME")
	private String last_name;
	
	@Size(max = 10)
	@NotBlank(message = Constants.MEMBER_PHONENUMBER)
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	
	@NotBlank(message = Constants.MEMBER_PASSWORD)
	@Column(name = "MEMBER_PASSWORD")
	private String memberPassword;
	
	@Email
	@NotBlank(message = Constants.MEMBER_EMAILID)
	@Column(name = "EMAIL_ID")
	private String emailId;
	
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "CREATED_ON")
	private Date created_on;
	
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "EXPIRES_ON")
	private Date expires_on;
	
	@Column(name = "GSTN_PERCENT")
	private int gstn_percent;
	
	@Column(name = "MEMBER_PHOTO")
	private String member_photo;
	
	@Column(name = "MEMBER_STATUS")
	private String member_status;
	
	@Column(name = "MEMBER_TYPE")
	private String member_type;

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public RegisterOrganization getRegisterOrganization() {
		return registerOrganization;
	}

	public void setRegisterOrganization(RegisterOrganization registerOrganization) {
		this.registerOrganization = registerOrganization;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobile_number) {
		this.mobileNumber = mobile_number;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String member_password) {
		this.memberPassword = member_password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String email_id) {
		this.emailId = email_id;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getExpires_on() {
		return expires_on;
	}

	public void setExpires_on(Date expires_on) {
		this.expires_on = expires_on;
	}

	public int getGstn_percent() {
		return gstn_percent;
	}

	public void setGstn_percent(int gstn_percent) {
		this.gstn_percent = gstn_percent;
	}

	public String getMember_photo() {
		return member_photo;
	}

	public void setMember_photo(String member_photo) {
		this.member_photo = member_photo;
	}

	public String getMember_status() {
		return member_status;
	}

	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	
	
}