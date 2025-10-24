package com.TechGhuru.SivajiAndSons.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TechGhuru.SivajiAndSons.model.Attendence;
import com.TechGhuru.SivajiAndSons.model.CustomersHotelDetails;

public interface CustomerHotelDetailsRepo extends JpaRepository<CustomersHotelDetails, Integer>{

	@Query(value = "SELECT * FROM customers_hotel_details  WHERE checkindate = CURDATE()+ INTERVAL 2 DAY ",nativeQuery = true)
	List<CustomersHotelDetails> gethoteldetailsbeforeday();
	// new line added
	List<CustomersHotelDetails> findAllByOrderByIdDesc();

}
