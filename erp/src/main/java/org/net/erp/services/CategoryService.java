package org.net.erp.services;

import java.util.List;

import org.net.erp.model.Category;

public interface CategoryService {

	void save(Category category);

	List<Category> listAll();

	void deleteCategory(int id);

	Category getCategoryById(int id);
}
