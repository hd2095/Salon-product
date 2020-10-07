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
	
	@Query(value="SELECT * from invoice_tbl i where i.MASTER_ID =:id and i.APPOINTMENT_ID IS NOT null", 
			nativeQuery = true) 
	List<Invoice> findByMasterIdForAppointments(@Param("id") int id);
	
	@Query(value="SELECT * from invoice_tbl i where i.MASTER_ID =:id and i.SALE_ID IS NOT null", 
			nativeQuery = true) 
	List<Invoice> findByMasterIdForSale(@Param("id") int id);
	
	@Query(value="SELECT * from invoice_tbl i where i.APPOINTMENT_ID =:id", 
			nativeQuery = true) 
	Invoice findInvoiceByAppointmentId(@Param("id") int id);
	
	
}
