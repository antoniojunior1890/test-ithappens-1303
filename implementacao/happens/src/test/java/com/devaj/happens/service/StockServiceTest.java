package com.devaj.happens.service;

import com.devaj.happens.model.Branch;
import com.devaj.happens.model.Product;
import com.devaj.happens.model.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @Test
    public void saveTest(){
        Product product = new Product();
        product.setId(1L);

        Branch branch = new Branch();
        branch.setId(1L);

        Stock newStock = new Stock();
        newStock.setAmount(40);
        newStock.setBranch(branch);
        newStock.setProduct(product);

        Stock createdStock= stockService.save(newStock);

        assertThat(createdStock.getId()).isEqualTo(5L);
    }

    @Test
    void listAllTest() {
        List<Stock> stocks = stockService.listAll();

        assertThat(stocks.size()).isEqualTo(5);
    }
}