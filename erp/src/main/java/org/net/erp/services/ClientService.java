package org.net.erp.services;

import java.util.List;

import org.net.erp.model.Client;
import org.net.erp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService {

	@Autowired
    private ClientRepository repo;
	
    public void save(Client client) {
        repo.save(client);
    }
    
    public List<Client> listAll() {
        return repo.findAll();
    }
}
