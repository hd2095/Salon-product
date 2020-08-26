package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{

	@Query(value="SELECT * from schedule_tbl s where s.MASTER_ID =:id and SCHEDULE_DATE between :start and :end", 
			nativeQuery = true) 
	List<Schedule> findByMasterId(@Param("id") int id,@Param("start") String start,@Param("end") String end);
	
}
