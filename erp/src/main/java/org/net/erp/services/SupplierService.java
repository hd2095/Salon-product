package org.net.erp.services;

import java.util.List;

import org.net.erp.model.Supplier;

public interface SupplierService {

	void save(Supplier supplier);
    
    List<Supplier> listAll();
    
    void deleteSupplier(int id);
    
    Supplier getSupplierById(int id);
    
    int checkSupplierEntries(int id);
}
