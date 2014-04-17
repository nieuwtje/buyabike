package com.is.buyabike.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is.buyabike.domain.Supplier;

@Transactional
@Repository
public class SupplierDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public Supplier findWithId(long id) {
		return entityManager.find(Supplier.class, id);
	}

	@Transactional
	public void persist(Supplier supplier) {
		entityManager.persist(supplier);
	}

	@Transactional(readOnly = true)
	public List<Supplier> findAll() {
		TypedQuery<Supplier> q = entityManager.createQuery(
				"SELECT p FROM Supplier p", Supplier.class);
		return q.getResultList();
	}

	@Transactional
	public void delete(Supplier supplier) {
		Supplier supplierToRemove = this.findWithId(supplier.getId());
		entityManager.remove(supplierToRemove);
	}

	@Transactional
	public Supplier update(Supplier supplier) {
		return entityManager.merge(supplier);
	}

}
