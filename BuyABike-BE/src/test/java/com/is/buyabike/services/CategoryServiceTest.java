package com.is.buyabike.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junitx.util.PrivateAccessor;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import static org.hamcrest.core.Is.*;

import com.is.buyabike.dao.CategoryDao;
import com.is.buyabike.domain.Category;

public class CategoryServiceTest {
	private CategoryService categoryService;
	private CategoryDao categoryDaoMock;

	@Before
	public void setUp() throws Exception {
		categoryService = new CategoryService();
		categoryDaoMock = mock(CategoryDao.class);
		PrivateAccessor.setField(categoryService, "categoryDao", categoryDaoMock);
	}

	@Test
	public void testGetCategoriesWithProducts() {
		List<Category> expectedCategories = new ArrayList<Category>();
		when(categoryDaoMock.findAllWithProducts()).thenReturn(expectedCategories);
		List<Category> actualCategories = categoryService.getCategoriesWithProducts();
		verify(categoryDaoMock).findAllWithProducts();
		assertThat(actualCategories,is(expectedCategories));
	}

	@Test
	public void testGetCategories() {
		List<Category> expectedCategories = new ArrayList<Category>();
		when(categoryDaoMock.findAll()).thenReturn(expectedCategories);
		List<Category> actualCategories = categoryService.getCategories();
		verify(categoryDaoMock).findAll();
		assertThat(actualCategories,is(expectedCategories));
	}

	@Test
	public void testGetCategory() {
		Category expectedCategory = new Category();
		when(categoryDaoMock.findWithId(anyLong())).thenReturn(expectedCategory);
		Category actualCategory = categoryService.getCategory(1);
		verify(categoryDaoMock).findWithId(anyLong());
		assertThat(actualCategory,is(expectedCategory));
	
	}

}
