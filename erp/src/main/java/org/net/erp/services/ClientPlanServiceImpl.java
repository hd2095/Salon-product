package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.ClientPlan;
import org.net.erp.repository.ClientPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClientPlanServiceImpl implements ClientPlanService {

	@Autowired
	ClientPlanRepository clienPlanRepository;
	
	@Override
	public void save(ClientPlan clientPlan) {
		// TODO Auto-generated method stub
		this.clienPlanRepository.save(clientPlan);
	}

	@Override
	public List<ClientPlan> listAll() {
		// TODO Auto-generated method stub
		return clienPlanRepository.findAll();
	}

	@Override
	public void deleteClientPlan(int id) {
		// TODO Auto-generated method stub
		this.clienPlanRepository.deleteById(id);
	}

	@Override
	public ClientPlan getClientPlanById(int id) {
		Optional<ClientPlan> optional = clienPlanRepository.findById(id);
		ClientPlan plan = null;
		if (optional.isPresent()) {
			plan = optional.get();
		}
		return plan;
	}

}
