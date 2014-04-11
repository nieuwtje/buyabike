package com.is.buyabike.services;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.powermock.reflect.Whitebox.setInternalState;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.is.buyabike.dao.OrderDao;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.order.Order;
import com.is.buyabike.domain.order.OrderItem;

@RunWith(PowerMockRunner.class)
@PrepareForTest(OrderDao.class)
public class TestOrderService {
	private OrderService service;
	private OrderDao dao;
	
	@Before
	public void setUp() {
		service = new OrderService();
		dao = createMock(OrderDao.class);
		setInternalState(service, dao);
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
		
		expect(dao.listOrders()).andReturn(list);	
		replay(dao);
		
		assertThat(service.listOrders().size(), is(1));
	}
	
	@Test
	public void verifyThatTheServiceWillCallSaveOrderFromTheDAO() {
		Order order1 = new Order();
		order1.setId(1);
		
		expect(dao.saveOrder(order1)).andReturn(order1);
		replay(dao);
		
		assertThat(service.create(order1).getId(), is(order1.getId()));
	}
	
	@Test
	public void verifyThatTheServiceWillCallRemoveFromTheDAO() {
		Order order1 = new Order();
		order1.setId(1);
		
		expect(dao.removeOrder(order1)).andReturn(order1);
		replay(dao);
		
		assertThat(service.remove(order1).getId(), is(order1.getId()));
	}
	
	@Test
	public void verifyThatTheServiceWillCallRemoveByIdFromTheDAO() {
		Order order1 = new Order();
		order1.setId(1);
		
		expect(dao.removeOrder(order1.getId())).andReturn(order1);
		replay(dao);
		
		assertThat(service.remove(order1.getId()).getId(), is(order1.getId()));
	}
	
	@Test
	public void verifyThatTheServiceWillCallFindFromTheDAO() {
		Order order1 = new Order();
		order1.setId(1);
		
		expect(dao.findOrderById(order1.getId())).andReturn(order1);
		replay(dao);
		
		assertThat(service.find(order1.getId()).getId(), is(order1.getId()));
	}
}
