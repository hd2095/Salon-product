package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.Client;
import org.net.erp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
    private ClientRepository repo;

	@Override
	public void save(Client client) {
		this.repo.save(client);
	}

	@Override
	public List<Client> listAll() {		
		return repo.findAll();
	}

	@Override
	public void deleteClient(int id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public Client getClientById(int id) {
		Optional<Client> optional = repo.findById(id);
		Client client = null;
        if (optional.isPresent()) {
        	client = optional.get();
        }
		return client;
	}

}
