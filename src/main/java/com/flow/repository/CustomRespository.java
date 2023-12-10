package com.flow.repository;

import com.flow.entity.Custom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomRespository extends JpaRepository<Custom, Long> {

    Optional<Custom> findByCustomName(String customName);

}
