package com.example.demo.services.product;

import com.example.demo.models.Product;
import com.example.demo.repositories.product.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductJpaRepository productJpaRepository;

    @Override
    public Product create(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public List<Product> readAll() {
        return productJpaRepository.findAll();
    }


    @Override
    public boolean delete(Long id) {
        if (productJpaRepository.existsById(id)) {
            productJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Product product) {
        if (productJpaRepository.existsById(id)) {
            product.setProductId(id);
            productJpaRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public Product readById(Long productId) {
        return productJpaRepository.getByProductId(productId);
    }

}
