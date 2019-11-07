package com.devaj.happens.repository;

import com.devaj.happens.model.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSystemRepository extends JpaRepository<UserSystem, Long> {

}
