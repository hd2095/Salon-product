package org.net.erp.services;

import java.util.List;

import org.net.erp.model.Staff;

public interface StaffService {

	void save(Staff staff);
    
    List<Staff> listAll();
    
    void deleteStaff(int id);
    
    Staff getStaffById(int id);
    
}
