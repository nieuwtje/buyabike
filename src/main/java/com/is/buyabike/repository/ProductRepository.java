package com.is.buyabike.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.is.buyabike.model.Product;

public interface ProductRepository  extends JpaRepository<Product, Integer> {

}
