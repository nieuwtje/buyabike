package com.is.buyabike.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import junitx.util.PrivateAccessor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/dao-context.xml"})
public class ProductDaoTest extends AbstractTransactionalJUnit4SpringContextTests  {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test(){
		Product product = new Product("MTB 1", "MTB 1", "test", 100.00, 200.00, 20);
		Category category = new Category("mtbs","mtbs");
		category.addProduct(product);
		productDao.persist(product);
		categoryDao.persist(category);
		System.out.println("id " + product.getId());
		Product product2 = productDao.findWithId(product.getId());
		assertThat(product,is(product2));
	}
//	@Test
//	public void testFindWithId() {
//		Product expectedProduct = new Product("MTB 1", "MTB 1", "test", 100.00, 200.00, 20);
//		when(entityManagerMock.createQuery(anyString(), eq(Product.class))).thenReturn(query);
//		when(query.getSingleResult()).thenReturn(expectedProduct);
//		Product actualProduct = productDao.findWithId(1);
//		verify(entityManagerMock).createQuery(anyString(), eq(Product.class));
//		verify(query).getSingleResult();
//		assertThat(actualProduct,is(expectedProduct));
//	}
//
//	@Test
//	public void testPersist() {
//		Product expectedProduct = new Product("MTB 1", "MTB 1", "test", 100.00, 200.00, 20);
//		productDao.persist(expectedProduct);
//		verify(entityManagerMock).persist(any(Product.class));
//	}
//
//	@Test
//	public void testFindAll() {
//		List<Product> expectedProducts = new ArrayList<Product>();
//		expectedProducts.add(new Product("MTB 1", "MTB 1", "test", 100.00, 200.00, 20));
//		expectedProducts.add(new Product("MTB 1", "MTB 1", "test", 100.00, 200.00, 20));
//		when(entityManagerMock.createQuery(anyString(), eq(Product.class))).thenReturn(query);
//		
//		
//		when(query.getResultList()).thenReturn(expectedProducts);
//		List<Product> actualProducts = productDao.findAll();
//		verify(entityManagerMock).createQuery(anyString(), eq(Product.class));
//		verify(query).getResultList();
//		assertThat(actualProducts,is(expectedProducts));
//	}
//
//	@Test
//	public void testDelete() {
//		Product product = new Product("MTB 1", "MTB 1", "test", 100.00, 200.00, 20);
//		when(entityManagerMock.createQuery(anyString(), eq(Product.class))).thenReturn(query);
//		when(query.getSingleResult()).thenReturn(product);
//		productDao.delete(product);
//		verify(entityManagerMock).remove(any(Product.class));
//	}
//
//	@Test
//	public void testUpdate() {
//		Product expectedProduct = new Product("MTB 1", "MTB 1", "test", 100.00, 200.00, 20);
//		productDao.update(expectedProduct);
//		verify(entityManagerMock).merge(any(Product.class));
//	}

}
