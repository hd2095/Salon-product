package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {
 
	@Query(value="SELECT * from product_tbl p where p.MASTER_ID =:id and p.PRODUCT_STATUS = 1", 
			nativeQuery = true) 
	List<Product> findByMasterId(@Param("id") int id);
}