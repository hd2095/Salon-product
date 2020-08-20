package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
	 
		@Query(value="SELECT * from supplier_tbl s where s.MASTER_ID =:id and s.SUPPLIER_STATUS = 1", 
				nativeQuery = true) 
		List<Supplier> findByMasterId(@Param("id") int id);
		
		@Query(value="SELECT * from supplier_tbl s where s.SUPPLIER_ID =:id and s.SUPPLIER_STATUS = 1", 
				nativeQuery = true) 
		Supplier findBySupplierId(@Param("id") int id);
		
		@Query(value="SELECT * from supplier_tbl s where s.SUPPLIER_ID =:id and s.SUPPLIER_STATUS = 1 and s.SUPPLIER_NAME =:name", 
				nativeQuery = true) 
		Supplier checkIfSupplierWithSameNameExists(@Param("id") int id,@Param("name") String name);
		
		@Query(value="SELECT * from supplier_tbl s where s.SUPPLIER_ID =:id and s.SUPPLIER_STATUS = 1 and s.SUPPLIER_NUMBER =:number", 
				nativeQuery = true) 
		Supplier checkIfSupplierWithSameNumberExists(@Param("id") int id,@Param("number") String number);
}
