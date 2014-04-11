package com.is.buyabike.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.order.Order;
import com.is.buyabike.domain.order.OrderItem;
import com.is.buyabike.services.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@RequestMapping(method = RequestMethod.PUT)
	public void submitOrder(@RequestBody Order order) {
		service.create(order);
	}
	
	// TODO - doesn't work???
	@RequestMapping(method = RequestMethod.GET, headers = "accept=application/xml")
	public @ResponseBody List<Order> getOrdersAsXML() {
//		final List<Order> list = new ArrayList<Order>();
//		Order order1 = new Order();
//		Product p1 = new Product("test", "test product", "url.jpg", 10.50, 10.50, 5);
//		Product p2 = new Product("test2", "test product2", "url.jpg", 12.50, 12.50, 20);
//		Product p3 = new Product("test3", "test product3", "url.jpg", 8.50, 8.50, 15);
//		OrderItem item1 = new OrderItem(p1, 5);
//		order1.addOrderItem(item1);
//		OrderItem item2 = new OrderItem(p2, 2);
//		order1.addOrderItem(item2);
//		OrderItem item3 = new OrderItem(p3, 3);
//		order1.addOrderItem(item3);
//		list.add(order1);
//		
//		return list;
		
		return service.listOrders();
	}
	
	@RequestMapping(method = RequestMethod.GET, headers = "accept=application/json")
	public @ResponseBody List<Order> getOrdersAsJSON() {
		return getOrdersAsXML();
	}
	
	@RequestMapping(method = RequestMethod.GET, params = {"id"})
	public @ResponseBody Order getOrderById(@RequestParam("id") long id) {
		return service.find(id);
	}
}
