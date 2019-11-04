package com.devaj.happens.config;

import com.devaj.happens.model.Product;
import com.devaj.happens.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductService productService;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Product> products = productService.listAll();

        if(products.isEmpty()){
            this.createProduct("Produto1", "Descrição", "1234", 100.0);
            this.createProduct("Produto2", "Descrição", "5678", 200.0);
        }
    }
//
    public void createProduct(String name, String description, String barCode, Double price){

        Product newProduct = new Product(name, description, barCode, price);

        productService.save(newProduct);
    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
