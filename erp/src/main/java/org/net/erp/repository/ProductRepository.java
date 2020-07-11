package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {
 
	@Query(value="SELECT * from product_tbl c where c.MASTER_ID =:id", 
			nativeQuery = true) 
	List<Product> findByMasterId(@Param("id") int id);
}