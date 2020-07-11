package org.net.erp.repository;

import java.util.List;

import org.net.erp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query(value="SELECT * from category_tbl c where c.MASTER_ID =:id", 
			nativeQuery = true) 
	List<Category> findByMasterId(@Param("id") int id);
}
