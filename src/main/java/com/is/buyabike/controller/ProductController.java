package com.is.buyabike.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.is.buyabike.aspects.NotifyClients;
import com.is.buyabike.model.Product;
import com.is.buyabike.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewProduct() {
	    return "products";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProducts() {
	    return service.getProducts();
	}
	
	@NotifyClients
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public @ResponseBody Product update(@PathVariable int id, @RequestBody Product product) {
	    product.setId(id);
	    Product out = service.updateProduct(product);
	    return out;
	}
	
	@NotifyClients
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public @ResponseBody Product add(@RequestBody Product product) {
	    Product out = service.addProduct(product);
	    return out;
	}
	
	@NotifyClients
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		Product task = new Product();
	    task.setId(id);
	    service.deleteProduct(task);
	}
}
