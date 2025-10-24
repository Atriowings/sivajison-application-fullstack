package com.TechGhuru.SivajiAndSons.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Airportcodes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String airportcode;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAirportcode() {
		return airportcode;
	}
	public void setAirportcode(String airportcode) {
		this.airportcode = airportcode;
	}
	
	
}
