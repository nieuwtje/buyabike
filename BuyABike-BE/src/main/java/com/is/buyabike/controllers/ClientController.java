package com.is.buyabike.controllers;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.buyabike.domain.client.Client;
import com.is.buyabike.domain.order.Order;
import com.is.buyabike.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public @ResponseBody boolean create(ServletRequest request, @RequestBody Client client) {
		try {
			service.create(client);
			login(request, client.getEmail(), client.getPassword());
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, params = {"email", "password"})
	public @ResponseBody boolean login(ServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) {
		HttpServletRequest req = (HttpServletRequest) request;
		
		Client client = service.loginClient(email, password);
		if (client != null) {
			req.getSession().setAttribute("client", client);
			
			System.out.println(client.getFirstName() + " " + client.getLastName() + " logged in!");
			return true;
		}
		
		return false;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Order> listOrders(ServletRequest request) {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			Client client = (Client) req.getSession().getAttribute("client");
		
			return client.getOrders();
		}
		catch (NullPointerException e) {
			return null;
		}
	}
}
