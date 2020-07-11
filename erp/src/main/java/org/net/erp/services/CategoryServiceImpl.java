package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.Category;
import org.net.erp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repo;

	@Override
	public void save(Category category) {
		this.repo.save(category);
	}

	@Override
	public List<Category> listAll() {		
		return repo.findAll();
	}

	@Override
	public void deleteCategory(int id) {
		this.repo.deleteById(id);

	}

	@Override
	public Category getCategoryById(int id) {
		Optional<Category> optional = repo.findById(id);
		Category category = null;
		if (optional.isPresent()) {
			category = optional.get();
		}
		return category;
	}


}
