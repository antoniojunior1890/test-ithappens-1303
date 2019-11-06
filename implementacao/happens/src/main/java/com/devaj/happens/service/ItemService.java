package com.devaj.happens.service;

import com.devaj.happens.exception.NotFoundException;
import com.devaj.happens.model.Item;
import com.devaj.happens.model.Solicitation;
import com.devaj.happens.model.Stock;
import com.devaj.happens.model.enums.Status;
import com.devaj.happens.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SolicitationService solicitationService;

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    public Item save(Item item, Long id ) {

        Solicitation solicitation = solicitationService.getById(id);

        if(!solicitationService.existsById(id)){
            throw new NotFoundException("Não encontrado Solicitação para id "+ id);
        }

        Stock stock = stockService.getById(item.getStock().getId());

        if(!solicitation.getBranch().getId().equals(stock.getBranch().getId())){
            throw new NotFoundException("Este produto não pertence a esta filial");
        }

        if(stock.getAmount() < item.getAmount()){
            throw new NotFoundException("Estoque insuficiente");
        }

        List<Item> itemsSolicitation = itemService.listAllByIdSolicitation(solicitation.getId());
        Item itemExist = itemsSolicitation.stream()
                .filter(i->  i.getStock().getId().equals(item.getStock().getId()))
                .findAny()
                .orElse(null);

        if(itemExist != null){
            return itemRepository.findById(itemExist.getId()).map(i -> {
                i.setAmount( i.getAmount() + item.getAmount());
                return itemRepository.save(i);
            }).orElseThrow(() -> new NotFoundException("Item não encontrado"));
        }else {

            item.setSolicitation(solicitation);
            item.setStatus(Status.ATIVO);

        }
        return itemRepository.save(item);
    }

    public List<Item> listAll() {
        return itemRepository.findAll();
    }

    public List<Item> listAllByIdSolicitation(long id) {
        return itemRepository.findAllByIdSolicitation(id);
    }

}
