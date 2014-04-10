package com.is.buyabike.service.impl;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.is.buyabike.dto.IdeaDto;
import com.is.buyabike.model.Idea;
import com.is.buyabike.model.Product;
import com.is.buyabike.repository.ProductRepository;
import com.is.buyabike.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repo;
    
    @Autowired
    private Mapper mapper;

    public List<Product> getProducts() {
        return repo.findAll();
    }

    @Transactional
    @Override
    public Product addProduct(Product product) {
        Product p = mapper.map(product, Product.class);
        return mapper.map(repo.saveAndFlush(p), Product.class);
    }

    @Transactional
    @Override
    public Product updateProduct(Product product) {
        Product p = repo.findOne((int) product.getId());
        return mapper.map(repo.saveAndFlush(p), Product.class);
    }

    @Transactional
    @Override
    public void deleteProduct(Product product) {
        repo.delete((int) product.getId());
    }
}
