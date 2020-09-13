package org.net.erp.repository;

import org.net.erp.model.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Integer> {

	@Query(value="SELECT * from invoice_details_tbl i where i.INVOICE_ID =:id", 
			nativeQuery = true) 
	InvoiceDetails findByInvoiceId(@Param("id") int id);
}
