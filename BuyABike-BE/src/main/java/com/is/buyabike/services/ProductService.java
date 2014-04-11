package com.is.buyabike.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.is.buyabike.domain.Address;
import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.Supplier;

@Service
public class ProductService {

	private List<Product> productTestData(){
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 15; i++) {
			int image = 500 + i;
			int stock = i;
			Product product = new Product("Fiets " + i,"Beschrijving " + i,"bike"+image,200.00,300.00,stock) ;
			product.setSupplier(new Supplier("Info Support",new Address("street","11", "City","State", "Country"),"website","0000000020"));
			product.addCategory(new Category("Bikes", "Just a Category with bikes"));
			products.add(product);
			
		}
		return products;
	}

	public List<Product> getProducts(){
		return productTestData();
	}
}
