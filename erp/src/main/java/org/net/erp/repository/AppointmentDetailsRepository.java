package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.AppointmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentDetailsRepository extends JpaRepository<AppointmentDetails, Integer>{
	
	@Query(value="SELECT * from appointment_details_tbl a where a.APPOINTMENT_ID =:id and a.SERVICE_DELETE_STATUS = 1", 
			nativeQuery = true) 
	List<AppointmentDetails> findByAppointmentId(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_details_tbl a where a.RECORD_ID =:id", 
			nativeQuery = true) 
	AppointmentDetails findByAppointmentDetailsId(@Param("id") int id);
	
	@Query(value="SELECT SERVICE_ID,COUNT(*) from (SELECT MASTER_ID,SERVICE_ID,APPOINTMENT_DELETE_STATUS from appointment_tbl t1 inner join appointment_details_tbl t2 on t1.APPOINTMENT_ID = t2.APPOINTMENT_ID where master_id =:id and APPOINTMENT_DELETE_STATUS = 1 and APPOINTMENT_STATUS = \"Completed\" )  sub group by SERVICE_ID order by COUNT(*) desc limit 5", 
			nativeQuery = true) 
	List<String> getMostUsedService(@Param("id") int id);
}
