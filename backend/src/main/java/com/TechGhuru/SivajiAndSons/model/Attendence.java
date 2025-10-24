package com.TechGhuru.SivajiAndSons.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Attendence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String empid;
	private String staffname;
	private LocalDate date;
	private LocalTime intime;
	
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getIntime() {
		return intime;
	}
	public void setIntime(LocalTime intime) {
		this.intime = intime;
	}

}
