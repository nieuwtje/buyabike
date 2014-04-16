package com.is.buyabike.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junitx.util.PrivateAccessor;

import org.junit.Before;
import org.junit.Test;

import com.is.buyabike.dao.OrderDao;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.order.Order;
import com.is.buyabike.domain.order.OrderItem;

public class TestOrderService {
	private OrderService service;
	private OrderDao dao;
	
	@Before
	public void setUp() throws NoSuchFieldException {
		service = new OrderService();
		dao = mock(OrderDao.class);
		PrivateAccessor.setField(service, "dao", dao);
	}
	
	@Test
	public void verifyThatTheServiceWillCallListOrdersFromTheDAO() {
		final List<Order> list = new ArrayList<Order>();
		Order order1 = new Order();
		Product p1 = new Product("test", "test product", "url.jpg", 10.50, 10.50, 5);
		Product p2 = new Product("test2", "test product2", "url.jpg", 12.50, 12.50, 20);
		Product p3 = new Product("test3", "test product3", "url.jpg", 8.50, 8.50, 15);
		OrderItem item1 = new OrderItem(p1, 5);
		order1.addOrderItem(item1);
		OrderItem item2 = new OrderItem(p2, 2);
		order1.addOrderItem(item2);
		OrderItem item3 = new OrderItem(p3, 3);
		order1.addOrderItem(item3);
		list.add(order1);
		
		when(dao.listOrders()).thenReturn(list);	
		
		assertThat(service.listOrders().size(), is(1));
		verify(dao).listOrders();
	}
	
	@Test
	public void verifyThatTheServiceWillCallListOrdersEagerFromTheDAO() {
		final List<Order> list = new ArrayList<Order>();
		Order order1 = new Order();
		Product p1 = new Product("test", "test product", "url.jpg", 10.50, 10.50, 5);
		Product p2 = new Product("test2", "test product2", "url.jpg", 12.50, 12.50, 20);
		Product p3 = new Product("test3", "test product3", "url.jpg", 8.50, 8.50, 15);
		OrderItem item1 = new OrderItem(p1, 5);
		order1.addOrderItem(item1);
		OrderItem item2 = new OrderItem(p2, 2);
		order1.addOrderItem(item2);
		OrderItem item3 = new OrderItem(p3, 3);
		order1.addOrderItem(item3);
		list.add(order1);
		
		when(dao.listOrdersEager()).thenReturn(list);	
		
		assertThat(service.listOrdersEager().size(), is(1));
		verify(dao).listOrdersEager();
	}
	
	@Test
	public void verifyThatTheServiceWillCallPersistFromTheDAO() {
		Order order1 = new Order();
		order1.setId(1);
		
		when(dao.persist(order1)).thenReturn(order1);
		
		assertThat(service.persist(order1).getId(), is(order1.getId()));
		verify(dao).persist(order1);
	}
	
	@Test
	public void verifyThatTheServiceWillCallUpdateFromTheDAO() {
		Order order1 = new Order();
		order1.setId(1);
		
		when(dao.update(order1)).thenReturn(order1);
		
		assertThat(service.update(order1).getId(), is(order1.getId()));
		verify(dao).update(order1);
	}
	
	@Test
	public void verifyThatTheServiceWillCallRemoveFromTheDAO() {
		Order order1 = new Order();
		order1.setId(1);
		
		when(dao.removeOrder(order1)).thenReturn(order1);
		
		assertThat(service.remove(order1).getId(), is(order1.getId()));
		verify(dao).removeOrder(order1);
	}
	
	@Test
	public void verifyThatTheServiceWillCallRemoveByIdFromTheDAO() {
		Order order1 = new Order();
		order1.setId(1);
		
		when(dao.removeOrder(order1.getId())).thenReturn(order1);
		
		assertThat(service.remove(order1.getId()).getId(), is(order1.getId()));
		verify(dao).removeOrder(order1.getId());
	}
	
	@Test
	public void verifyThatTheServiceWillCallFindFromTheDAO() {
		Order order1 = new Order();
		order1.setId(1);
		
		when(dao.findOrderById(order1.getId())).thenReturn(order1);
		
		assertThat(service.find(order1.getId()).getId(), is(order1.getId()));
		verify(dao).findOrderById(order1.getId());
	}
}
