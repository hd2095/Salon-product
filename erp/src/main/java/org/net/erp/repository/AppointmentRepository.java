package org.net.erp.repository;

import java.util.Date;
import java.util.List;

import org.net.erp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by APPOINTMENT_DATE desc", 
			nativeQuery = true) 
	List<Appointment> findByMasterId(@Param("id") int id);

	@Query(value="SELECT * from appointment_tbl a where a.STAFF_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1", 
			nativeQuery = true) 
	List<Appointment> findByStaffId(@Param("id") int id);

	@Query(value="SELECT * from appointment_tbl a where a.STAFF_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 and a.APPOINTMENT_DATE =:date", 
			nativeQuery = true) 
	List<Appointment> checkIfStaffHasAppointment(@Param("date") Date appointmentDate,@Param("id") int id);
	
	@Query(value="SELECT MAX(APPOINTMENT_DATE) from appointment_tbl a where a.CLIENT_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1", 
			nativeQuery = true)
	Date getLastClientVisitDate(@Param("id") int id);
	
	@Query(value="SELECT COUNT(*) from appointment_tbl a where a.MASTER_ID =:id", 
			nativeQuery = true)
	int checkAppointmentEntries(@Param("id") int id);
	
}
