package org.net.erp.services;

import java.util.List;

import org.net.erp.model.Client;

public interface ClientService {

	void save(Client client);
    
    List<Client> listAll();
    
    void deleteClient(int id);
    
    Client getClientById(int id);
    
    int checkClientEntries(int id);
}
