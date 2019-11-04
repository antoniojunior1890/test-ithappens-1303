package com.devaj.happens.service;

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
        Product newProduct = new Product(null, "ProdutoTeste", "Descrição", "0001", 100.0);
        Product createdProduct= productService.save(newProduct);

        Stock newStock = new Stock( null, newProduct, 10);
        Stock createdStock = stockService.save(newStock);

        assertThat(createdStock.getId()).isEqualTo(3L);
    }

    @Test
    void listAllTest() {
        List<Stock> stocks = stockService.listAll();

        assertThat(stocks.size()).isEqualTo(3);
    }
}