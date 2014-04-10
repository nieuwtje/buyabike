package nl.remyh.buyabike.service;

import java.util.List;

import nl.remyh.buyabike.model.Product;

import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

	List<Product> getProducts();

	@Transactional
	Product addProduct(Product product);

	@Transactional
	Product updateProduct(Product product);

	@Transactional
	void deleteProduct(Product product);
}
