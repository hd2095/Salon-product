package org.net.erp.model;

import java.util.Date;

public class Appointment {
	
	private String name;
	private String service;
	private String staff;
	private Date day;
	private Date time;
	private float cost;
	private String status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Appointment [name=" + name + ", service=" + service + ", staff=" + staff + ", day=" + day + ", time="
				+ time + ", cost=" + cost + ", status=" + status + "]";
	}
	
}
