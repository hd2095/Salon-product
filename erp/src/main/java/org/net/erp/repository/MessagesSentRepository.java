package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.MessagesSent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessagesSentRepository extends JpaRepository<MessagesSent, Integer>{

	@Query(value="SELECT * from messages_sent_tbl a where a.MASTER_ID =:id", 
			nativeQuery = true) 
	List<MessagesSent> findByMasterId(@Param("id") int id);
	
}
