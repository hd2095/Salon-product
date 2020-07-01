package org.net.erp.services;

import java.util.List;

import org.net.erp.model.Plan;
import org.net.erp.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlanService {
	@Autowired
    private PlanRepository repo;
     
    public List<Plan> listAll() {
        return repo.findAll();
    }
     
    public void save(Plan plan) {
        repo.save(plan);
    }
     
    public Plan get(int id) {
        return repo.findById(id).get();
    }
     
    public void delete(int id) {
        repo.deleteById(id);
    }
}
