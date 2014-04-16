package com.is.buyabike.controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junitx.util.PrivateAccessor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;
import static org.hamcrest.core.Is.*;

import org.junit.Before;
import org.junit.Test;

import com.is.buyabike.domain.Address;
import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.Supplier;
import com.is.buyabike.services.CategoryService;

public class CategoryControllerTest {

	private CategoryController categoryController;
	private CategoryService categoryServiceMock;
	@Before
	public void setUp() throws Exception {
		categoryController = new CategoryController();
		categoryServiceMock = mock(CategoryService.class);
		PrivateAccessor.setField(categoryController, "categoryService", categoryServiceMock);
	}
	
	private List<Category> categoriesWithoutProducts(){
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category("MTB", "Mountain bikes"));
		categories.add(new Category("ATB", "All Terrain bikes"));
		return categories;
	}
	
	private List<Category> categoriesWithProducts(){
		List<Category> categories = new ArrayList<Category>();
		Category category1 = new Category("Mountainbikes",
				"Categorie mountainbikes");
		Category category2 = new Category("Racebikes", "Categorie racingbikes");

		Product product1 = new Product("MTB 1", "MTB 1", "test", 100.00,
				200.00, 20);
		Product product2 = new Product("MTB 2", "MTB 2", "test", 100.00,
				200.00, 20);
		Product product3 = new Product("MTB 3", "MTB 3", "test", 100.00,
				200.00, 20);

		Supplier supplier = new Supplier("Fietsfabrikant", new Address(
				"Street", "2", "City", "State", "Country"), "website", "phone");

		product1.setSupplier(supplier);
		product2.setSupplier(supplier);
		product3.setSupplier(supplier);
		
		category1.addProduct(product1);
		category1.addProduct(product2);
		category1.addProduct(product3);
		
		category2.addProduct(product2);
		category2.addProduct(product3);
		categories.add(category1);
		categories.add(category2);
		return categories;
	}

	@Test
	public void getAllCategoriesShouldReturnAllCategoriesFromCategoryService() {
		List<Category> expectedCategories = categoriesWithoutProducts();
		when(categoryServiceMock.getCategories()).thenReturn(expectedCategories);
		List<Category> actualCategories = categoryController.getAllCategories();
		verify(categoryServiceMock).getCategories();
		assertThat(actualCategories,is(expectedCategories));
	}

	@Test
	public void getAllCategoriesWithProductsShouldReturnAllCategoriesWithProductsFromCategoryService() {
		List<Category> expectedCategories = categoriesWithProducts();
		when(categoryServiceMock.getCategoriesWithProducts()).thenReturn(expectedCategories);

		List<Category> actualCategories = categoryController.getAllCategoriesWithProducts();
		verify(categoryServiceMock).getCategoriesWithProducts();
		assertThat(actualCategories,is(expectedCategories));
	}

	@Test
	public void getCategoryShouldReturnCategoryWithProductsFromCategoryService() {
		Category category = new Category("Mountainbikes", "Categorie mountainbikes");
		category.addProduct(new Product("MTB 1", "MTB 1", "test", 100.00, 200.00, 20));
		when(categoryServiceMock.getCategory(anyLong())).thenReturn(category);
		Category actualCategory  = categoryController.getCategory(1);
		verify(categoryServiceMock).getCategory(anyLong());
		assertThat(actualCategory,is(category));
	}

}
