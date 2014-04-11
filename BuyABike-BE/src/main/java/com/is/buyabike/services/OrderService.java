package com.is.buyabike.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.buyabike.dao.OrderDAO;
import com.is.buyabike.domain.order.Order;

@Service
public class OrderService {
	@Autowired
	private OrderDAO dao;
	
	public void create(Order order) {
		dao.saveOrder(order);
	}

	public List<Order> listOrders() {
		return dao.listOrders();
	}
}