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
	private int gtsnNo;
	
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

	public int getGtsnNo() {
		return gtsnNo;
	}

	public void setGtsnNo(int gtsnNo) {
		this.gtsnNo = gtsnNo;
	}

	public String getOrganizationPlan() {
		return organizationPlan;
	}

	public void setOrganizationPlan(String organizationPlan) {
		this.organizationPlan = organizationPlan;
	}
	
	
}
