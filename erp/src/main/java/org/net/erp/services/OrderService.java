package org.net.erp.services;

import java.util.List;

import org.net.erp.model.Order;

public interface OrderService {

	void save(Order order);
    
    List<Order> listAll();
    
    void deleteOrder(int id);
    
    Order getOrderById(int id);
 
    int checkOrderEntries(int id);
}
