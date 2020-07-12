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
	
}
