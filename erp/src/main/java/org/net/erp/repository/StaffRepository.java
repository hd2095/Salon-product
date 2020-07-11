package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepository extends JpaRepository<Staff,Integer>{

	@Query(value="SELECT * from staff_tbl c where c.MASTER_ID =:id", 
			nativeQuery = true) 
	List<Staff> findByMasterId(@Param("id") int id);
	
}
