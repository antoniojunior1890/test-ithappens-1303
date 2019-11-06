package com.devaj.happens.service;

import com.devaj.happens.exception.NotFoundException;
import com.devaj.happens.model.*;
import com.devaj.happens.model.enums.PaymentMethod;
import com.devaj.happens.model.enums.Status;
import com.devaj.happens.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private SolicitationService solicitationService;

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment save(Long solicitationId, PaymentMethod paymentMethod) {
        Solicitation solicitation = solicitationService.getById(solicitationId);

        List<Item> items = itemService.listAllByIdSolicitation(solicitationId);

        List<Stock> stocks = getStockByBranchId(solicitation.getBranch());

        try {
            items.forEach( item -> {
                if(degradeStock(item, stocks)){
                    updateStatusItem(item, Status.PROCESSADO);
                }
            });

            Payment payment = new Payment();
            payment.setCreationDate(new Date());
            payment.setSolicitation(solicitation);
            payment.setPaymentMethod(paymentMethod);

            paymentRepository.save(payment);
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }

        return null;
    }



    private List<Stock> getStockByBranchId(Branch branch){
        return stockService.getByIdBranch(branch.getId());
    }

    private Boolean degradeStock(Item item, List<Stock> stocks){
        Stock stockExist = stocks.stream()
                .filter(s->  s.getId().equals(item.getStock().getId()))
                .findAny()
                .orElse(null);

        if(stockExist != null && item.getStatus().equals(Status.ATIVO)) {
            return stockService.findById(stockExist.getId()).map(s -> {
                s.setAmount(s.getAmount() -  item.getAmount());
                if(s.getAmount() < 0){
                    throw  new IllegalArgumentException("Saldo insuficiente");
                }
                stockService.save(s);
                return true;
            }).orElseThrow(() -> new NotFoundException("Item não encontrado"));

        }else {
            if(!item.getStatus().equals(Status.ATIVO)){
                throw  new IllegalArgumentException("Item não esta mais ativo");
            }
        }
        return false;
    }

    public void updateStatusItem(Item item, Status status){
        itemService.update(item, status);
    }
}
