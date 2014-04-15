package com.is.buyabike.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is.buyabike.domain.order.Order;
import com.is.buyabike.domain.order.OrderItem;

@Repository
@Transactional
public class OrderDao {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Order> listOrders() {
		Query q = em.createQuery("FROM Order");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Order> listOrdersEager() {
		Query q = em.createQuery("SELECT DISTINCT o FROM Order o JOIN FETCH o.items");
		return q.getResultList();
	}

	@Transactional
	public Order persist(Order order) {
		em.persist(order);
		
		return findOrderById(order.getId());
	}

	@Transactional
	public Order findOrderById(long id) {
		return em.find(Order.class, id);
	}

	@Transactional
	public Order removeOrder(Order order)  {
		Order deleted = em.find(Order.class, order.getId());
		em.remove(order);
		
		return deleted;
	}

	@Transactional
	public Order removeOrder(long id) {
		return removeOrder(em.find(Order.class, id));
	}
}
