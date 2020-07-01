package org.net.erp.repository;

import org.net.erp.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Integer>{

	Master findByMasterId(int id);
	
}
