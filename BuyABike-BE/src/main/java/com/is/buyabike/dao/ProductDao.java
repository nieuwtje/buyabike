package com.is.buyabike.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;

@Transactional
@Repository
public class ProductDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Product findWithId(long id) {
		TypedQuery<Product> q = entityManager
				.createQuery(
						"SELECT p FROM Product p JOIN FETCH p.categories WHERE p.id = :id",
						Product.class);
		q.setParameter("id", id);
		try{

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	@Transactional
	public void persist(Product product) {
		entityManager.persist(product);
	}

	@Transactional
	public List<Product> findAll() {
		TypedQuery<Product> q = entityManager.createQuery(
				"SELECT DISTINCT p FROM Product p JOIN FETCH p.categories",
				Product.class);
		List<Product> products = q.getResultList();
		return products;
	}

	@Transactional
	public void delete(Product product) {
		

		
//		
		Query q = entityManager.createQuery("delete from Product p WHERE p.id = :id");
		q.setParameter("id", product.getId());
		System.out.println(q.executeUpdate() + " rows deleted");
		//entityManager.remove(product);
	}

	@Transactional
	public Product update(Product product) {
		return entityManager.merge(product);
	}

}
