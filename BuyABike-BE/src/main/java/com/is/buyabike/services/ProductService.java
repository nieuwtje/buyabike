package com.is.buyabike.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.buyabike.dao.ProductDao;
import com.is.buyabike.domain.Address;
import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.Supplier;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	public List<Product> getProducts(){
		return productDao.findAll();
	}
	
	public Product getProductById(long id){
		return productDao.findWithId(id);
	}
	
	public Product updateProduct(Product product){
		return productDao.update(product);
	}
	
	public void removeProduct(Product product){
		productDao.delete(product);
	}
	
	public void saveProduct(Product product){
		productDao.persist(product);
	}
}
