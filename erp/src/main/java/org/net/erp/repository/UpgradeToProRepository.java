package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.UpgradeToPro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UpgradeToProRepository extends JpaRepository<UpgradeToPro, Integer>{

	@Query(value="SELECT * from upgrade_to_pro_tbl", 
			nativeQuery = true) 
	List<UpgradeToPro> findByRecordId(@Param("id") int id);
	
}
