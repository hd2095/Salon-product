package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.ClientPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientPlanRepository extends JpaRepository<ClientPlan,Integer>{

	@Query(value="SELECT * from client_plan_tbl c where c.MASTER_ID =:id and c.PLAN_STATUS = 1", 
			nativeQuery = true) 
	List<ClientPlan> findByMasterId(@Param("id") int id);
}
