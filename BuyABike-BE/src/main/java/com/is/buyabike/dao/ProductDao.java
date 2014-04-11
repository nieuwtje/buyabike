package com.is.buyabike.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is.buyabike.domain.Product;

@Transactional
@Repository
public class ProductDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly=true)
	public Product findWithId(long id) {
		return entityManager.find(Product.class, id);
	}

	@Transactional
	public void persist(Product product){
		entityManager.persist(product);
	}

	@Transactional(readOnly=true)
	public List<Product> findAll(){
		TypedQuery<Product> q = entityManager.createQuery("SELECT p FROM Product p", Product.class);
		return q.getResultList();
	}

	@Transactional
	public void delete(Product product){
		Product productToRemove = this.findWithId(product.getId());
		entityManager.remove(productToRemove);
	}

	@Transactional
	public Product update(Product product){
		return entityManager.merge(product);
	}

}
