package com.TechGhuru.SivajiAndSons.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class CustomersHotelDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String customername;
	private String customernumber;
	private String customermail;
	@Column(unique=true)
	private String bookingid;
	private String hotelname;
	private int noofnights;
	private LocalDate checkindate;
	private LocalDate checkoutdate;
	private String hotelcontactperson;
	private String hcn;
	

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "bookingid")
	private List<CustomersHotelDetails> customers_hotel_details;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "hotel_id")
	private List<hotelguest> passengername;




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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookingid() {
		return bookingid;
	}
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public int getNoofnights() {
		return noofnights;
	}
	public void setNoofnights(int noofnights) {
		this.noofnights = noofnights;
	}
	public LocalDate getCheckindate() {
		return checkindate;
	}
	public void setCheckindate(LocalDate checkindate) {
		this.checkindate = checkindate;
	}
	public LocalDate getCheckoutdate() {
		return checkoutdate;
	}
	public void setCheckoutdate(LocalDate checkoutdate) {
		this.checkoutdate = checkoutdate;
	}
	public String getHotelcontactperson() {
		return hotelcontactperson;
	}
	public void setHotelcontactperson(String hotelcontactperson) {
		this.hotelcontactperson = hotelcontactperson;
	}
	public String getHcn() {
		return hcn;
	}
	public void setHcn(String hcn) {
		this.hcn = hcn;
	}
// newly added
	public List<hotelguest> getPassengername() {
		return passengername;
	}
	public void setPassengername(List<hotelguest> passengername) {
		this.passengername = passengername;
	}
}


