package com.F5aes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.F5aes.model.Bootcamp;

public interface BootcampRepository extends JpaRepository<Bootcamp, Long> {

    Optional<Bootcamp> findByName(String String);

}
