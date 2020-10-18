package org.net.erp.services;

import java.util.List;
import java.util.Optional;

import org.net.erp.model.Order;
import org.net.erp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
    private OrderRepository repo;
	
	@Override
	public void save(Order order) {
		this.repo.save(order);
	}

	@Override
	public List<Order> listAll() {
		return this.repo.findAll();
	}

	@Override
	public void deleteOrder(int id) {
		this.repo.deleteById(id);
	}

	@Override
	public Order getOrderById(int id) {
		Optional<Order> optional = repo.findById(id);
		Order order = null;
        if (optional.isPresent()) {
        	order = optional.get();
        }
		return order;
	}

	@Override
	public int checkOrderEntries(int id) {
		return repo.checkOrderEntries(id);
	}
}
