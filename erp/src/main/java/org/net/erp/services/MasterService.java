package org.net.erp.services;


import org.net.erp.model.Master;
import org.net.erp.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MasterService {

	@Autowired
    private MasterRepository repo;
	
	/*
	 * This function fetches the user on basis of email
	 * */
    public Master findByMasterId(int id) {
    	return repo.findByMasterId(id);	
    }
}
