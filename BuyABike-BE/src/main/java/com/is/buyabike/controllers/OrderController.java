package com.is.buyabike.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.buyabike.domain.order.Order;
import com.is.buyabike.services.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@RequestMapping(method = RequestMethod.PUT)
	public void submitOrder(@RequestBody Order order) {
		service.persist(order);
	}
	
	@RequestMapping(method = RequestMethod.GET, headers = "accept=application/json")
	public @ResponseBody List<Order> getOrdersAsJSON() {
		return service.listOrders();
	}
	
	@RequestMapping(method = RequestMethod.GET, params = {"id"})
	public @ResponseBody Order getOrderById(@RequestParam("id") long id) {
		return service.find(id);
	}
}
