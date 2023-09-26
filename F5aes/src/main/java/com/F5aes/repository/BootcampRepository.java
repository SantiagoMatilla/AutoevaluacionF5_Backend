package com.F5aes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.F5aes.model.BootcampModel;

public interface BootcampRepository extends JpaRepository<BootcampModel, Long> {

    Optional<BootcampModel> findByName(String String);

}
