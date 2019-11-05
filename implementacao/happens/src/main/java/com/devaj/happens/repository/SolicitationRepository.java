package com.devaj.happens.repository;

import com.devaj.happens.model.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationRepository extends JpaRepository<Solicitation, Long> {

}
