package com.is.buyabike.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is.buyabike.domain.Category;

@Transactional
@Repository
public class CategoryDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly=true)
	public Category findWithId(long id) {
		return entityManager.find(Category.class, id);
	}

	@Transactional
	public void persist(Category category){
		entityManager.persist(category);
	}

	@Transactional(readOnly=true)
	public List<Category> findAll(){
		TypedQuery<Category> q = entityManager.createQuery("SELECT c FROM Category c", Category.class);
		return q.getResultList();
	}

	@Transactional
	public void delete(Category category){
		Category categoryToRemove = this.findWithId(category.getId());
		entityManager.remove(categoryToRemove);
	}

	@Transactional
	public Category update(Category category){
		return entityManager.merge(category);
	}

	public List<Category> findAllWithProducts() {
		TypedQuery<Category> q = entityManager.createQuery("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.products", Category.class);
		return q.getResultList();
	}

}
