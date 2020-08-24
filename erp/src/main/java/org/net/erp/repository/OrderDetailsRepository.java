package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

	@Query(value="SELECT * from order_details_tbl a where a.ORDER_ID =:id and a.ORDER_DETAILS_STATUS = 1", 
			nativeQuery = true) 
	List<OrderDetails> findByOrderId(@Param("id") int id);
	
	@Query(value="SELECT * from order_details_tbl a where a.RECORD_ID =:id", 
			nativeQuery = true) 
	OrderDetails findByOrderDetailsId(@Param("id") int id);
}
