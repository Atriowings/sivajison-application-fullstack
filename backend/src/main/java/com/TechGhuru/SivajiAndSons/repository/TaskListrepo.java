package com.TechGhuru.SivajiAndSons.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TechGhuru.SivajiAndSons.model.TaskList;

public interface TaskListrepo extends JpaRepository<TaskList, Integer>{

	
	@Query(value = "select a from TaskList a where a.empid = :empid")
	List<TaskList> findByEmpid(String empid);

	
	
}
