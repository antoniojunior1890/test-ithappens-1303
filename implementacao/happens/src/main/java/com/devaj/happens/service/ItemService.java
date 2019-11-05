package com.devaj.happens.service;

import com.devaj.happens.model.Item;
import com.devaj.happens.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> listAll() {
        return itemRepository.findAll();
    }

    public List<Item> listAllByIdSolicitation(long id) {
        return itemRepository.findAllByIdSolicitation(id);
    }

}
