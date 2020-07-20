package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends JpaRepository<Stock, Integer> {
	 
		@Query(value="SELECT * from stock_tbl s where s.MASTER_ID =:id and s.QUANTITY > 0", 
				nativeQuery = true) 
		List<Stock> findByMasterId(@Param("id") int id);
		
		@Query(value="SELECT * from stock_tbl s where s.STOCK_ID =:id", 
				nativeQuery = true) 
		Stock findByStockId(@Param("id") int id);
}
