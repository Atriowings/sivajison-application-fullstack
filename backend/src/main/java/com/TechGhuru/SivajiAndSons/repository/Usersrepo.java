package com.TechGhuru.SivajiAndSons.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TechGhuru.SivajiAndSons.model.users;

import jakarta.transaction.Transactional;

@Repository
public interface Usersrepo extends JpaRepository<users, Integer>{

 	users findByUsername(String username);
 	
 	@Modifying
 	@Transactional
 	@Query(value = "delete from users u where u.empid = :empid")
 	int DeleteUserByEmpId(String empid);

 	@Query("SELECT s FROM users s WHERE NOT EXISTS ( SELECT 1 FROM Attendence a WHERE a.empid = s.empid AND a.date = :today )")
	List<users> currentdayabsentees(LocalDate today);
 	
}
