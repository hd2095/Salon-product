package org.net.erp.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "sales_tbl")
public class lastSevenDaysSales {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int salesId;
	
	@Column(name = "SALE_SELLING_PRICE")
	private float sellingPrice;
	
	@Column(name = "ROW_INSERTED_DATE")
	private Date sellingDate;

	@Transient
	private String label;
	
	public String getDate() {
		return label;
	}

	public void setDate(String date) {
		this.label = date;
	}

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Date getSellingDate() {
		return sellingDate;
	}

	public void setSellingDate(Date sellingDate) {
		this.sellingDate = sellingDate;
	}
	
	
	
}
