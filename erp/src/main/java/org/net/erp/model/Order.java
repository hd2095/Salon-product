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
@Table(name = "order_tbl")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	
	@NotNull(message = Constants.ORDER_QUANTITY)
	@Column(name = "QUANTITY")
	private int quantity;
	
	@NotNull(message = Constants.ORDER_COST_PRICE)
	@Column(name = "COST_PRICE")
	private String costPrice;
	
	@Column(name = "ORDER_TOTAL")
	private float orderTotal;
	
	@NotNull(message = Constants.ORDER_DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "ORDER_DATE")
	private Date orderDate;
    
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "ORDER_RECEIEVED_DATE")
	private Date orderReceivedDate;
		
	@Column(name = "ORDER_DELIVERY_STATUS")
	private String orderDeliveryStatus;
	
	@Column(name = "ORDER_STATUS")
	private int orderStatus;

	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "supplier_id",nullable = false)
	private Supplier supplier;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "product_id",nullable = false)
	private Product product;

	@Transient
	private final String actions = "null";

	public Date getOrderReceivedDate() {
		return orderReceivedDate;
	}

	public void setOrderReceivedDate(Date orderReceivedDate) {
		this.orderReceivedDate = orderReceivedDate;
	}

	public String getOrderDeliveryStatus() {
		return orderDeliveryStatus;
	}

	public void setOrderDeliveryStatus(String orderDeliveryStatus) {
		this.orderDeliveryStatus = orderDeliveryStatus;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public float getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
}
