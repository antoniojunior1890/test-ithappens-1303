package com.devaj.happens.controller;

import com.devaj.happens.dto.ItemSavedto;
import com.devaj.happens.exception.NotFoundException;
import com.devaj.happens.model.Item;
import com.devaj.happens.model.Product;
import com.devaj.happens.model.Solicitation;
import com.devaj.happens.model.Stock;
import com.devaj.happens.service.ItemService;
import com.devaj.happens.service.ProductService;
import com.devaj.happens.service.SolicitationService;
import com.devaj.happens.service.StockService;
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
    private ItemService itemService;

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

    @PostMapping("/{id}/item")
    public ResponseEntity<Item> createProduct(@PathVariable (value = "id") Long id,
                                 @Valid @RequestBody ItemSavedto itemSavedto) {

        Item itemRequest = itemSavedto.transformToItem();

        Item createdItem = itemService.save(itemRequest, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);

    }

    @GetMapping(path = {"/{id}/item"})
    public ResponseEntity<List<Item>> getItemById(@PathVariable long id){

        if(!solicitationService.existsById(id)) {
            throw new NotFoundException("Não encontrado Solicitação para id "+ id);
        }

        List<Item> items = itemService.listAllByIdSolicitation(id);

        return ResponseEntity.ok(items);
    }
}
