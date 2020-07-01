package org.net.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plan_tbl")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int plan_id;
	
	@Column(name = "PLAN_NAME")
	private String plan_name;
	
	@Column(name = "PLAN_MSG_COUNT")
	private int plan_msg_count;

	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public int getPlan_msg_count() {
		return plan_msg_count;
	}

	public void setPlan_msg_count(int plan_msg_count) {
		this.plan_msg_count = plan_msg_count;
	}


	
}
