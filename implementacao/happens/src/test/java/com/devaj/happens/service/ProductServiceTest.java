package com.devaj.happens.service;

import com.devaj.happens.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void saveTest(){

        Product newProduct = new Product(null, "ProdutoTeste", "Descrição", "0000", 100.0);
        Product createdProduct= productService.save(newProduct);

        assertThat(createdProduct.getId()).isEqualTo(3L);
    }

    @Test
    void listAllTest() {
        List<Product> products = productService.listAll();

        assertThat(products.size()).isEqualTo(3);
    }

    @Test
    public void getByIdTest(){
        Product product = productService.getById(1L);

        assertThat(product.getBarCode()).isEqualTo("1234");
    }
}
