package nl.remyh.buyabike.service.impl;

import java.util.ArrayList;
import java.util.List;

import nl.remyh.buyabike.dto.ProductDto;
import nl.remyh.buyabike.model.Product;
import nl.remyh.buyabike.repository.ProductRepository;
import nl.remyh.buyabike.service.ProductService;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private Mapper mapper;

    public List<Product> getProducts() {
        List<ProductDto> list = repo.findAll();
        List<Product> out = new ArrayList<Product>();
        for (ProductDto dto : list) {
            out.add(mapper.map(dto, Product.class));
        }
        return out;
    }

    @Transactional
    @Override
    public Product addProduct(Product product) {
        ProductDto dto = mapper.map(product, ProductDto.class);
        return mapper.map(repo.saveAndFlush(dto), Product.class);
    }

    @Transactional
    @Override
    public Product updateProduct(Product product) {
    	ProductDto dto = repo.findOne(product.getId());
        dto.setOmschrijving(product.getOmschrijving());
        dto.setNaam(product.getNaam());
        return mapper.map(repo.saveAndFlush(dto), Product.class);
    }

    @Transactional
    @Override
    public void deleteProduct(Product product) {
        repo.delete(product.getId());
    }

	
}
