package com.is.buyabike.controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import junitx.util.PrivateAccessor;

import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
		PrivateAccessor.setField(productController, "productService", productServiceMock);
		
	}
	
	private List<Product> getProductsForTest(){
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 15; i++) {
			int image = 500 + i;
			int stock = i;
			Product product = new Product("Fiets " + i,"Beschrijving " + i,"bike"+image,200.00,300.00,stock) ;
			products.add(product);
		}
		return products;
	}

	@Test
	public void getProductsShouldReturnListOfProductsFromProductService() {
		List<Product> expectedProducts = getProductsForTest();
		when(productServiceMock.getProducts()).thenReturn(expectedProducts);
		
		List<Product> actualProducts = productController.getProducts();

        verify(productServiceMock).getProducts();

        assertArrayEquals(expectedProducts.toArray(), actualProducts.toArray());
	}

}
