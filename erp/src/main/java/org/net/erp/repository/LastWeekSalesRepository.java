package org.net.erp.repository;

import java.util.Date;
import java.util.List;

import org.net.erp.model.lastSevenDaysSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LastWeekSalesRepository extends JpaRepository<lastSevenDaysSales,Integer>{

	@Query(value="SELECT * from last_seven_days_tbl s where s.SALE_DATE >= DATE(NOW()) - INTERVAL 7 DAY and s.SALE_DATE <= DATE(NOW()) and s.MASTER_ID =:id", 
			nativeQuery = true)
	List<lastSevenDaysSales> lastWeekSales(@Param("id") int id);
	
	@Query(value="SELECT * from last_seven_days_tbl s where s.MASTER_ID =:id and s.SALE_DATE =:date", 
			nativeQuery = true) 
	lastSevenDaysSales checkIfSaleExists(@Param("id") int id,@Param("date") Date date);
		
	@Transactional
	@Modifying()
	@Query(value="UPDATE last_seven_days_tbl s set s.SALE_TOTAL =:total where s.SALE_ID =:id",
	nativeQuery=true)
	public void updateSaleTotal(@Param("id") int id,@Param("total") float total);

}
