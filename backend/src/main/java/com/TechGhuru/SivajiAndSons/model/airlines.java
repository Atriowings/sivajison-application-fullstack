package com.TechGhuru.SivajiAndSons.model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class airlines {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ticketnumber;
	private String flightnumber;
	private String departureairport;
	private String arrivalairport;
	private LocalDateTime departuretime;
	private LocalDateTime arrivaltime;
	private String airline;
	private String travelclass;
	private String baggageallowance;
	private String terminal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTicketnumber() {
		return ticketnumber;
	}
	public void setTicketnumber(String ticketnumber) {
		this.ticketnumber = ticketnumber;
	}
	public String getFlightnumber() {
		return flightnumber;
	}
	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}
	public String getDepartureairport() {
		return departureairport;
	}
	public void setDepartureairport(String departureairport) {
		this.departureairport = departureairport;
	}
	public String getArrivalairport() {
		return arrivalairport;
	}
	public void setArrivalairport(String arrivalairport) {
		this.arrivalairport = arrivalairport;
	}

	
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getTravelclass() {
		return travelclass;
	}
	public void setTravelclass(String travelclass) {
		this.travelclass = travelclass;
	}
	
	public String getBaggageallowance() {
		return baggageallowance;
	}
	public void setBaggageallowance(String baggageallowance) {
		this.baggageallowance = baggageallowance;
	}
// Terminal newly added
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	public LocalDateTime getDeparturetime() {
		return departuretime;
	}
	public void setDeparturetime(LocalDateTime departuretime) {
		this.departuretime = departuretime;
	}
	public LocalDateTime getArrivaltime() {
		return arrivaltime;
	}
	public void setArrivaltime(LocalDateTime arrivaltime) {
		this.arrivaltime = arrivaltime;
	}
	
	 
	
	
	
}
