package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	 
		@Query(value="SELECT * from order_tbl o where o.MASTER_ID =:id and o.ORDER_STATUS = 1", 
				nativeQuery = true) 
		List<Order> findByMasterId(@Param("id") int id);
		
		@Query(value="SELECT * from order_tbl o inner join product_tbl p on p.product_id = o.product_id inner join supplier_tbl s on s.supplier_id = o.supplier_id and o.order_id =:id",nativeQuery = true)
		Order findByOrderId(@Param("id") int id);
		
		@Modifying
		@Query(value="update order_tbl o set o.ORDER_STATUS = 0 where o.ORDER_ID =:id",
				nativeQuery = true)
		void updateOrderStatus(@Param("id") int id);
}