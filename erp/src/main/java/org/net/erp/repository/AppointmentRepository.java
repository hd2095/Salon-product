package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id", 
			nativeQuery = true) 
	List<Appointment> findByMasterId(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_tbl a where a.STAFF_ID =:id", 
			nativeQuery = true) 
	List<Appointment> findByStaffId(@Param("id") int id);
	
}
