package com.is.buyabike.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

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
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> root = cq.from(Order.class);
		
		root.fetch("items");
		cq.select(root).distinct(true);
		
		Query q = em.createQuery(cq);
		return q.getResultList();
	}

	@Transactional
	public Order persist(Order order) {
		em.persist(order);
		
		return findOrderById(order.getId());
	}

	@Transactional
	public Order findOrderById(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> root = cq.from(Order.class);
		
		root.fetch("items");
		cq.select(root).where(cb.equal(root.get("id"), id));
		
		Query q = em.createQuery(cq);
		return (Order) q.getResultList().get(0);
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
