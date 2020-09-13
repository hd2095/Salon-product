package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

	@Query(value="SELECT * from invoice_tbl i where i.MASTER_ID =:id", 
			nativeQuery = true) 
	List<Invoice> findByMasterId(@Param("id") int id);
	
}
