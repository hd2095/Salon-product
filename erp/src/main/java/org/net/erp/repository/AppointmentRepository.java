package org.net.erp.repository;

import java.util.Date;
import java.util.List;

import org.net.erp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1", 
			nativeQuery = true) 
	List<Appointment> findByMasterId(@Param("id") int id);

	@Query(value="SELECT * from appointment_tbl a where a.STAFF_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1", 
			nativeQuery = true) 
	List<Appointment> findByStaffId(@Param("id") int id);

	@Query(value="SELECT * from appointment_tbl a where a.STAFF_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 and a.APPOINTMENT_START_TIME =:time and a.APPOINTMENT_DATE =:date", 
			nativeQuery = true) 
	Appointment checkIfStaffHasAppointment(@Param("date") Date appointmentDate,@Param("time") String time,@Param("id") int id);
	
	@Query(value="SELECT MAX(APPOINTMENT_DATE) from appointment_tbl a where a.CLIENT_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1", 
			nativeQuery = true)
	Date getLastClientVisitDate(@Param("id") int id);
}
