package com.TechGhuru.SivajiAndSons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TechGhuru.SivajiAndSons.model.CustomerFlightDetails;
import com.TechGhuru.SivajiAndSons.model.CustomersHotelDetails;
import com.TechGhuru.SivajiAndSons.model.customerbook;

public interface customerbookrepo extends JpaRepository<customerbook, Integer>{
	
 	@Query(value = "select a from CustomersHotelDetails a where a.customernumber = :number ORDER BY a.id DESC")
	List<CustomersHotelDetails> findByCustomernumberOrderByIdDesc(String number);
 	
 	@Query(value = "select a from CustomerFlightDetails a where a.customernumber = :number ORDER BY a.id DESC")
	List<CustomerFlightDetails> findByCustomernumberFlightOrderByIdDesc(String number);
	// newly added
	@Query(value = "select a from CustomerFlightDetails a where a.pnr = :pnr ORDER BY a.id DESC")
	List<CustomerFlightDetails> findBypnrFlightOrderByIdDesc(String pnr);

}
