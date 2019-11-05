package com.devaj.happens.controller;

import com.devaj.happens.model.Stock;
import com.devaj.happens.service.BranchService;
import com.devaj.happens.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private StockService stockService;


    @GetMapping(path = {"/{id}/stock"})
    public ResponseEntity<List<Stock>> listByBranch(@PathVariable long id){
        return ResponseEntity.ok(stockService.getByIdBranch(id));
    }

}
