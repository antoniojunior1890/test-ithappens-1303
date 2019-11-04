package com.devaj.happens.config;

import com.devaj.happens.model.Branch;
import com.devaj.happens.model.Product;
import com.devaj.happens.model.Stock;
import com.devaj.happens.repository.BranchRepository;
import com.devaj.happens.service.ProductService;
import com.devaj.happens.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Product> products = productService.listAll();

        if(products.isEmpty()){
            this.createProduct("Produto1", "Descrição", "1234", 100.0, 50);
            this.createProduct("Produto2", "Descrição", "5678", 200.0, 120);
        }

        List<Branch> branches = branchRepository.findAll();

        if(branches.isEmpty()){
            this.createBranche("Filial1");
            this.createBranche("Filial2");
        }
    }

    private void createBranche(String name) {
        Branch newBranch = new Branch(name);

        branchRepository.save(newBranch);
    }

    public void createProduct(String name, String description, String barCode, Double price, Integer amount){

        Product newProduct = new Product(name, description, barCode, price);

        productService.save(newProduct);
        this.createStock(newProduct, amount);
    }

    public void createStock(Product product, Integer amount){

        Stock newStock = new Stock(product, amount);

        stockService.save(newStock);
    }

}
