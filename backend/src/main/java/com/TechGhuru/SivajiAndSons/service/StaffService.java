package com.TechGhuru.SivajiAndSons.service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.TechGhuru.SivajiAndSons.model.Airportcodes;
import com.TechGhuru.SivajiAndSons.model.Attendence;
import com.TechGhuru.SivajiAndSons.model.CustomerFlightDetails;
import com.TechGhuru.SivajiAndSons.model.Customerotherdetails;
import com.TechGhuru.SivajiAndSons.model.CustomersHotelDetails;
import com.TechGhuru.SivajiAndSons.model.TaskList;
import com.TechGhuru.SivajiAndSons.model.airlinesWithLogo;
import com.TechGhuru.SivajiAndSons.model.customerbook;
import com.TechGhuru.SivajiAndSons.model.dailyreport;
import com.TechGhuru.SivajiAndSons.repository.Airportcoderepo;
import com.TechGhuru.SivajiAndSons.repository.AttendenceRepo;
import com.TechGhuru.SivajiAndSons.repository.CustomerFlightRepo;
import com.TechGhuru.SivajiAndSons.repository.CustomerHotelDetailsRepo;
import com.TechGhuru.SivajiAndSons.repository.Customerotherdetailsrepo;
import com.TechGhuru.SivajiAndSons.repository.DailyReportRepo;
import com.TechGhuru.SivajiAndSons.repository.TaskListrepo;
import com.TechGhuru.SivajiAndSons.repository.airlinelogorepo;
import com.TechGhuru.SivajiAndSons.repository.customerbookrepo;
import com.TechGhuru.SivajiAndSons.repository.AirlinesRepo;



@Service
public class StaffService {

	@Autowired
	AttendenceRepo attendencerepo;
	@Autowired
	TaskListrepo taskrepo;
	@Autowired
	CustomerHotelDetailsRepo hotelrepo;
	@Autowired
	DailyReportRepo dailytaskrepo;
	@Autowired
	CustomerFlightRepo flightrepo;
	@Autowired
	customerbookrepo customerrepo;
	@Autowired
	Customerotherdetailsrepo otherdetailsrepo;
	@Autowired
	Airportcoderepo coderepo;
	@Autowired
	airlinelogorepo logorepo;
	// airlinesrepo newly added
	@Autowired
    AirlinesRepo airlinesRepo;

	
	public Attendence registerAttendence(Attendence attendence) {
		attendence.setDate(LocalDate.now()) ;
		attendence.setIntime(LocalTime.now()) ;
		return attendencerepo.save(attendence);
		
	}

	public List<TaskList> viewtask(String empid) {
		// TODO Auto-generated method stub
		return taskrepo.findByEmpid(empid);
	}
	
	public TaskList updatetask(TaskList taskdetails) {
		taskdetails.setStatus("completed");
		return taskrepo.save(taskdetails);
	}
	
	public String savehoteldetails(CustomersHotelDetails hoteldetails) {
//		hoteldetails.set
		hotelrepo.save(hoteldetails);
		customerbook customer = new customerbook();
		customer.setCustomername(hoteldetails.getCustomername());
		customer.setCustomermail(hoteldetails.getCustomermail());
		customer.setCustomernumber(hoteldetails.getCustomernumber());
		try {
			customerrepo.save(customer);
		}catch (Exception e) {
			// TODO: handle exception
			return "Already exists in customer book";
		}
		
		
		return "Successfully Submitted !!";
	}
	
	public String saveflightdetails(CustomerFlightDetails flightdetails) {
		flightdetails.setTraveldate(flightdetails.getAirlines().get(0).getDeparturetime().toLocalDate());
		flightrepo.save(flightdetails);
		
		customerbook customer = new customerbook();
		customer.setCustomername(flightdetails.getCustomername());
		customer.setCustomermail(flightdetails.getCustomermail());
		customer.setCustomernumber(flightdetails.getCustomernumber());
		try {
			customerrepo.save(customer);
		}catch (Exception e) {
			// TODO: handle exception
			return "Already exists in customer book";
		}
		
		return "Successfully Submitted !!";
	}

	public List<Attendence> attendencechecking(String empid, LocalDate date) {
		// TODO Auto-generated method stub
		System.out.println(date);
		return attendencerepo.findattendence(empid,date);
		 
	}

	public String senddailyreport(dailyreport report) {
		// TODO Auto-generated method stub
		
		report.setDate(LocalDate.now());
		dailytaskrepo.save(report);
		return "Report submitted";
	}

	public void sendremark(TaskList task) {
		// TODO Auto-generated method stub
		task.setStatus("pending");
		taskrepo.save(task);
	}

	
	
	public String saveotherdetails(Customerotherdetails otherdetails) {
		// TODO Auto-generated method stub
		customerbook customer = new customerbook();
		customer.setCustomername(otherdetails.getCustomername());
		customer.setCustomermail(otherdetails.getCustomermail());
		customer.setCustomernumber(otherdetails.getCustomernumber());
		try {
			customerrepo.save(customer);
		}catch (Exception e) {
			// TODO: handle exception
//			return "Already exists in customer book";
		}
		
		otherdetailsrepo.save(otherdetails);
		return "Added Successfully";
	}

	public List<Airportcodes> getairportcode() {
		
		// TODO Auto-generated method stub
		return coderepo.findAll();
	}

	public List<airlinesWithLogo> getairline() {
		// TODO Auto-generated method stub
		return logorepo.findAll();
	}

	public List<airlinesWithLogo> getairlinelogo(String name) {
		// TODO Auto-generated method stub
		return logorepo.getlogo(name);
	}

	


}
