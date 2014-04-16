package com.is.buyabike.dao;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
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

import com.is.buyabike.domain.Supplier;

@RunWith(MockitoJUnitRunner.class)
public class SupplierDaoTest {

	private SupplierDao supplierDao;
	@Mock
	private EntityManager entityManagerMock;
	@Mock
	TypedQuery<Supplier> query;

	@Before
	public void setUp() throws Exception {
		supplierDao = new SupplierDao();
		PrivateAccessor.setField(supplierDao, "entityManager", entityManagerMock);
	}

	@Test
	public void testFindWithId() {
		Supplier expectedSupplier = new Supplier();
		when(entityManagerMock.find(eq(Supplier.class), anyLong())).thenReturn(expectedSupplier);
		Supplier actualSupplier = supplierDao.findWithId(1);
		verify(entityManagerMock).find(eq(Supplier.class), anyLong());
		assertThat(actualSupplier,is(expectedSupplier));
	}

	@Test
	public void testPersist() {
		Supplier expectedSupplier = new Supplier();
		supplierDao.persist(expectedSupplier);
		verify(entityManagerMock).persist(any(Supplier.class));
	}

	@Test
	public void testFindAll() {
		List<Supplier> expectedSuppliers = new ArrayList<Supplier>();
		expectedSuppliers.add(new Supplier());
		expectedSuppliers.add(new Supplier());
		when(entityManagerMock.createQuery(anyString(), eq(Supplier.class))).thenReturn(query);
		
		
		when(query.getResultList()).thenReturn(expectedSuppliers);
		List<Supplier> actualSuppliers = supplierDao.findAll();
		verify(entityManagerMock).createQuery(anyString(), eq(Supplier.class));
		verify(query).getResultList();
		assertThat(actualSuppliers,is(expectedSuppliers));
	}

	@Test
	public void testDelete() {
		Supplier supplier = new Supplier();
		when(entityManagerMock.createQuery(anyString(), eq(Supplier.class))).thenReturn(query);
		when(query.getSingleResult()).thenReturn(supplier);
		supplierDao.delete(supplier);
		verify(entityManagerMock).remove(any(Supplier.class));
	}

	@Test
	public void testUpdate() {
		Supplier expectedSupplier =new Supplier();
		supplierDao.update(expectedSupplier);
		verify(entityManagerMock).merge(any(Supplier.class));
	}


}
