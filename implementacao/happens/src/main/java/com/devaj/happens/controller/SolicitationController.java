package com.devaj.happens.controller;

import com.devaj.happens.model.Product;
import com.devaj.happens.model.Solicitation;
import com.devaj.happens.service.ProductService;
import com.devaj.happens.service.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("solicitation")
public class SolicitationController {

    @Autowired
    private SolicitationService solicitationService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Solicitation> create(@RequestBody @Valid Solicitation solicitation){

        Solicitation createdSolicitation = solicitationService.save(solicitation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSolicitation);
    }

    @GetMapping
    public ResponseEntity<List<Solicitation>> listAll(){
        return ResponseEntity.ok(solicitationService.listAll());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Solicitation> getById(@PathVariable long id){
        Solicitation solicitation = solicitationService.getById(id);
        return ResponseEntity.ok(solicitation);
    }

    @PostMapping("/{id}/product")
    public ResponseEntity<Product> createProduct(@PathVariable (value = "id") Long id,
                                 @Valid @RequestBody Product productRequest) {

        Solicitation solicitation = solicitationService.getById(id);
        productRequest.setSolicitation(solicitation);

        Product createdProduct = productService.save(productRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);

    }
}
