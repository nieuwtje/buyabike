package com.is.buyabike.controllers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junitx.util.PrivateAccessor;

import org.junit.Before;
import org.junit.Test;

import com.is.buyabike.domain.Address;
import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.Supplier;
import com.is.buyabike.domain.client.Client;
import com.is.buyabike.domain.order.Order;
import com.is.buyabike.domain.order.Order.OrderStatus;
import com.is.buyabike.services.ClientService;
import com.is.buyabike.services.MailService;
import com.is.buyabike.services.OrderService;

public class OrderControllerTest {

	private OrderController orderController;
	private OrderService orderServiceMock;
	private MailService mailServiceMock;
	
	@Before
	public void setUp() throws Exception {
		orderController = new OrderController();
		orderServiceMock = mock(OrderService.class);
		mailServiceMock = mock(MailService.class);
		PrivateAccessor.setField(orderController, "service", orderServiceMock);
		PrivateAccessor.setField(orderController, "mailService", mailServiceMock);

	}

	private List<Order> getOrdersForTest() {
		List<Order> orders = new ArrayList<Order>();
		Category category1 = new Category("Mountainbikes", "Categorie mountainbikes");
		Category category2 = new Category("Racebikes", "Categorie racingbikes");

		Product product1 = new Product("MTB 1", "MTB 1", "test", 100.00, 200.00, 20);
		Product product2 = new Product("MTB 2", "MTB 2", "test", 100.00, 200.00, 20);
		Product product3 = new Product("MTB 3", "MTB 3", "test", 100.00, 200.00, 20);

		Supplier supplier = new Supplier("Fietsfabrikant", new Address("Street", "2", "City", "State", "Country"), "website", "phone");

		product1.setSupplier(supplier);
		product2.setSupplier(supplier);
		product3.setSupplier(supplier);

		product1.addCategory(category1);
		product2.addCategory(category1);
		product3.addCategory(category1);

		product2.addCategory(category2);
		product3.addCategory(category2);

		Address address = new Address("krommeweg", "123", "Zuid-Laren", "Utrecht", "Nederland");
		Client client = new Client("Berend", "Botje", "berend@botje.nl", address, "pw");
		
		Order order1 = new Order(client);
		order1.setId(1);
		order1.addProduct(product1);
		order1.addProduct(product1);
		order1.addProduct(product1);
		order1.addProduct(product2);
		order1.addProduct(product2);

		Order order2 = new Order(client);
		order2.setId(2);
		order2.addProduct(product1);
		order2.addProduct(product3);
		order2.addProduct(product2);
		order2.addProduct(product2);
		order2.addProduct(product3);

		Order order3 = new Order(client);
		order3.setId(3);
		order3.addProduct(product2);
		order3.addProduct(product2);
		order3.addProduct(product1);
		order3.addProduct(product1);
		order3.addProduct(product3);

		orders.add(order1);
		orders.add(order2);
		orders.add(order3);

		return orders;
	}
	
	@Test
	public void submitOrderShouldPersistANewOrderToTheService() {
		Order expectedOrder = getOrdersForTest().get(0);
		
		when(orderServiceMock.persist(expectedOrder)).thenReturn(expectedOrder);
		boolean result = orderController.submitOrder(expectedOrder);
		verify(orderServiceMock).persist(expectedOrder);
		
		assert(result);
	}
	
	@Test
	public void updateOrderShouldChangeTheStatusOfTheOrder() {
		Order expectedOrder = getOrdersForTest().get(0);
		
		when(orderServiceMock.find(anyLong())).thenReturn(expectedOrder);
		orderController.updateOrder(anyLong(), "onroute");
		verify(orderServiceMock).find(anyLong());
		verify(orderServiceMock).update(expectedOrder);
		assertThat(expectedOrder.getStatus(), is(OrderStatus.ONROUTE));
	}

	@Test
	public void getOrderShouldReturnAllOrdersWithOrderItemsAndTheirProduct() {
		List<Order> expectedOrders = getOrdersForTest();
		
		when(orderServiceMock.listOrdersEager()).thenReturn(expectedOrders);
		List<Order> actualOrders = orderController.getOrders();
		verify(orderServiceMock).listOrdersEager();
		assertThat(actualOrders, is(expectedOrders));
	}

	@Test
	public void getOrderByIdShouldReturnTheOrderWithTheGivenId() {
		Order expectedOrder = getOrdersForTest().get(0);
		
		when(orderServiceMock.find(anyLong())).thenReturn(expectedOrder);
		Order actualOrder = orderController.getOrderById(1);
		verify(orderServiceMock).find(anyLong());
		assertThat(actualOrder, is(expectedOrder));
	}
	
	@Test
	public void getStatusByOrderIdShouldReturnTheStatusOfTheOrderWithTheGivenId() {
		Order expectedOrder = getOrdersForTest().get(0);

		when(orderServiceMock.find(anyLong())).thenReturn(expectedOrder);
		OrderStatus actualStatus = orderController.getStatusByOrderId(1);
		verify(orderServiceMock).find(anyLong());
		assertThat(actualStatus, is(expectedOrder.getStatus()));
	}

	@Test
	public void getOrderStatussesShouldReturnAllPossibleOrderStatusses() {
		OrderStatus[] expectedStatusses = OrderStatus.values();
		OrderStatus[] actualStatusses = orderController.getOrderStatusses();
		assertThat(actualStatusses, is(expectedStatusses));
	}
	
	@Test
	public void removeOrderByIdShouldRemoveAnOrderByItsIdAndReturnTheRemovedOrder() {
		Order expectedOrder = getOrdersForTest().get(0);
		
		when(orderServiceMock.remove(anyLong())).thenReturn(expectedOrder);
		Order actualOrder = orderController.removeOrderById(1);
		verify(orderServiceMock).remove(anyLong());
		assertThat(actualOrder, is(expectedOrder));
	}
}
