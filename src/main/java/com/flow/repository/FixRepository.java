package com.flow.repository;

import com.flow.entity.Fix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FixRepository extends JpaRepository<Fix, Long> {

    Optional<Fix> findByFixName(String fixName);
    Optional<Fix> findByFixStatus(boolean fixStatus);
}
