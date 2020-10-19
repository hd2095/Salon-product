package org.net.erp.services;

import java.util.List;

import org.net.erp.model.Appointment;

public interface AppointmentService {

	void save(Appointment appointment);

	List<Appointment> listAll();

	void deleteAppointment(int id);

	Appointment getAppointmentById(int id);
	
	int checkAppointmentEntries(int id);

}
