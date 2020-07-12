package org.net.erp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.net.erp.util.Constants;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "staff_tbl")
public class Staff {
	
	public enum GENDER {
		Male,Female
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int staffId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;
	
	@NotBlank(message = Constants.STAFF_FULLNAME)
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Size(max = 10)
	@NotBlank(message = Constants.STAFF_PHONENUMBER)
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	
	@NotBlank(message = Constants.STAFF_ADDRESS)
	@Column(name = "STAFF_ADDRESS")
	private String staff_address;

	@Email
	@NotBlank(message = Constants.STAFF_EMAILID)
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@NotNull(message = Constants.STAFF_START_DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "STAFF_START_DATE")
	private Date staff_start_date;
	
	@NotNull(message = Constants.STAFF_END_DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "STAFF_END_DATE")
	private Date staff_end_date;
    
	@Column(name = "REVENUE_GENERATED")
	private float revenue_generated;
	
	@Enumerated(EnumType.STRING)
	private GENDER gender;
	
	@Column(name="STAFF_WORKING_DAYS")
	private String workdays;
	
	@NotNull(message = Constants.STAFF_IN_TIME)
	@Column(name="STAFF_IN_TIME")
	private String staff_in_time;
	
	@NotNull(message = Constants.STAFF_OUT_TIME)
	@Column(name="STAFF_OUT_TIME")
	private String staff_out_time;
	
	@NotNull(message = Constants.STAFF_BIRTHDAY)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "STAFF_BIRTHDAY")
	private Date birthday;
	
	@NotBlank(message = Constants.STAFF_PINCODE)
	@Column(name = "STAFF_PINCODE")
	private String staffPincode;
	
	@NotBlank(message = Constants.STAFF_DESIGNATION)
	@Column(name = "STAFF_DESIGNATION")
	private String staffDesignation;
	
	@Column(name = "STAFF_STATUS")
	private int staffStatus;
	
	@Transient
	private final String actions = "null";

    public int getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(int staffStatus) {
		this.staffStatus = staffStatus;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getStaffPincode() {
		return staffPincode;
	}

	public void setStaffPincode(String staffPincode) {
		this.staffPincode = staffPincode;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
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

	public String getStaff_address() {
		return staff_address;
	}

	public void setStaff_address(String staff_address) {
		this.staff_address = staff_address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getStaff_start_date() {
		return staff_start_date;
	}

	public void setStaff_start_date(Date staff_start_date) {
		this.staff_start_date = staff_start_date;
	}

	public Date getStaff_end_date() {
		return staff_end_date;
	}

	public void setStaff_end_date(Date staff_end_date) {
		this.staff_end_date = staff_end_date;
	}

	public float getRevenue_generated() {
		return revenue_generated;
	}

	public void setRevenue_generated(float revenue_generated) {
		this.revenue_generated = revenue_generated;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

	public String getWorkdays() {
		return workdays;
	}

	public void setWorkdays(String workdays) {
		this.workdays = workdays;
	}

	public String getStaff_in_time() {
		return staff_in_time;
	}

	public void setStaff_in_time(String staff_in_time) {
		this.staff_in_time = staff_in_time;
	}

	public String getStaff_out_time() {
		return staff_out_time;
	}

	public void setStaff_out_time(String staff_out_time) {
		this.staff_out_time = staff_out_time;
	}

	public String getStaffDesignation() {
		return staffDesignation;
	}

	public void setStaffDesignation(String staffDesignation) {
		this.staffDesignation = staffDesignation;
	}
	
	

}
