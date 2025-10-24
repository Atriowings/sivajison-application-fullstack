package com.TechGhuru.SivajiAndSons.model;

import java.time.LocalDate;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class CustomerFlightDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String customername;
	private String customernumber;
	private String customermail;
	private String cutomerid;
	private LocalDate issuedate;
	
	private LocalDate Traveldate;
	// private String Airlines;
//	private String sector;
	private String pnr;
	// private String familymebers;
//	@Column(unique=true)
//	private String ticketnumber;
	
	 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinColumn(name = "input_data_id")
	    private List<airlines> airlines;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinColumn(name = "Flight_id")
	    private List<passengername> passengername;
	
	
	public List<airlines> getAirlines() {
		return airlines;
	}
	public void setAirlines(List<airlines> airlines) {
		this.airlines = airlines;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCustomernumber() {
		return customernumber;
	}
	public void setCustomernumber(String customernumber) {
		this.customernumber = customernumber;
	}
	public String getCustomermail() {
		return customermail;
	}
	public void setCustomermail(String customermail) {
		this.customermail = customermail;
	}
	public String getCutomerid() {
		return cutomerid;
	}
	public void setCutomerid(String cutomerid) {
		this.cutomerid = cutomerid;
	}

	
	public List<passengername> getPassengername() {
		return passengername;
	}
	public void setPassengername(List<passengername> passengername) {
		this.passengername = passengername;
	}
	
	
	public LocalDate getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(LocalDate issuedate) {
		this.issuedate = issuedate;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public LocalDate getTraveldate() {
		return Traveldate;
	}
	public void setTraveldate(LocalDate traveldate) {
		Traveldate = traveldate;
	}

}
