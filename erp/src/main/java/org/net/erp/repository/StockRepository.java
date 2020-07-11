package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends JpaRepository<Stock, Integer> {
	 
		@Query(value="SELECT * from stock_tbl s where s.MASTER_ID =:id", 
				nativeQuery = true) 
		List<Stock> findByMasterId(@Param("id") int id);
}
