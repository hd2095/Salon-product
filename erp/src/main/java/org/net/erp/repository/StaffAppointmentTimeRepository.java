package org.net.erp.repository;

import java.util.Date;
import java.util.List;
import org.net.erp.model.StaffAppointmentTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StaffAppointmentTimeRepository extends JpaRepository<StaffAppointmentTime, Integer> {

	@Query(value="SELECT * from staff_appointment_time_tbl s where s.STAFF_ID =:id and s.APPOINTMENT_DATE = :date", 
			nativeQuery = true) 
	List<StaffAppointmentTime> checkIfStaffHasAppointment(@Param("id") int id,@Param("date") Date date);

	@Transactional
	@Modifying
	@Query(value="delete from staff_appointment_time_tbl where APPOINTMENT_DETAILS_ID =:id", 
	nativeQuery = true) 
	void deleteStaffEntry(@Param("id") int id);
}
