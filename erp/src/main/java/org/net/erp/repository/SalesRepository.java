package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalesRepository extends JpaRepository<Sales,Integer>{

	@Query(value="SELECT * from sales_tbl s where s.MASTER_ID =:id", 
			nativeQuery = true) 
	List<Sales> findByMasterId(@Param("id") int id);
}
