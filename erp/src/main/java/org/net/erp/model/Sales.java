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
@Table(name = "sales_tbl")
public class Sales {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int salesId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "product_id",nullable = false)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "client_id",nullable = false)
	private Client client;

	@Column(name = "SALE_SELLING_PRICE")
	private float sellingPrice;
	
	@Column(name = "SALE_COST_PRICE")
	private float costPrice;
	
	@Column(name = "SALE_NOTES")
	private String saleNotes;

	@Column(name = "SALE_QUANTITY")
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;

	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "stock_id",nullable = false)
	private Stock stock;

	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "supplier_id",nullable = false)
	private Supplier supplier;
	
	@Transient
	private final String actions = "null";

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
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

	
}
