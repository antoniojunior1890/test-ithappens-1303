package com.devaj.happens.controller;

import com.devaj.happens.model.Product;
import com.devaj.happens.model.Stock;
import com.devaj.happens.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> listAll(){
        return ResponseEntity.ok(stockService.listAll());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<List<Stock>> listByBranch(@PathVariable long id){
//        Stock stock = stockService.getByIdBranch(id);
        return ResponseEntity.ok(stockService.getByIdBranch(id));
    }
}
