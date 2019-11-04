package com.devaj.happens.controller;

import com.devaj.happens.model.Product;
import com.devaj.happens.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid Product product){

        Product createdProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAll(){
        return ResponseEntity.ok(productService.listAll());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Product> getById(@PathVariable long id){
        Product product = productService.getById(id);
        return ResponseEntity.ok(product);
    }
}
