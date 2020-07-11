package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.Services;
import org.net.erp.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements ServiceOperations{

	@Autowired
    private ServiceRepository repo;

	@Override
	public void save(Services service) {
		this.repo.save(service);
	}

	@Override
	public List<Services> listAll() {		
		return repo.findAll();
	}

	@Override
	public void deleteService(int id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public Services getServiceById(int id) {
		Optional<Services> optional = repo.findById(id);
		Services service = null;
        if (optional.isPresent()) {
        	service = optional.get();
        }
		return service;
	}

	
}
