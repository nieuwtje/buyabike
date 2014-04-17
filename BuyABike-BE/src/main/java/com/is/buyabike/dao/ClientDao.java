package com.is.buyabike.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is.buyabike.domain.client.Client;
import com.is.buyabike.domain.order.Order;

@Transactional
@Repository
public class ClientDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public Client findWithId(long id) {
		return entityManager.find(Client.class, id);
	}

	@Transactional
	public void persist(Client client) {
		entityManager.persist(client);
	}

	@Transactional(readOnly = true)
	public List<Client> findAll() {
		TypedQuery<Client> q = entityManager.createQuery("SELECT p FROM Client p", Client.class);
		return q.getResultList();
	}

	@Transactional
	public void delete(Client client) {
		Client clientToRemove = this.findWithId(client.getId());
		entityManager.remove(clientToRemove);
	}

	@Transactional
	public Client update(Client client) {
		return entityManager.merge(client);
	}

	public Client login(String email, String password) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		Root<Client> root = cq.from(Client.class);
		
		cq.select(root).where(cb.and(cb.equal(root.get("email"), email), cb.equal(root.get("password"), password)));
		
		TypedQuery<Client> q = entityManager.createQuery(cq);
		try { 
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

}
