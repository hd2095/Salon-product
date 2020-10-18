package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.Sales;
import org.net.erp.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SalesServiceImpl implements SalesService{
	@Autowired
    private SalesRepository repo;
	
	@Override
	public void save(Sales sales) {
		this.repo.save(sales);
	}

	@Override
	public List<Sales> listAll() {		
		return repo.findAll();
	}

	@Override
	public void deleteSales(int id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public Sales getSalesById(int id) {
		Optional<Sales> optional = repo.findById(id);
		Sales sales = null;
        if (optional.isPresent()) {
        	sales = optional.get();
        }
		return sales;
	}

	@Override
	public int checkSaleEntries(int id) {
		return repo.checkSaleEntries(id);
	}
}
