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

import com.is.buyabike.dao.OrderDAO;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.order.Order;
import com.is.buyabike.domain.order.OrderItem;

@RunWith(PowerMockRunner.class)
@PrepareForTest(OrderDAO.class)
public class TestOrderService {
	private OrderService service;
	private OrderDAO dao;
	
	@Before
	public void before() throws Throwable {
		service = new OrderService();
		dao = createMock(OrderDAO.class);
		setInternalState(service, dao);
	}
	
	@Test
	public void verifyThatTheServiceWillCallTheDAO() {
		final List<Order> list = new ArrayList<Order>();
		Order order1 = new Order();
		Product p1 = new Product("test", "test product", "url.jpg", 10.50);
		Product p2 = new Product("test2", "test product2", "url.jpg", 12.50);
		Product p3 = new Product("test3", "test product3", "url.jpg", 8.50);
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
}
