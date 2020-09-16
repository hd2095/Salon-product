package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Integer>{
	
	@Query(value="SELECT * from client_tbl c where c.MASTER_ID =:id and c.CLIENT_STATUS = 1", 
			nativeQuery = true) 
	List<Client> findByMasterId(@Param("id") int id);
	
	@Query(value="SELECT * from client_tbl c where c.MASTER_ID =:id and c.CLIENT_STATUS = 1 ORDER BY c.REVENUE_GENERATED desc limit 5", 
			nativeQuery = true) 
	List<Client> findByRevenue(@Param("id") int id);
	
	@Query(value="SELECT * from client_tbl c where c.MASTER_ID =:id and c.CLIENT_STATUS = 1 and c.MOBILE_NUMBER =:mobile", 
			nativeQuery = true) 
	Client checkIfClientExists(@Param("id") int id,@Param("mobile") String number);
	
	@Query(value="SELECT * from client_tbl c where c.MASTER_ID =:id and c.CLIENT_STATUS = 1 and DATE_ADD(BIRTHDAY,INTERVAL year(CURDATE()) - YEAR(BIRTHDAY) + IF(DAYOFYEAR(CURDATE()) > DAYOFYEAR(BIRTHDAY),1,0)YEAR) BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 2 MONTH) order by DAY(BIRTHDAY)", 
			nativeQuery = true) 
	List<Client> findByBirthday(@Param("id") int id);
}
