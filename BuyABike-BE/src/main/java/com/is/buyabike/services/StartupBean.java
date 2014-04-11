package com.is.buyabike.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.InitBinder;

import com.is.buyabike.dao.CategoryDao;
import com.is.buyabike.dao.ProductDao;
import com.is.buyabike.dao.SupplierDao;
import com.is.buyabike.domain.Address;
import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.Supplier;

@Component
public class StartupBean {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SupplierDao supplierDao;
	
	@PostConstruct
	public void init(){
		Category category1 = new Category("Mountainbikes", "Categorie mountainbikes");
		Category category2 = new Category("Racebikes","Categorie racingbikes");
		
		Product product1 = new Product("MTB 1", "MTB 1", "test",100.00,200.00,20);
		Product product2 = new Product("MTB 2", "MTB 2", "test",100.00,200.00,20);
		Product product3 = new Product("MTB 3", "MTB 3", "test",100.00,200.00,20);
		
		Supplier supplier = new Supplier("Fietsfabrikant",new Address("Street","2","City","State","Country"),"website","phone");
		
		supplierDao.persist(supplier);
		categoryDao.persist(category1);
		categoryDao.persist(category2);
		
		product1.setSupplier(supplier);
		product2.setSupplier(supplier);
		product3.setSupplier(supplier);
		product1.setCategory(category1);
		product2.setCategory(category2);
		product3.setCategory(category1);
		
		productDao.persist(product1);
		productDao.persist(product2);
		productDao.persist(product3);
		
	}
}
