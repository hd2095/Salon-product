package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.Appointment;
import org.net.erp.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	AppointmentRepository repo;
	
	@Override
	public void save(Appointment appointment) {
		this.repo.save(appointment);
	}

	@Override
	public List<Appointment> listAll() {
		return this.repo.findAll();
	}

	@Override
	public void deleteAppointment(int id) {
		this.repo.deleteById(id);
	}

	@Override
	public Appointment getAppointmentById(int id) {
		Optional<Appointment> optional = repo.findById(id);
		Appointment appointment = null;
        if (optional.isPresent()) {
        	appointment = optional.get();
        }
		return appointment;
	}

}
