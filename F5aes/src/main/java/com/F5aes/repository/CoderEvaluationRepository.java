package com.F5aes.repository;

import com.F5aes.model.CoderEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoderEvaluationRepository extends JpaRepository<CoderEvaluation,Long> {
}
