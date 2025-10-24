package com.TechGhuru.SivajiAndSons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TechGhuru.SivajiAndSons.model.AdminFiles;

@Repository
public interface AdminFilesRepo  extends JpaRepository<AdminFiles, Integer>{

}

