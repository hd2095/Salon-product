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
@Table(name = "sale_details_tbl")
public class SaleDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int recordId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "sale_id",nullable = false)	
	private Sales sale;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "product_id",nullable = false)	
	private Product product;
	
	@Column(name = "SELLING_PRICE")
	private float sellingPrice;
	
	@Column(name = "SALE_DETAILS_DELTE_STATUS")
	private int saleDetailsDeleteStatuts;
	
	@Column(name = "QUANTITY")
	private int quantity;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Sales getSale() {
		return sale;
	}

	public void setSale(Sales sale) {
		this.sale = sale;
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

	public int getSaleDetailsDeleteStatuts() {
		return saleDetailsDeleteStatuts;
	}

	public void setSaleDetailsDeleteStatuts(int saleDetailsDeleteStatuts) {
		this.saleDetailsDeleteStatuts = saleDetailsDeleteStatuts;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
