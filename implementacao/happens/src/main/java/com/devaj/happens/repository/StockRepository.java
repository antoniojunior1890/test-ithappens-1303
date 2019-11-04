package com.devaj.happens.repository;

import com.devaj.happens.model.Product;
import com.devaj.happens.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
