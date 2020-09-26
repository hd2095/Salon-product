package org.net.erp.repository;

import org.net.erp.model.SignUpOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SignUpOtpRepository extends JpaRepository<SignUpOtp, Integer>{
	
	@Query(value="SELECT * from sign_up_otp_tbl s where s.MOBILE_NUMBER =:id", 
			nativeQuery = true) 
	SignUpOtp findByNumber(@Param("id") String id);
}
