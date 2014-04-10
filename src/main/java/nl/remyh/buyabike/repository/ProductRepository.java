package nl.remyh.buyabike.repository;

import nl.remyh.buyabike.dto.ProductDto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDto, Integer> {

}