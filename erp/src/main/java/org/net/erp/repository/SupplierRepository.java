package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
	 
		@Query(value="SELECT * from supplier_tbl c where c.MASTER_ID =:id", 
				nativeQuery = true) 
		List<Supplier> findByMasterId(@Param("id") int id);
}
