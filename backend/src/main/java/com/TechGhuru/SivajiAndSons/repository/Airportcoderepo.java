package com.TechGhuru.SivajiAndSons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TechGhuru.SivajiAndSons.model.Airportcodes;

@Repository
public interface Airportcoderepo extends JpaRepository<Airportcodes, Integer> {
	

}
