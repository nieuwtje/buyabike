package com.is.buyabike.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import junitx.util.PrivateAccessor;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import static org.hamcrest.core.Is.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.is.buyabike.domain.Category;

@RunWith(MockitoJUnitRunner.class)
public class CategoryDaoTest {
	
	private CategoryDao categoryDao;
	@Mock
	private EntityManager entityManagerMock;
	@Mock
	TypedQuery<Category> query;

	@Before
	public void setUp() throws Exception {
		categoryDao = new CategoryDao();
		PrivateAccessor.setField(categoryDao, "entityManager", entityManagerMock);
		
	}

	@Test
	public void testFindWithId() {
		Category expectedCategory = new Category("Test Category", "Test Category");
		when(entityManagerMock.createQuery(anyString(), eq(Category.class))).thenReturn(query);
		when(query.getSingleResult()).thenReturn(expectedCategory);
		Category actualCategory = categoryDao.findWithId(1);
		verify(entityManagerMock).createQuery(anyString(), eq(Category.class));
		verify(query).getSingleResult();
		assertThat(actualCategory,is(expectedCategory));
	}

	@Test
	public void testPersist() {
		Category expectedCategory = new Category("Test Category", "Test Category");
		categoryDao.persist(expectedCategory);
		verify(entityManagerMock).persist(any(Category.class));
	}

	@Test
	public void testFindAll() {
		List<Category> expectedCategories = new ArrayList<Category>();
		expectedCategories.add(new Category("Test Category", "Test Category"));
		expectedCategories.add(new Category("Test Category1", "Test Category1"));
		when(entityManagerMock.createQuery(anyString(), eq(Category.class))).thenReturn(query);
		
		
		when(query.getResultList()).thenReturn(expectedCategories);
		List<Category> actualCategories = categoryDao.findAll();
		verify(entityManagerMock).createQuery(anyString(), eq(Category.class));
		verify(query).getResultList();
		assertThat(actualCategories,is(expectedCategories));
	}

	@Test
	public void testDelete() {
		Category category = new Category("Test Category", "Test Category");
		when(entityManagerMock.createQuery(anyString(), eq(Category.class))).thenReturn(query);
		when(query.getSingleResult()).thenReturn(category);
		categoryDao.delete(category);
		verify(entityManagerMock).remove(any(Category.class));
	}

	@Test
	public void testUpdate() {
		Category expectedCategory = new Category("Test Category", "Test Category");
		categoryDao.update(expectedCategory);
		verify(entityManagerMock).merge(any(Category.class));
	}

	@Test
	public void testFindAllWithProducts() {
		List<Category> expectedCategories = new ArrayList<Category>();
		expectedCategories.add(new Category("Test Category", "Test Category"));
		expectedCategories.add(new Category("Test Category1", "Test Category1"));
		when(entityManagerMock.createQuery(anyString(), eq(Category.class))).thenReturn(query);
		
		
		when(query.getResultList()).thenReturn(expectedCategories);
		List<Category> actualCategories = categoryDao.findAll();
		verify(entityManagerMock).createQuery(anyString(), eq(Category.class));
		verify(query).getResultList();
		assertThat(actualCategories,is(expectedCategories));
	}

}
