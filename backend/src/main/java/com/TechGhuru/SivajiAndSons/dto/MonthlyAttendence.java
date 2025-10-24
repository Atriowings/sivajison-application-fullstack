package com.TechGhuru.SivajiAndSons.dto;

import org.springframework.stereotype.Component;

@Component
public class MonthlyAttendence {
	
	
	private int month;
	private String empid;
	private int year;
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
//	public int getEmpid() {
//		return empid;
//	}
//	public void setEmpid(int empid) {
//		this.empid = empid;
//	}
	

	public int getYear() {
		return year;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public void setYear(int year) {
		this.year = year;
	}

}
