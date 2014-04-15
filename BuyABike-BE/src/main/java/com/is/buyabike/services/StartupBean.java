package com.is.buyabike.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.is.buyabike.dao.CategoryDao;
import com.is.buyabike.dao.ProductDao;
import com.is.buyabike.dao.SupplierDao;
import com.is.buyabike.domain.Address;
import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.Supplier;
import com.is.buyabike.domain.order.Order;
import com.is.buyabike.domain.order.Order.OrderStatus;

@Component
public class StartupBean {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private OrderService orderService;	
	
	@PostConstruct
	public void init(){
		Category category1 = new Category("Mountainbikes", "Categorie mountainbikes");
		Category category2 = new Category("Racebikes","Categorie racingbikes");
		
		Product product1 = new Product("MTB 1", "MTB 1", "test",100.00,200.00,20);
		Product product2 = new Product("MTB 2", "MTB 2", "test",100.00,200.00,20);
		Product product3 = new Product("MTB 3", "MTB 3", "test",100.00,200.00,20);
		
		Supplier supplier = new Supplier("Fietsfabrikant",new Address("Street","2","City","State","Country"),"website","phone");
		
		supplierDao.persist(supplier);		
		
		
		product1.setSupplier(supplier);
		product2.setSupplier(supplier);
		product3.setSupplier(supplier);
		
		productDao.persist(product1);
		productDao.persist(product2);
		productDao.persist(product3);
		
		category1.addProduct(product1);
		category2.addProduct(product1);
		category1.addProduct(product2);
		category2.addProduct(product2);
		category1.addProduct(product3);
		category2.addProduct(product3);
		
		categoryDao.persist(category1);
		categoryDao.persist(category2);
		
		Order order = new Order();
		order.addProduct(product1);
		order.addProduct(product2);
		order.setStatus(OrderStatus.RECEIVED);
		
		orderService.persist(order);
	}
}
