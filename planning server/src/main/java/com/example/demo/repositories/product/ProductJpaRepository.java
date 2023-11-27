package com.example.demo.repositories.product;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    Product getByProductId(Long productId);
}