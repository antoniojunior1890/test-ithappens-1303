package com.devaj.happens.controller;

import com.devaj.happens.dto.ItemSavedto;
import com.devaj.happens.dto.PaymentMethodSavedto;
import com.devaj.happens.exception.NotFoundException;
import com.devaj.happens.model.*;
import com.devaj.happens.model.enums.PaymentMethod;
import com.devaj.happens.service.*;
import net.bytebuddy.asm.Advice;
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

    @Autowired
    private PaymentService paymentService;

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

    @DeleteMapping("/{solicitationId}/item/{itemId}")
    public ResponseEntity<?> cancelItemSolicitation(@PathVariable("solicitationId") Long solicitationId,
                                                    @PathVariable("itemId") Long itemId) {
        itemService.deleteItemBySolicitation(solicitationId, itemId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = {"/{id}/item"})
    public ResponseEntity<List<Item>> getItemById(@PathVariable long id){

        if(!solicitationService.existsById(id)) {
            throw new NotFoundException("Não encontrado Solicitação para id "+ id);
        }

        List<Item> items = itemService.listAllByIdSolicitation(id);

        return ResponseEntity.ok(items);
    }

    @PostMapping("/{solicitationId}/payment")
    public ResponseEntity<Payment> createPaymentSolicitation(@PathVariable (value = "solicitationId") Long solicitationId,
                                                             @RequestBody @Valid PaymentMethodSavedto paymentMethodSavedto){

        PaymentMethod paymentMethod = paymentMethodSavedto.transformToPaymentMethod();

        Payment createdPayment = paymentService.save(solicitationId, paymentMethod);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);

    }
}
