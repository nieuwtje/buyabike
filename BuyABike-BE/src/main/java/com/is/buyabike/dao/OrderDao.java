package com.is.buyabike.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is.buyabike.domain.order.Order;

@Repository
@Transactional
public class OrderDao {
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Order> listOrders() {
		Query q = em.createQuery("FROM Order");
		return q.getResultList();
	}

	@Transactional
	public Order saveOrder(Order order) {
		if (findOrderById(order.getId()) != null) {
			em.merge(order);
		}
		else {
			em.persist(order);
		}
		
		return findOrderById(order.getId());
	}

	@Transactional
	public Order findOrderById(long id) {
		return em.find(Order.class, id);
	}

	@Transactional
	public void removeOrder(Order order) {
		em.remove(order);
	}

	@Transactional
	public void removeOrder(long id) {
		em.remove(em.find(Order.class, id));
	}
}
