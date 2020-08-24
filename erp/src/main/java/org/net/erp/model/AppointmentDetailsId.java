
package org.net.erp.model;

import java.io.Serializable;

public class AppointmentDetailsId implements Serializable{

	private Services service; 
	
	private Appointment appointment;
	
	public AppointmentDetailsId() {
		super();
	}


	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + appointment; result = prime * result + service;
	 * return result; }
	 * 
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; AppointmentDetailsId other = (AppointmentDetailsId) obj; if
	 * (appointmentId != other.appointmentId) return false; if (serviceId !=
	 * other.serviceId) return false; return true; }
	 */

	

	public AppointmentDetailsId(Services service, Appointment appointment) {
		super();
		this.service = service; 
		this.appointment = appointment; 
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointment == null) ? 0 : appointment.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentDetailsId other = (AppointmentDetailsId) obj;
		if (appointment == null) {
			if (other.appointment != null)
				return false;
		} else if (!appointment.equals(other.appointment))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		return true;
	}



}
