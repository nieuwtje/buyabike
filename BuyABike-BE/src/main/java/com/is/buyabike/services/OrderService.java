package com.is.buyabike.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.buyabike.dao.OrderDao;
import com.is.buyabike.domain.order.Order;

@Service
public class OrderService {
	@Autowired
	private OrderDao dao;
	
	public void create(Order order) {
		dao.saveOrder(order);
	}

	public List<Order> listOrders() {
		return dao.listOrders();
	}

	public Order findOrderById(long id) {
		return dao.findOrderById(id);
	}

	public void removeOrder(Order order) {
		dao.removeOrder(order);
	}

	public void removeOrder(long id) {
		dao.removeOrder(id);
	}
}
