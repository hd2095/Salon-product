package org.net.erp.repository;

import org.net.erp.model.RegisterMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterMemberRepository extends JpaRepository<RegisterMember, Integer>{

	 RegisterMember findByEmailId(String email); 
	 
	 RegisterMember findByMobileNumber(String mobileNumber);
	
}
