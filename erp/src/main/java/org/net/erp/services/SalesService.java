package org.net.erp.services;

import java.util.List;

import org.net.erp.model.Sales;

public interface SalesService {

	void save(Sales sales);

	List<Sales> listAll();

	void deleteSales(int id);

	Sales getSalesById(int id);

	int checkSaleEntries(int id);
	
}
