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
import javax.validation.constraints.NotNull;

import org.net.erp.util.Constants;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sales_tbl")
public class Sales {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int saleId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "client_id",nullable = false)
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;
	
	@NotNull(message = Constants.SALE_DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "SALE_DATE")
	private Date saleDate;
    
	@Column(name = "SALE_TOTAL")
	private float saleTotal;
	
	@Column(name = "SALE_DELTE_STATUS")
	private int saleDeleteStatuts;
	
	@Column(name = "SALE_NOTES")
	private String saleNotes;

	@Transient
	private final String actions = "null";

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int salesId) {
		this.saleId = salesId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public float getSaleTotal() {
		return saleTotal;
	}

	public void setSaleTotal(float saleTotal) {
		this.saleTotal = saleTotal;
	}

	public int getSaleDeleteStatuts() {
		return saleDeleteStatuts;
	}

	public void setSaleDeleteStatuts(int saleDeleteStatuts) {
		this.saleDeleteStatuts = saleDeleteStatuts;
	}

	public String getSaleNotes() {
		return saleNotes;
	}

	public void setSaleNotes(String saleNotes) {
		this.saleNotes = saleNotes;
	}

}
