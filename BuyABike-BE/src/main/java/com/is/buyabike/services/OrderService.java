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
	
	public Order persist(Order order) {
		return dao.persist(order);
	}

	public List<Order> listOrders() {
		return dao.listOrders();
	}

	public Order find(long id) {
		return dao.findOrderById(id);
	}

	public Order remove(Order order) {
		return dao.removeOrder(order);
	}

	public Order remove(long id) {
		return dao.removeOrder(id);
	}
}
