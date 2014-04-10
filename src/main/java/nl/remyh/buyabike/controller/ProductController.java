package nl.remyh.buyabike.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import nl.remyh.buyabike.aspects.NotifyClients;
import nl.remyh.buyabike.model.Product;
import nl.remyh.buyabike.service.ProductService;
import org.springframework.http.*;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewProducts() {
	    return "products";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProducts() {
	    return service.getProducts();
	}

	@NotifyClients
	@RequestMapping(value = "/list/{id}", method = RequestMethod.PUT)
	public @ResponseBody Product update(@PathVariable int id, @RequestBody Product product) {
	    product.setId(id);
	    Product out = service.updateProduct(product);
	    return out;
	}

	@NotifyClients
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Product add(@RequestBody Product idea) {
	    Product out = service.addProduct(idea);
	    return out;
	}

	@NotifyClients
	@RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
	    Product product = new Product();
	    product.setId(id);
	    service.deleteProduct(product);
	}
}
