package org.net.erp.model;

import java.math.BigInteger;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "staff_details_tbl")
public class StaffDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int staffDetailId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "staff_id",nullable = false)
	private Staff staff;
	
	@Column(name = "COMMISSION_PERCENTAGE")
	private int commissionPercent;
	
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "FROM_DATE")
	private Date fromDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "TO_DATE")
	private Date toDate;
	
	@Column(name = "COMMISSION_AMOUNT")
	private BigInteger commissionAmount;

	public int getStaffDetailId() {
		return staffDetailId;
	}

	public void setStaffDetailId(int staffDetailId) {
		this.staffDetailId = staffDetailId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getCommissionPercent() {
		return commissionPercent;
	}

	public void setCommissionPercent(int commissionPercent) {
		this.commissionPercent = commissionPercent;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public BigInteger getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(BigInteger commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	
	
}
