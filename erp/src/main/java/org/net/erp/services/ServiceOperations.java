package org.net.erp.services;

import java.util.List;

import org.net.erp.model.Services;

public interface ServiceOperations {

	void save(Services service);

	List<Services> listAll();

	void deleteService(int id);

	Services getServiceById(int id);

	int checkServiceEntries(int id);
}
