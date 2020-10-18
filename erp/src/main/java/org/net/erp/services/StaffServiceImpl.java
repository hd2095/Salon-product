package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.Staff;
import org.net.erp.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
    private StaffRepository repo;

	@Override
	public void save(Staff Staff) {
		this.repo.save(Staff);
	}

	@Override
	public void deleteStaff(int id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public Staff getStaffById(int id) {
		Optional<Staff> optional = repo.findById(id);
		Staff Staff = null;
        if (optional.isPresent()) {
        	Staff = optional.get();
        }
		return Staff;
	}

	@Override
	public List<Staff> listAll() {	
		return repo.findAll();
	}

	@Override
	public int checkStaffEntries(int id) {
		return repo.checkStaffEntries(id);
	}


}
