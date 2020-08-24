package org.net.erp.services;

import java.util.List;

import org.net.erp.model.StaffDetails;

public interface StaffDetailsService {

	void save(StaffDetails staffDetails);
    
    List<StaffDetails> listAll();
    
    StaffDetails getStaffDetailsById(int id);
    
}
