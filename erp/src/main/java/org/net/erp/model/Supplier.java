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
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.net.erp.util.Constants;

@Entity
@Table(name = "supplier_tbl")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int supplierId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;
	
	@NotBlank(message = Constants.SUPPLIER_FULLNAME)
	@Column(name = "SUPPLIER_NAME")
	private String supplierName;
	
	@Size(max = 10)
	@NotBlank(message = Constants.SUPPLIER_PHONENUMBER)
	@Column(name = "SUPPLIER_NUMBER")
	private String supplierNumber;
	
	@NotBlank(message = Constants.SUPPLIER_ADDRESS)
	@Column(name = "SUPPLIER_ADDRESS")
	private String supplier_address;

	@NotBlank(message = Constants.SUPPLIER_PINCODE)
	@Column(name = "SUPPLIER_PINCODE")
	private String supplierPincode;

	@Email
	@NotBlank(message = Constants.SUPPLIER_EMAILID)
	@Column(name = "SUPPLIER_EMAIL")
	private String supplierEmail;

	@Column(name = "SUPPLIER_GSTN_NO")
	private String supplierGstnNo;
	
	@Column(name = "SUPPLIER_STATUS")
	private int supplierStatus;
	
	@Transient
	private final String actions = "null";

	public int getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(int supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplier_address() {
		return supplier_address;
	}

	public void setSupplier_address(String supplier_address) {
		this.supplier_address = supplier_address;
	}

	public String getSupplierPincode() {
		return supplierPincode;
	}

	public void setSupplierPincode(String supplierPincode) {
		this.supplierPincode = supplierPincode;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getSupplierGstnNo() {
		return supplierGstnNo;
	}

	public void setSupplierGstnNo(String supplierGstnNo) {
		this.supplierGstnNo = supplierGstnNo;
	}

	
}
