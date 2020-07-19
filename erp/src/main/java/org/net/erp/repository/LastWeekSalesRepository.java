package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.lastSevenDaysSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LastWeekSalesRepository extends JpaRepository<lastSevenDaysSales,Integer>{

	@Query(value="SELECT s.SALES_ID,s.SALE_SELLING_PRICE,s.ROW_INSERTED_DATE from sales_tbl s where s.ROW_INSERTED_DATE >= DATE(NOW()) - INTERVAL 7 DAY and s.MASTER_ID =:id", 
			nativeQuery = true)
	List<lastSevenDaysSales> lastWeekSales(@Param("id") int id);

}
