package org.net.erp.repository;

import java.math.BigDecimal;
import java.util.List;

import org.net.erp.model.AppointmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AppointmentDetailsRepository extends JpaRepository<AppointmentDetails, Integer>{

	/*
	 * @Modifying(clearAutomatically = true)
	 * 
	 * @Transactional
	 * 
	 * @Query( value =
	 * "insert into appointment_details_tbl (appointment_id,service_id,staff_id,service_cost,service_delete_status) values(:appointment_id,:service_id,:staff_id,:service_cost,:service_delete_status)"
	 * , nativeQuery = true ) void insertAppointmentDetails(@Param("appointment_id")
	 * Integer appointment_id,
	 * 
	 * @Param("service_id") Integer service_id,
	 * 
	 * @Param("staff_id") Integer staff_id,
	 * 
	 * @Param("service_cost") BigDecimal service_cost,
	 * 
	 * @Param("service_delete_status") int service_delete_status );
	 */
	
	@Query(value="SELECT * from appointment_details_tbl a where a.APPOINTMENT_ID =:id and a.SERVICE_DELETE_STATUS = 1", 
			nativeQuery = true) 
	List<AppointmentDetails> findByAppointmentId(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_details_tbl a where a.RECORD_ID =:id", 
			nativeQuery = true) 
	AppointmentDetails findByAppointmentDetailsId(@Param("id") int id);
}
