package org.net.erp.repository;

import java.util.Date;
import java.util.List;

import org.net.erp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by APPOINTMENT_DATE", 
			nativeQuery = true) 
	List<Appointment> findByMasterIdAsc(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by APPOINTMENT_DATE desc", 
			nativeQuery = true) 
	List<Appointment> findByMasterId(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by APPOINTMENT_STATUS desc", 
			nativeQuery = true) 
	List<Appointment> findByStatus(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by APPOINTMENT_STATUS", 
			nativeQuery = true) 
	List<Appointment> findByStatusAsc(@Param("id") int id);
	
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
	
	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by APPOINTMENT_EXPECTED_TOTAL desc", 
			nativeQuery = true) 
	List<Appointment> findByTotal(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by APPOINTMENT_EXPECTED_TOTAL", 
			nativeQuery = true) 
	List<Appointment> findByTotalAsc(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by APPOINTMENT_START_TIME desc", 
			nativeQuery = true) 
	List<Appointment> findByAppointmentTime(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by APPOINTMENT_START_TIME", 
			nativeQuery = true) 
	List<Appointment> findByAppointmentTimeAsc(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by CLIENT_ID desc", 
			nativeQuery = true) 
	List<Appointment> findByClientId(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_tbl a where a.MASTER_ID =:id and a.APPOINTMENT_DELETE_STATUS = 1 order by CLIENT_ID", 
			nativeQuery = true) 
	List<Appointment> findByClientIdAsc(@Param("id") int id);
	
	@Query(value="SELECT * from appointment_tbl inner join client_tbl on appointment_tbl.client_id = client_tbl.client_id where appointment_tbl.MASTER_ID =:id and appointment_tbl.APPOINTMENT_DELETE_STATUS = 1 and client_tbl.FULL_NAME LIKE CONCAT('%',:text,'%')", 
			nativeQuery = true) 
	List<Appointment> findByClientName(@Param("id") int id,@Param("text") String text);
	
}
