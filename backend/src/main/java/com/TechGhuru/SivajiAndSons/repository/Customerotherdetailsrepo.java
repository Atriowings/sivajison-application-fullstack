package com.TechGhuru.SivajiAndSons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TechGhuru.SivajiAndSons.model.Customerotherdetails;
import com.TechGhuru.SivajiAndSons.model.CustomersHotelDetails;

public interface Customerotherdetailsrepo extends JpaRepository<Customerotherdetails, Integer> {
   List<Customerotherdetails> findAllByOrderByIdDesc();
}
