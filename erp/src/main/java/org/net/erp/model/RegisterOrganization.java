package org.net.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.net.erp.util.Constants;

@Entity
@Table(name = "master_tbl")
public class RegisterOrganization {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int master_id;
	
	@Size(min = 2, max = 50)
	@NotBlank(message = Constants.ORGANIZATION_NAME)
	@Column(name = "ORGANIZATION_NAME")
	private String organization_name;
	
	@NotBlank(message = Constants.ORGANIZATION_ADDRESS)
	@Column(name = "ORGANIZATION_ADDRESS")
	private String orgnization_address;
	
	@Column(name = "GSTN_NO")
	private String gstn_no;
	
	@Column(name = "GSTN_PERCENT")
	private int gstn_percent;
	
	@NotBlank(message = Constants.ORGANIZATION_PLAN)
	@Column(name = "PLAN")
	private String plan;
	
	@Column(name="INVOICE_NO")
	private int invoiceNo;
	
	public RegisterOrganization() {
		
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public int getMaster_id() {
		return master_id;
	}

	public void setMaster_id(int master_id) {
		this.master_id = master_id;
	}

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	public String getOrgnization_address() {
		return orgnization_address;
	}

	public void setOrgnization_address(String orgnization_address) {
		this.orgnization_address = orgnization_address;
	}

	public String getGstn_no() {
		return gstn_no;
	}

	public void setGstn_no(String gstn_no) {
		this.gstn_no = gstn_no;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public int getGstn_percent() {
		return gstn_percent;
	}

	public void setGstn_percent(int gstn_percent) {
		this.gstn_percent = gstn_percent;
	}
	
}
