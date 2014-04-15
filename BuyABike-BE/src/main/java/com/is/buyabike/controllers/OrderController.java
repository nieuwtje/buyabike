package com.is.buyabike.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.buyabike.domain.order.Order;
import com.is.buyabike.domain.order.Order.OrderStatus;
import com.is.buyabike.services.MailService;
import com.is.buyabike.services.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(method = RequestMethod.PUT, consumes="application/json")
	public @ResponseBody boolean submitOrder(@RequestBody Order order) {
		try {
			service.persist(order);
			String to = "t.nieuwenhuys@hotmail.com"; // TODO to: client.getEmailAddress();
			mailService.sendMail("endcasebuyabike@gmail.com", to, "Order confirmation", "Order received!");
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value = "/{id}/{status}", method = RequestMethod.PUT)
	public @ResponseBody Order updateOrder(@PathVariable("id") long id, @PathVariable("status") String status) {
		Order order = service.find(id);
		order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
		return service.update(order);
	}
	
	@RequestMapping(method = RequestMethod.GET, headers = "accept=application/json")
	public @ResponseBody List<Order> getOrdersAsJSON() {
		return service.listOrdersEager();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Order getOrderById(@PathVariable("id") long id) {
		return service.find(id);
	}
	
	@RequestMapping(value = "/{id}/status", method = RequestMethod.GET)
	public @ResponseBody OrderStatus getStatusByIdOrder(@PathVariable("id") long id) {
		return service.find(id).getStatus();
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
