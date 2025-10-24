package com.TechGhuru.SivajiAndSons.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TechGhuru.SivajiAndSons.model.Attendence;



@Repository
public interface AttendenceRepo extends JpaRepository<Attendence, Integer> {
	
 	@Query(value = "select a from Attendence a where a.date = :date ORDER BY a.date DESC")
 	List<Attendence> getcurrentdayattendance(LocalDate date);

 	@Query("SELECT a FROM Attendence a WHERE MONTH(a.date) = :month and YEAR(a.date) = :year and a.empid = :empid ORDER BY a.date DESC")
	List<Attendence> getmonthlyattendence(int month, String empid, int year);

 	@Query(value = "select a from Attendence a where a.date = :date and a.empid = :empid ORDER BY a.date DESC")
 	List<Attendence> findattendence(String empid, LocalDate date);

}