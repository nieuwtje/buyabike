package com.is.buyabike.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junitx.util.PrivateAccessor;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import static org.hamcrest.core.Is.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.is.buyabike.dao.ProductDao;
import com.is.buyabike.domain.Address;
import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.Supplier;

public class ProductServiceTest {
	
	private ProductService productService;
	private ProductDao productDaoMock;

	@Before
	public void setUp() throws Exception {
		productService = new ProductService();
		productDaoMock = mock(ProductDao.class);
		PrivateAccessor.setField(productService, "productDao", productDaoMock);
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
	public void getProductShouldReturnAllProductsFromProductDao() {
		List<Product> expectedProducts = getProductsForTest();
		when(productDaoMock.findAll()).thenReturn(expectedProducts);
		
		List<Product> actualProducts = productService.getProducts();
		verify(productDaoMock).findAll();
		assertThat(actualProducts, is(expectedProducts));
	}

	@Test
	public void getProductByIdShouldReturnProductFromProductDao() {
		Product expectedProduct = new Product("MTB 3", "MTB 3", "test", 100.00, 200.00, 20);
		when(productDaoMock.findWithId(anyLong())).thenReturn(expectedProduct);
		
		Product actualProduct = productService.getProductById(1);
		verify(productDaoMock).findWithId(anyLong());
		assertThat(actualProduct, is(expectedProduct));
	}

	@Test
	public void updateProduct() {
		Product product = new Product("MTB 3", "MTB 3", "test", 100.00, 200.00, 20);
		when(productDaoMock.update(product)).thenReturn(product);
		product.setDescription("new Description");
		
		Product updatedProduct = productService.updateProduct(product);
		verify(productDaoMock).update(any(Product.class));
		assertThat(updatedProduct, is(product));
	}

	@Test
	public void testRemoveProduct() {
		Product product = new Product("MTB 3", "MTB 3", "test", 100.00, 200.00, 20);
		productService.removeProduct(product);
		verify(productDaoMock).delete(any(Product.class));
	}

	@Test
	public void testSaveProduct() {
		Product product = new Product("MTB 3", "MTB 3", "test", 100.00, 200.00, 20);
		productService.saveProduct(product);
		verify(productDaoMock).persist(any(Product.class));
	}

}
