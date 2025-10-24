package com.TechGhuru.SivajiAndSons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TechGhuru.SivajiAndSons.model.Resourcelink;

@Repository
public interface ResourceRepo extends JpaRepository<Resourcelink, Integer> {

}
