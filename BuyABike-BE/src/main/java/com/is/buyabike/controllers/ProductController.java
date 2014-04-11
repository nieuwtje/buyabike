package com.is.buyabike.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.buyabike.domain.Product;
import com.is.buyabike.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getProducts(){	
		return productService.getProducts();		
	}
	
	
}
