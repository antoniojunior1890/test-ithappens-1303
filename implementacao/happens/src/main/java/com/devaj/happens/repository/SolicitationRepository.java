package com.devaj.happens.repository;

import com.devaj.happens.model.Solicitation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitationRepository extends JpaRepository<Solicitation, Long> {

    @Override
    @EntityGraph(attributePaths = {"branch", "user", "client"})
    List<Solicitation> findAll();

    @Override
    @EntityGraph(attributePaths = {"branch", "user", "client"})
    Optional<Solicitation> findById(Long aLong);
}
