package com.devaj.happens.service;

import com.devaj.happens.exception.NotFoundException;
import com.devaj.happens.model.Product;
import com.devaj.happens.model.Stock;
import com.devaj.happens.repository.ProductRepository;
import com.devaj.happens.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public List<Stock> listAll() {
        return stockRepository.findAll();
    }

    public List<Stock> getByIdBranch(long id) {
        return stockRepository.getByIdBranch(id).orElseThrow(()-> new NotFoundException("Não encontrado estoque para id "+id));
    }


}
