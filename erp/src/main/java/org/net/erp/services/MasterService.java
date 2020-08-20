package org.net.erp.services;


import java.util.List;

import org.net.erp.model.Master;
import org.net.erp.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MasterService {

	@Autowired
    private MasterRepository repo;
	
	/*
	 * This function fetches the user on basis of email
	 * */
    public Master findByMasterId(int id) {
    	return repo.findByMasterId(id);	
    }
    
    public List<Master> listAll() {
        return repo.findAll();
    }
}
