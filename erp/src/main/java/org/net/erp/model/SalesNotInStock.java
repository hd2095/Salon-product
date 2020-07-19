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

@Entity
@Table(name = "SALE_NOT_IN_STOCK")
public class SalesNotInStock {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int salesId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "client_id",nullable = false)
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "supplier_id",nullable = false)
	private Supplier supplier;
	
	@Column(name = "SALE_SELLING_PRICE")
	private float sellingPrice;
	
	@Column(name = "SALE_COST_PRICE")
	private float costPrice;
	
	@Column(name = "SALE_NOTES")
	private String saleNotes;

	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "SALE_QUANTITY")
	private int quantity;
	
	@Column(name = "SALE_STATUS")
	private int status;
	
	@Column(name = "SALE_PROFIT_OR_LOSS")
	private float saleProfitOrLoss;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;

	@Transient
	private final String actions = "null";

	public float getSaleProfitOrLoss() {
		return saleProfitOrLoss;
	}

	public void setSaleProfitOrLoss(float saleProfitOrLoss) {
		this.saleProfitOrLoss = saleProfitOrLoss;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public float getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(float costPrice) {
		this.costPrice = costPrice;
	}

	public String getSaleNotes() {
		return saleNotes;
	}

	public void setSaleNotes(String saleNotes) {
		this.saleNotes = saleNotes;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
	}
	
	
}
