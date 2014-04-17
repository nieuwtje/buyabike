package com.is.buyabike.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.buyabike.domain.order.Order;
import com.is.buyabike.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public @ResponseBody String create() {
		service.create(null);
		return "<html><body>hoi</body></html>";
	}

	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public @ResponseBody List<Order> listOrders() {
		return null; // TODO: client.getOrders();
	}
}
