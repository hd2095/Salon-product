package org.net.erp.model;

import java.math.BigDecimal;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import org.net.erp.util.Constants;
@Entity
@Table(name = "service_tbl")
public class Services {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int serviceId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "category_id",nullable = false)
	private Category category;
	
	@Column(name = "SERVICE_DESCRIPTION")
	private String serviceDescription;
	
	@PositiveOrZero(message = Constants.SERVICE_COST)
	@Column(name = "SERVICE_COST")
	private BigDecimal serviceCost;
	
	//@NotBlank(message = Constants.SERVICE_DURATION)
	//@Duration
	@Column(name = "SERVICE_DURATION")
	private String serviceDuration;
	
	@NotBlank(message = Constants.SERVICE_NAME)
	@Column(name = "SERVICE_NAME")
	private String serviceName;

	@Column(name = "SERVICE_STATUS")
	private int serviceStatus;

	@Transient
	private final String actions = "null";

	public int getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(int serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public BigDecimal getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(BigDecimal serviceCost) {
		this.serviceCost = serviceCost;
	}

	public String getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(String serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	
}
