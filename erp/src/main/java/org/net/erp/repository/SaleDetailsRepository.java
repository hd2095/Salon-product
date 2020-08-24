package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.SaleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails,Integer> {

	@Query(value="SELECT * from sale_details_tbl s where s.SALE_ID =:id and s.SALE_DETAILS_DELTE_STATUS = 1", 
			nativeQuery = true) 
	List<SaleDetails> findBySaleId(@Param("id") int id);
	
	@Query(value="SELECT * from sale_details_tbl s where s.RECORD_ID =:id", 
			nativeQuery = true) 
	SaleDetails findBySaleDetailsId(@Param("id") int id);
}
