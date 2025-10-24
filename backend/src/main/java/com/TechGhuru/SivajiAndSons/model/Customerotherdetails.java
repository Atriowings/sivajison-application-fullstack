package com.TechGhuru.SivajiAndSons.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customerotherdetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String customername;
	private String customerid;
	private String customernumber;
	private String customermail;
	
	@Column(name = "visadetails", columnDefinition = "TEXT")
	private String visadetails;
	@Column(name = "passportdetails", columnDefinition = "TEXT")
	private String passportdetails;
	@Column(name = "travelinsurance", columnDefinition = "TEXT")
	private String travelinsurance;
	@Column(name = "busticket", columnDefinition = "TEXT")
	private String busticket;
	
	private LocalDate submissiondate;
	private LocalDate collectiondate;
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
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
	public String getVisadetails() {
		return visadetails;
	}
	public void setVisadetails(String visadetails) {
		this.visadetails = visadetails;
	}
	public String getPassportdetails() {
		return passportdetails;
	}
	public void setPassportdetails(String passportdetails) {
		this.passportdetails = passportdetails;
	}
	public String getTravelinsurance() {
		return travelinsurance;
	}
	public void setTravelinsurance(String travelinsurance) {
		this.travelinsurance = travelinsurance;
	}
	public String getBusticket() {
		return busticket;
	}
	public void setBusticket(String busticket) {
		this.busticket = busticket;
	}
	public LocalDate getSubmissiondate() {
		return submissiondate;
	}
	public void setSubmissiondate(LocalDate submissiondate) {
		this.submissiondate = submissiondate;
	}
	public LocalDate getCollectiondate() {
		return collectiondate;
	}
	public void setCollectiondate(LocalDate collectiondate) {
		this.collectiondate = collectiondate;
	}
}
