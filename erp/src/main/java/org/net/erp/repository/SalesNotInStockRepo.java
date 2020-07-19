package org.net.erp.repository;

import java.util.List;


import org.net.erp.model.SalesNotInStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalesNotInStockRepo extends JpaRepository<SalesNotInStock,Integer>{
	@Query(value="SELECT * from sale_not_in_stock_tbl s where s.MASTER_ID =:id and s.SALE_STATUS = 1", 
			nativeQuery = true) 
	List<SalesNotInStock> findByMasterId(@Param("id") int id);
}
