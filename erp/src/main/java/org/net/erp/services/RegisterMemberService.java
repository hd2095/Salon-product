package org.net.erp.services;

import org.net.erp.model.RegisterMember;
import org.net.erp.repository.RegisterMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterMemberService {
    
	@Autowired
	RegisterMemberRepository registerMemberRepository;
	
	/*
	 * This function fetches the user on basis of email
	 * */
    public RegisterMember findUserByEmail(String email) {
    	return registerMemberRepository.findByEmailId(email);	
    }
    /*
     * This function fetches the user on basis of mobile number
     * */
    public RegisterMember findUserByMobileNumber(String mobileNumber) {
    	return registerMemberRepository.findByMobileNumber(mobileNumber);
    }
    /*
     * This function fetches the user on basis of mobile number
     * */
    public RegisterMember findUserByClientId(int clientId) {
    	return registerMemberRepository.findByMemberId(clientId);
    }
    public void save(RegisterMember registerMember) {
    	registerMemberRepository.save(registerMember);
    }

}
