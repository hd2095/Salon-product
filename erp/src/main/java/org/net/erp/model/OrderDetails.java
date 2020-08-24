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
@Table(name = "order_details_tbl")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int recordId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "order_id",nullable = false)	
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "supplier_id",nullable = false)	
	private Supplier supplier;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "product_id",nullable = false)	
	private Product product;
	
	@Column(name = "PRODUCT_COST_PRICE")
	private float productCostPrice;
	
	@Column(name = "PRODUCT_BRAND")
	private String productBrand;
	
	@Column(name = "PRODUCT_QUANTITY")
	private int productQuantity;
	
	@Column(name = "ORDER_DETAILS_STATUS")
	private int orderDeleteStatus;

	@Column(name = "PRODUCT_DELIVERY_STATUS")
	private String productDeliveryStatus;
	
	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

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

	public float getProductCostPrice() {
		return productCostPrice;
	}

	public void setProductCostPrice(float productCostPrice) {
		this.productCostPrice = productCostPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getOrderDeleteStatus() {
		return orderDeleteStatus;
	}

	public void setOrderDeleteStatus(int orderDeleteStatus) {
		this.orderDeleteStatus = orderDeleteStatus;
	}

	public String getProductDeliveryStatus() {
		return productDeliveryStatus;
	}

	public void setProductDeliveryStatus(String productDeliveryStatus) {
		this.productDeliveryStatus = productDeliveryStatus;
	}
	
}
