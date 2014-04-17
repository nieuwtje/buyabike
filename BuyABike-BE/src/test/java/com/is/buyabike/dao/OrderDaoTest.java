package com.is.buyabike.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.order.Order;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/dao-context.xml"})
public class OrderDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductDao productDao;
	
	private Product product;
	
	@Before
	public void init() {
		product = new Product("name", "description", "image", 2.00, 2.00, 10);
		productDao.persist(product);
	}
	
	@Test
	public void testFindWithId() {
		Order expectedOrder = new Order();
		expectedOrder.addProduct(product);
		orderDao.persist(expectedOrder);
		Order actualOrder = orderDao.findOrderById(expectedOrder.getId());
		
		assertThat(actualOrder, is(expectedOrder));
	}

	@Test
	public void testListAll() {
		List<Order> expectedOrders = new ArrayList<Order>();
		Order order1 = new Order();
		order1.addProduct(product);
		orderDao.persist(order1);
		Order order2 = new Order();
		order2.addProduct(product);
		orderDao.persist(order2);
		expectedOrders.add(order1);
		expectedOrders.add(order2);

		List<Order> actualOrders = orderDao.listOrders();
		assertThat(actualOrders, is(expectedOrders));
	}

	@Test
	public void testListAllEager() {
		List<Order> expectedOrders = new ArrayList<Order>();
		Order order1 = new Order();
		order1.addProduct(product);
		orderDao.persist(order1);
		Order order2 = new Order();
		order2.addProduct(product);
		orderDao.persist(order2);
		expectedOrders.add(order1);
		expectedOrders.add(order2);
		
		List<Order> actualOrders = orderDao.listOrdersEager();
		assertThat(actualOrders, is(expectedOrders));
	}

	@Test
	public void testDelete() {
		Order expectedOrder = new Order();
		expectedOrder.addProduct(product);
		orderDao.persist(expectedOrder);
		long id = expectedOrder.getId();
		
		Order actualOrder = orderDao.removeOrder(expectedOrder);

		assertThat(actualOrder, is(expectedOrder));
		
		Order order2 = orderDao.findOrderById(id);
		assertNull(order2);
	}

	@Test
	public void testDeleteById() {
		Order expectedOrder = new Order();
		expectedOrder.addProduct(product);
		orderDao.persist(expectedOrder);
		
		Order actualOrder = orderDao.removeOrder(expectedOrder.getId());
		
		assertThat(actualOrder, is(expectedOrder));
	}

	@Test
	public void testUpdate() {
		Order order = new Order();
		order.addProduct(product);
		orderDao.persist(order);
		
		Order expectedOrder = orderDao.findOrderById(order.getId());
		expectedOrder.addProduct(product);
		Order actualOrder = orderDao.update(expectedOrder);
		
		assertThat(actualOrder, is(expectedOrder));
	}
}
