package org.net.erp.services;

import java.util.List;

import org.net.erp.model.StaffDetails;
import org.net.erp.repository.StaffDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffDetailsServiceImpl implements StaffDetailsService{

	@Autowired
	StaffDetailsRepository staffDetailsRepository;
	
	@Override
	public void save(StaffDetails staffDetails) {
		// TODO Auto-generated method stub
		this.staffDetailsRepository.save(staffDetails);
	}

	@Override
	public List<StaffDetails> listAll() {
		// TODO Auto-generated method stub
		return staffDetailsRepository.findAll();
	}

	@Override
	public StaffDetails getStaffDetailsById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
