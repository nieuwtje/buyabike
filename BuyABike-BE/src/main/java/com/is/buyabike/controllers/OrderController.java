package com.is.buyabike.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.buyabike.domain.order.Order;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@RequestMapping(method = RequestMethod.PUT)
	public void submitOrder(@RequestBody Order order) {

	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String testOrder() {
		return "<html><body>hoi</body></html>";
	}
}
