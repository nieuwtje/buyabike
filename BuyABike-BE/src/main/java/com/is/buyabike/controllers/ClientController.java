package com.is.buyabike.controllers;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.is.buyabike.domain.client.Client;
import com.is.buyabike.domain.order.Order;
import com.is.buyabike.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public @ResponseBody boolean create(ServletRequest request, @RequestBody Client client) {
		try {
			service.create(client);
			login(request, client);
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void login(ServletRequest request, @RequestBody Client client) {
		HttpServletRequest req = (HttpServletRequest) request;
		req.getSession().setAttribute("client", client);
	}

	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public @ResponseBody List<Order> listOrders(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		Client client = (Client) req.getSession().getAttribute("client");
		return client.getOrders();
	}
}
