package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.RegisterOrganization;
import org.net.erp.repository.RegisterOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterOrganizationService {

	@Autowired
    private RegisterOrganizationRepository repo;
	
    public void save(RegisterOrganization registerOrganization) {
        repo.save(registerOrganization);
    }
    
    public List<RegisterOrganization> listAll() {
        return repo.findAll();
    }
    
    public Optional<RegisterOrganization> getOrganization(int id) {
    	return repo.findById(id);
    }

}
