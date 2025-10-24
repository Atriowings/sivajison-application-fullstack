package com.TechGhuru.SivajiAndSons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TechGhuru.SivajiAndSons.model.airlines;

@Repository
public interface AirlinesRepo extends JpaRepository<airlines, Integer> {
    
}
