package com.TechGhuru.SivajiAndSons.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TechGhuru.SivajiAndSons.model.dailyreport;

public interface DailyReportRepo extends JpaRepository<dailyreport, Integer>{

	
	@Query(value = "select a from dailyreport a where a.date = :date")
	List<dailyreport> getreportbydate(LocalDate date);



}
