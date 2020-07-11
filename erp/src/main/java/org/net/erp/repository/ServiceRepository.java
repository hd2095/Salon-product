package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<Services, Integer> {

	@Query(value="SELECT * from service_tbl s where s.MASTER_ID =:id", 
			nativeQuery = true) 
	List<Services> findByMasterId(@Param("id") int id);
	
}
