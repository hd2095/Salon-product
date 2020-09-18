package org.net.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "master_tbl")
public class Master {
	
	@Id
	@Column(name = "MASTER_ID")
	private int masterId;
	
	@Column(name = "ORGANIZATION_NAME")
	private String organizationName;
	
	@Column(name = "ORGANIZATION_ADDRESS")
	private String organizationAddress;
	
	@Column(name = "GSTN_NO")
	private String gstnNO;
	
	@Column(name = "GSTN_PERCENT")
	private int gstnPercent;
	
	@Column(name = "INVOICE_NO")
	private int invoiceNo;
	
	@Column(name = "PLAN")
	private String organizationPlan;

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationAddress() {
		return organizationAddress;
	}

	public void setOrganizationAddress(String organizationAddress) {
		this.organizationAddress = organizationAddress;
	}

	public String getOrganizationPlan() {
		return organizationPlan;
	}

	public void setOrganizationPlan(String organizationPlan) {
		this.organizationPlan = organizationPlan;
	}

	public String getGstnNO() {
		return gstnNO;
	}

	public void setGstnNO(String gstnNO) {
		this.gstnNO = gstnNO;
	}

	public int getGstnPercent() {
		return gstnPercent;
	}

	public void setGstnPercent(int gstnPercent) {
		this.gstnPercent = gstnPercent;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	
}
