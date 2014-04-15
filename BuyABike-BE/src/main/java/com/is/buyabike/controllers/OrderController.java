package com.is.buyabike.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.buyabike.domain.order.Order;
import com.is.buyabike.domain.order.Order.OrderStatus;
import com.is.buyabike.services.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public void submitOrder(@RequestBody Order order) {
		service.persist(order);
	}
	
	@RequestMapping(value = "/{id}/{status}", method = RequestMethod.PUT, consumes = "application/json")
	public void updateOrder(@PathVariable("id") long id, @PathVariable("status") OrderStatus status) {
		Order order = service.find(id);
		order.setStatus(status);
		service.update(order);
	}
	
	@RequestMapping(method = RequestMethod.GET, headers = "accept=application/json")
	public @ResponseBody List<Order> getOrdersAsJSON() {
		return service.listOrdersEager();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Order getOrderById(@PathVariable("id") long id) {
		return service.find(id);
	}
	
	@RequestMapping(value = "/statusses", method = RequestMethod.GET)
	public @ResponseBody OrderStatus[] getOrderStatusses() {
		return OrderStatus.values();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Order removeOrderById(@PathVariable("id") long id) {
		return service.remove(id);
	}
}
