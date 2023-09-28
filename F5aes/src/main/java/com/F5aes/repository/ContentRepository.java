package com.F5aes.repository;


import com.F5aes.model.ContentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<ContentModel, Long> {
}
