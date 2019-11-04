package com.devaj.happens.repository;

import com.devaj.happens.model.Branch;
import com.devaj.happens.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}
