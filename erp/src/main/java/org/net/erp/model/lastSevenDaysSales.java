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
import javax.persistence.Transient;
@Entity
@Table(name = "LAST_SEVEN_DAYS_TBL")
public class lastSevenDaysSales {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int saleId;
	
	@Column(name = "SALE_TOTAL")
	private float saleTotal;
	
	@Column(name = "SALE_DATE")
	private Date sellingDate;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "MASTER_ID",nullable = false)
	private Master organization;
	
	@Transient
	private String label;

	public int getSalesId() {
		return saleId;
	}

	public void setSalesId(int salesId) {
		this.saleId = salesId;
	}

	public float getSellingPrice() {
		return saleTotal;
	}

	public void setSellingPrice(float sellingPrice) {
		this.saleTotal = sellingPrice;
	}

	public Date getSellingDate() {
		return sellingDate;
	}

	public void setSellingDate(Date sellingDate) {
		this.sellingDate = sellingDate;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public float getSaleTotal() {
		return saleTotal;
	}

	public void setSaleTotal(float saleTotal) {
		this.saleTotal = saleTotal;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
	}
	
	
	

}
