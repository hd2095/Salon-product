package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepository extends JpaRepository<Staff,Integer>{

	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1", 
			nativeQuery = true) 
	List<Staff> findByMasterId(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 ORDER BY s.REVENUE_GENERATED desc LIMIT 5", 
			nativeQuery = true) 
	List<Staff> findByRevenue(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 and s.MOBILE_NUMBER =:number", 
			nativeQuery = true) 
	Staff checkIfStaffExists(@Param("id") int id,@Param("number") String number);
	
}
