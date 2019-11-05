package com.devaj.happens.repository;

import com.devaj.happens.model.Stock;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE s.branch.id = :id")
    @EntityGraph(attributePaths = {"product", "branch"})
    Optional<List<Stock>> getByIdBranch(long id);
}
