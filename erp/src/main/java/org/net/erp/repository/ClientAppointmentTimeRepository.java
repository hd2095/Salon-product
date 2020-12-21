package org.net.erp.repository;

import java.util.Date;
import java.util.List;

import org.net.erp.model.ClientAppointmentTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ClientAppointmentTimeRepository  extends JpaRepository<ClientAppointmentTime, Integer> {
	
	@Query(value="SELECT * from client_appointment_time_tbl c where c.CLIENT_ID =:id and c.APPOINTMENT_DATE = :date", 
			nativeQuery = true) 
	List<ClientAppointmentTime> checkIfClientHasAppointment(@Param("id") int id,@Param("date") Date date);

	@Transactional
	@Modifying
	@Query(value="delete from client_appointment_time_tbl  where APPOINTMENT_ID =:id", 
	nativeQuery = true) 
	void deleteClientEntry(@Param("id") int id);
}
