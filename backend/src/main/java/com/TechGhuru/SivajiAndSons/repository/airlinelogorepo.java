package com.TechGhuru.SivajiAndSons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TechGhuru.SivajiAndSons.model.airlinesWithLogo;

@Repository
public interface airlinelogorepo extends JpaRepository<airlinesWithLogo, Integer> {

	@Query(value = "select a from airlinesWithLogo a where a.airline = :name ")
	List<airlinesWithLogo> getlogo(String name);

}
