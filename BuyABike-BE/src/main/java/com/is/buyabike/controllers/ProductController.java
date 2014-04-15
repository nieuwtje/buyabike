package com.is.buyabike.controllers;

import java.util.ArrayList;
import java.util.List;







import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Product> getAllProducts(){	
		List<Product> products= productService.getProducts();	
		return products;
				
	}
	
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public @ResponseBody Product getProduct(@PathVariable long id){
		return productService.getProductById(id);
	}
	
	@RequestMapping(value="/{id}/addstock/{amount}",method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void updateProductStock(@PathVariable("id") long id,@PathVariable("amount") int amount){
		Product product = productService.getProductById(id);
		product.incrementStock(amount);
		productService.updateProduct(product);
	}
	
	
}
