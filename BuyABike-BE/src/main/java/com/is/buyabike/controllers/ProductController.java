package com.is.buyabike.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.buyabike.domain.Product;

@Controller
@RequestMapping("/products")
public class ProductController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Product> listProducts(){
		
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 15; i++) {
			products.add(new Product("Fiets " + i,"Fiets Description", "url1",100.20));
		}
		return products;
		
	}
}
