package com.devaj.happens.repository;

import com.devaj.happens.model.Item;
import com.devaj.happens.model.Solicitation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByIdAndSolicitationId( Long itemId, Long solicitationId);

    List<Item> findAllBySolicitationId(Long id);
}
