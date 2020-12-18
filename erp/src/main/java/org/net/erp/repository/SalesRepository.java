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
	
	@Query(value="SELECT * from sales_tbl s where s.MASTER_ID =:id and s.SALE_DELTE_STATUS = 1 order by SALE_DATE", 
			nativeQuery = true) 
	List<Sales> findByMasterIdAsc(@Param("id") int id);
	
	@Query(value="SELECT * from sales_tbl s where s.MASTER_ID =:id and s.SALE_DELTE_STATUS = 1 order by SALE_TOTAL desc", 
			nativeQuery = true) 
	List<Sales> findByTotal(@Param("id") int id);
	
	@Query(value="SELECT * from sales_tbl s where s.MASTER_ID =:id and s.SALE_DELTE_STATUS = 1 order by SALE_TOTAL", 
			nativeQuery = true) 
	List<Sales> findByTotalAsc(@Param("id") int id);
	
	@Query(value="SELECT * from sales_tbl s where s.MASTER_ID =:id and s.SALE_DELTE_STATUS = 1 order by CLIENT_ID desc", 
			nativeQuery = true) 
	List<Sales> findByClientId(@Param("id") int id);
	
	@Query(value="SELECT * from sales_tbl s where s.MASTER_ID =:id and s.SALE_DELTE_STATUS = 1 order by CLIENT_ID", 
			nativeQuery = true) 
	List<Sales> findByClientIdAsc(@Param("id") int id);
	
	@Query(value="SELECT * from sales_tbl s where s.MASTER_ID =:id and s.SALE_DATE =:date", 
			nativeQuery = true) 
	Sales checkIfSaleExists(@Param("id") int id,@Param("date") Date date);
		
	@Transactional
	@Modifying()
	@Query(value="UPDATE sales_tbl s set s.TOTAL_SALES =:total where s.SALES_ID =:id",
	nativeQuery=true)
	public void updateSaleTotal(@Param("id") int id,@Param("total") float total);
	
	@Query(value="SELECT COUNT(*) from sales_tbl s where s.MASTER_ID =:id", 
			nativeQuery = true)
	int checkSaleEntries(@Param("id") int id);
	
	@Query(value="SELECT * from sales_tbl inner join client_tbl on sales_tbl.client_id = client_tbl.client_id where sales_tbl.MASTER_ID =:id and sales_tbl.SALE_DELTE_STATUS = 1 and client_tbl.FULL_NAME LIKE CONCAT('%',:text,'%')", 
			nativeQuery = true) 
	List<Sales> findByClientName(@Param("id") int id,@Param("text") String text);
}
