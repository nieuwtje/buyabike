package com.is.buyabike.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import junitx.util.PrivateAccessor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.is.buyabike.domain.order.Order;

@RunWith(MockitoJUnitRunner.class)
public class OrderDaoTest {

	private OrderDao orderDao;
	@Mock
	private EntityManager entityManagerMock;
	@Mock
	TypedQuery<Order> query;
	@Mock
	CriteriaBuilder cb;
	@Mock
	CriteriaQuery<Order> cq;
	@Mock
	Root<Order> root;
	
	@Before
	public void setUp() throws Exception {
		Order order = new Order();
		order.setId(1);
		
		orderDao = new OrderDao();
		PrivateAccessor.setField(orderDao, "em", entityManagerMock);
		
		when(entityManagerMock.getCriteriaBuilder()).thenReturn(cb);
		when(cb.createQuery(Order.class)).thenReturn(cq);
		when(cq.from(Order.class)).thenReturn(root);
		when(cq.select(root)).thenReturn(cq);
		when(entityManagerMock.createQuery(cq)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(order);
	}

	@Test
	public void testFindWithId() {
		Order expectedOrder = new Order();
		when(entityManagerMock.createQuery(anyString(), eq(Order.class))).thenReturn(query);
		when(query.getSingleResult()).thenReturn(expectedOrder);
		Order actualOrder = orderDao.findOrderById(1);
		verify(query).getSingleResult();
		assertThat(actualOrder, is(expectedOrder));
	}

	@Test
	public void testPersist() {
		Order expectedOrder = new Order();
		orderDao.persist(expectedOrder);
		verify(entityManagerMock).persist(any(Order.class));
	}

	@Test
	public void testListAll() {
		List<Order> expectedOrders = new ArrayList<Order>();
		expectedOrders.add(new Order());
		expectedOrders.add(new Order());

		when(entityManagerMock.createQuery(anyString(), eq(Order.class))).thenReturn(query);
		when(query.getResultList()).thenReturn(expectedOrders);
		List<Order> actualOrders = orderDao.listOrders();
		verify(entityManagerMock).createQuery(anyString(), eq(Order.class));
		verify(query).getResultList();
		assertThat(actualOrders, is(expectedOrders));
	}

	@Test
	public void testListAllEager() {
		List<Order> expectedOrders = new ArrayList<Order>();
		expectedOrders.add(new Order());
		expectedOrders.add(new Order());

		when(entityManagerMock.createQuery(anyString(), eq(Order.class))).thenReturn(query);
		when(query.getResultList()).thenReturn(expectedOrders);
		List<Order> actualOrders = orderDao.listOrdersEager();
		verify(query).getResultList();
		assertThat(actualOrders, is(expectedOrders));
	}

	@Test
	public void testDelete() {
		Order order = new Order();
		when(entityManagerMock.find(Order.class, order.getId())).thenReturn(order);
		orderDao.removeOrder(order);
		verify(entityManagerMock).remove(any(Order.class));
	}

	@Test
	public void testDeleteById() {
		Order order = new Order();
		order.setId(1);
		when(entityManagerMock.find(Order.class, order.getId())).thenReturn(order);
		orderDao.removeOrder(order.getId());
		verify(entityManagerMock).remove(any(Order.class));
	}

	@Test
	public void testUpdate() {
		Order expectedOrder = new Order();
		orderDao.update(expectedOrder);
		verify(entityManagerMock).merge(any(Order.class));
	}

}
