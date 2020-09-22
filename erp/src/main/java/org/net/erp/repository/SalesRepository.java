package org.net.erp.repository;

import java.util.Date;
import java.util.List;

import org.net.erp.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SalesRepository extends JpaRepository<Sales,Integer>{

	@Query(value="SELECT * from sales_tbl s where s.MASTER_ID =:id and s.SALE_DELTE_STATUS = 1 order by SALE_DATE desc", 
			nativeQuery = true) 
	List<Sales> findByMasterId(@Param("id") int id);
	
	@Query(value="SELECT * from sales_tbl s where s.MASTER_ID =:id and s.SALE_DATE =:date", 
			nativeQuery = true) 
	Sales checkIfSaleExists(@Param("id") int id,@Param("date") Date date);
		
	@Transactional
	@Modifying()
	@Query(value="UPDATE sales_tbl s set s.TOTAL_SALES =:total where s.SALES_ID =:id",
	nativeQuery=true)
	public void updateSaleTotal(@Param("id") int id,@Param("total") float total);
}
