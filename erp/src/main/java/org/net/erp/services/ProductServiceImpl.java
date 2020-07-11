package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.Product;
import org.net.erp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
    private ProductRepository repo;
	
	@Override
	public void save(Product product) {
		this.repo.save(product);
	}

	@Override
	public List<Product> listAll() {		
		return repo.findAll();
	}

	@Override
	public void deleteProduct(int id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public Product getProductById(int id) {
		Optional<Product> optional = repo.findById(id);
		Product product = null;
        if (optional.isPresent()) {
        	product = optional.get();
        }
		return product;
	}
}
