package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepository extends JpaRepository<Staff,Integer>{

	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 ORDER BY s.REVENUE_GENERATED desc", 
			nativeQuery = true) 
	List<Staff> findByMasterId(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 ORDER BY s.REVENUE_GENERATED", 
			nativeQuery = true) 
	List<Staff> findByMasterIdAsc(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 ORDER BY s.FULL_NAME desc", 
			nativeQuery = true) 
	List<Staff> sortByName(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 ORDER BY s.FULL_NAME", 
			nativeQuery = true) 
	List<Staff> sortByNameAsc(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 ORDER BY s.EMAIL_ID desc", 
			nativeQuery = true) 
	List<Staff> sortByEmail(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 ORDER BY s.EMAIL_ID", 
			nativeQuery = true) 
	List<Staff> sortByEmailAsc(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 ORDER BY s.MOBILE_NUMBER desc", 
			nativeQuery = true) 
	List<Staff> sortByNumber(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 ORDER BY s.MOBILE_NUMBER", 
			nativeQuery = true) 
	List<Staff> sortByNumberAsc(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 ORDER BY s.REVENUE_GENERATED desc LIMIT 5", 
			nativeQuery = true) 
	List<Staff> findByRevenue(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id AND s.STAFF_STATUS = 1 and s.MOBILE_NUMBER =:number", 
			nativeQuery = true) 
	Staff checkIfStaffExists(@Param("id") int id,@Param("number") String number);
	
	@Query(value="SELECT COUNT(*) from staff_tbl s where s.MASTER_ID =:id", 
			nativeQuery = true) 
	int checkStaffEntries(@Param("id") int id);
	
	@Query(value="SELECT * from staff_tbl s where s.MASTER_ID =:id and s.STAFF_STATUS = 1 and s.FULL_NAME LIKE CONCAT('%',:text,'%')", 
			nativeQuery = true) 
	List<Staff> findByStaffName(@Param("id") int id,@Param("text") String text);
	
}
