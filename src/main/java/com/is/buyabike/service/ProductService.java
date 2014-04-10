package com.is.buyabike.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.is.buyabike.model.Product;

public interface ProductService {
    List<Product> getProducts();

    @Transactional
    Product addProduct(Product product);

    @Transactional
    Product updateProduct(Product product);

    @Transactional
    void deleteProduct(Product product);
}