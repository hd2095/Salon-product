package org.net.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "client_plan_tbl")
public class ClientPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int planId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;

	@Column(name = "PLAN_NAME")
	private String planName;
	
	@Column(name = "PLAN_DESCRIPTION")
	private String planDescription;
	
	@Column(name = "PLAN_STATUS")
	private int planStatus;

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}

	public int getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(int planStatus) {
		this.planStatus = planStatus;
	}
	
	
}
