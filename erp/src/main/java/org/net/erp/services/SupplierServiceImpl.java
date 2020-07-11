package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.Supplier;
import org.net.erp.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService{

	@Autowired
    private SupplierRepository repo;
	
	@Override
	public void save(Supplier supplier) {
		this.repo.save(supplier);
	}

	@Override
	public List<Supplier> listAll() {		
		return repo.findAll();
	}

	@Override
	public void deleteSupplier(int id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public Supplier getSupplierById(int id) {
		Optional<Supplier> optional = repo.findById(id);
		Supplier supplier = null;
        if (optional.isPresent()) {
        	supplier = optional.get();
        }
		return supplier;
	}

}
