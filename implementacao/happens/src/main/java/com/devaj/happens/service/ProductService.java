package com.devaj.happens.service;

import com.devaj.happens.model.Product;
import com.devaj.happens.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }


}
