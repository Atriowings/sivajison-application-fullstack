package com.TechGhuru.SivajiAndSons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TechGhuru.SivajiAndSons.model.CustomerFlightDetails;

public interface CustomerFlightRepo extends JpaRepository<CustomerFlightDetails, Integer>{

	@Query(value = "SELECT * FROM customer_flight_details  WHERE traveldate = CURDATE()+ INTERVAL 2 DAY ",nativeQuery = true)

	List<CustomerFlightDetails> getflightdetailsbeforeday();

	@Query(value = "SELECT * FROM customer_flight_details  WHERE customernumber = :number ",nativeQuery = true)
	List<CustomerFlightDetails> findbyCustomernumber(String number); 
	// new line added
    List<CustomerFlightDetails>findAllByOrderByIdDesc();
}
