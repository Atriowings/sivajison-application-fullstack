// import java.util.List;

package com.TechGhuru.SivajiAndSons.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TechGhuru.SivajiAndSons.model.Attendence;
import com.TechGhuru.SivajiAndSons.model.CustomersHotelDetails;
import com.TechGhuru.SivajiAndSons.model.Reminder;

@Repository
public interface ReminderRepo extends JpaRepository<Reminder, Integer>{
    // if you want use these method needed.  but i fetched directly in AdminService getreminded function
  // List<Reminder> findAllByOrderByIdDesc(); 
  //  List<Reminder> findAllByOrderByfinaldateAsc();
  // List<Reminder> findAll();
}
