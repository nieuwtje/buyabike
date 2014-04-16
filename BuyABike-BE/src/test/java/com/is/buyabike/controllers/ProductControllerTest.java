package com.is.buyabike.controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.core.Is.*;

import org.junit.Before;
import org.junit.Test;

import junitx.util.PrivateAccessor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;

import com.is.buyabike.domain.Address;
import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.Supplier;
import com.is.buyabike.services.ProductService;

public class ProductControllerTest {

	private ProductController productController;
	private ProductService productServiceMock;

	@Before
	public void setUp() throws Exception {
		productController = new ProductController();
		productServiceMock = mock(ProductService.class);
		PrivateAccessor.setField(productController, "productService",
				productServiceMock);

	}

	private List<Product> getProductsForTest() {
		List<Product> products = new ArrayList<Product>();
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

		product1.addCategory(category1);
		product2.addCategory(category1);
		product3.addCategory(category1);

		product2.addCategory(category2);
		product3.addCategory(category2);

		products.add(product1);
		products.add(product2);
		products.add(product3);
		return products;
	}

	@Test
	public void getAllProductsShouldReturnAllProductsWithCategories() {
		List<Product> expectedProducts = getProductsForTest();
 		when(productServiceMock.getProducts()).thenReturn(expectedProducts);
 		
 		List<Product> actualProducts = productController.getAllProducts();
 		verify(productServiceMock).getProducts();
		assertThat(actualProducts, is(expectedProducts));
	}

	@Test
	public void getProductShouldReturnProduct() {
		Product expectedProduct = new Product("MTB 1", "MTB 1", "test", 100.00,
				200.00, 20);
		when(productServiceMock.getProductById(anyLong())).thenReturn(
				expectedProduct);
		Product actualProduct = productController.getProduct(1);
		verify(productServiceMock).getProductById(anyLong());
		assertThat(actualProduct, is(expectedProduct));
	}

	@Test
	public void updateProductStockShouldIncrementProductStockWithGivenAmount() {
		int stock = 20;
		int amountToAdd = 10;
		int result = stock + amountToAdd;
		Product product = new Product("MTB 1", "MTB 1", "test", 100.00, 200.00,
				stock);
		when(productServiceMock.getProductById(anyLong())).thenReturn(product);
		productController.updateProductStock(1, amountToAdd);
		verify(productServiceMock).getProductById(anyLong());
		verify(productServiceMock).updateProduct(product);
		assertThat(product.getStock(), is(result));
	}

}
