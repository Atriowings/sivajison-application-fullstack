package com.TechGhuru.SivajiAndSons.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reminder {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String visaappointment;
	private String updates;
	private String paymentdues;
	private String finaldate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVisaappointment() {
		return visaappointment;
	}
	public void setVisaappointment(String visaappointment) {
		this.visaappointment = visaappointment;
	}
	public String getUpdates() {
		return updates;
	}
	public void setUpdates(String updates) {
		this.updates = updates;
	}
	public String getPaymentdues() {
		return paymentdues;
	}
	public void setPaymentdues(String paymentdues) {
		this.paymentdues = paymentdues;
	}
	public String getFinaldate() {
		return finaldate;
	}
	public void setFinaldate(String finaldate) {
		this.finaldate = finaldate;
	}
}
