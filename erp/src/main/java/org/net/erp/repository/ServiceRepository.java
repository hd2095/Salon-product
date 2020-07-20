package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ServiceRepository extends JpaRepository<Services, Integer> {

	@Query(value="SELECT * from service_tbl s where s.MASTER_ID =:id and s.SERVICE_STATUS = 1", 
			nativeQuery = true) 
	List<Services> findByMasterId(@Param("id") int id);
	
	@Transactional
	@Modifying()
	@Query(value="UPDATE service_tbl s set s.SERVICE_STATUS = 0 where s.CATEGORY_ID = :id",
	nativeQuery=true)
	public void deletServiceAfterCategory(@Param("id") int id);

	@Query(value="SELECT * from service_tbl s where s.MASTER_ID =:id and s.SERVICE_STATUS = 1 ORDER BY SERVICE_COST desc LIMIT 5", 
			nativeQuery = true) 
	List<Services> getTopServices(@Param("id") int id);
	
	@Query(value="SELECT * from service_tbl s where s.MASTER_ID =:id and s.SERVICE_STATUS = 1 and s.SERVICE_NAME =:name ", 
			nativeQuery = true) 
	Services getServiceByName(@Param("name") String name,@Param("id") int id);
}
