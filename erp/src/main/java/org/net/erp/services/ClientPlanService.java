package org.net.erp.services;

import java.util.List;

import org.net.erp.model.ClientPlan;

public interface ClientPlanService {

	void save(ClientPlan category);

	List<ClientPlan> listAll();

	void deleteClientPlan(int id);

	ClientPlan getClientPlanById(int id);
}
