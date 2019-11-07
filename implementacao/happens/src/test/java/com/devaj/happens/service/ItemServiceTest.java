package com.devaj.happens.service;

import com.devaj.happens.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

//    @Test
    public void saveTest(){
        Stock stock = new Stock();
        stock.setId(3L);

        Solicitation solicitation = new Solicitation();
        solicitation.setId(1L);

        Item newItem = new Item();
        newItem.setSolicitation(solicitation);
        newItem.setStock(stock);
        newItem.setAmount(2);
        newItem.setPrice(65.9);

        Item createdItem = itemService.save(newItem, 1L);

        assertThat(createdItem.getId()).isEqualTo(2L);

    }

//    @Test
    void listAllBySolicitationTest() {
        List<Item> items = itemService.listAllByIdSolicitation(1L);
        assertThat(items.size()).isEqualTo(2);
    }
}