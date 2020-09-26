package org.net.erp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "sign_up_otp_tbl")
public class SignUpOtp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int recordId;
	
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name = "OTP")
	private int otp;

	@Column(name = "OTP_DATE")
	private Date otp_date;
	
	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public Date getOtp_date() {
		return otp_date;
	}

	public void setOtp_date(Date otp_date) {
		this.otp_date = otp_date;
	}
	
	
}
