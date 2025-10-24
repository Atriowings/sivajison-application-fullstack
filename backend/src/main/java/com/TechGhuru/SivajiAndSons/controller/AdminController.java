package com.TechGhuru.SivajiAndSons.controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TechGhuru.SivajiAndSons.dto.MonthlyAttendence;
import com.TechGhuru.SivajiAndSons.model.AdminFiles;
import com.TechGhuru.SivajiAndSons.model.Airportcodes;
import com.TechGhuru.SivajiAndSons.model.Attendence;
import com.TechGhuru.SivajiAndSons.model.CustomerFlightDetails;
import com.TechGhuru.SivajiAndSons.model.Customerotherdetails;
import com.TechGhuru.SivajiAndSons.model.CustomersHotelDetails;
import com.TechGhuru.SivajiAndSons.model.Reminder;
import com.TechGhuru.SivajiAndSons.model.Resourcelink;
import com.TechGhuru.SivajiAndSons.model.TaskList;
import com.TechGhuru.SivajiAndSons.model.customerbook;
import com.TechGhuru.SivajiAndSons.model.dailyreport;
import com.TechGhuru.SivajiAndSons.model.users;
import com.TechGhuru.SivajiAndSons.service.AdminService;
import com.TechGhuru.SivajiAndSons.service.userService;



// @CrossOrigin(origins = "https://admin.sivajison.com")
// @CrossOrigin(origins = "https://localhost:3000")


@RestController
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	AdminService adminservice;
	@Autowired
	userService userservice;
	
//	ATTENDANCE CONTROLLES
	@GetMapping("/admin/AttendenceHistory")
	public List<Attendence> GetAttendence() {
		return adminservice.getattendence();
	}
	
	@GetMapping("/admin/CurrentDayAttendance")
	public List<Attendence> CurrentDayAttendance() {
		return adminservice.getcurrentdayattendance();
	}
	
	@GetMapping("/admin/CurrentDayAbsentees")
	public List<users> CurrentDayAbsentees(){
		return adminservice.getcurrentdayabsentees();
	}
	
	@PostMapping("/admin/MonthlyAttendence")
	public List<Attendence> MonthlyAttendence(@RequestBody MonthlyAttendence monthlyattendence) {
		return adminservice.getmonthlyattendence(monthlyattendence);
	}
	
	@PostMapping("/admin/MarkAsAbsent/{id}")
	public void markasabsent(@PathVariable int id) {
		adminservice.markasabsent(id);
	}
	
//	STAFF CRUD CONTROLLERS
	@GetMapping("/admin/GetAllStaff")
	public List<users> GetAllStaff() {  
		return adminservice.getallstaff();
	}
	
	
	@PostMapping("/admin/AddStaff")
	public users AddStaff(@RequestBody users user) {
		
		return userservice.register(user);
		
	}
	
	@PutMapping("/admin/EditStaff/{id}")
	public users EditStaff(@PathVariable int id ,@RequestBody users user) {
		user.setId(id);
		user.setRole("staff");
//		
		return userservice.register(user);
		
	}
	
	@PostMapping("/admin/DeleteStaff/{empid}")
	
	public String DeleteStaff(@PathVariable String empid) {
		
		adminservice.deleteStaff(empid);
		return "Deleted";
	}
	
//	TASK CONTROLLERS
	@PostMapping("/admin/AddTask")
	public TaskList AddTask(@RequestBody TaskList task) {
		
		return adminservice.addtask(task);
	}
	
	@GetMapping("admin/GetAllTasks")
	public List<TaskList> getalltask(){
		return adminservice.getalltask();
	}
	
	@PostMapping("/admin/DeleteTask/{id}")	
	public String Deletetask(@PathVariable int id) {
		
		adminservice.deletetask(id);
		return "Deleted";
	}
	
	@PostMapping("admin/ReassignTask")
	public String reassigntask(@RequestBody TaskList task) {
		adminservice.reassigntask(task);
		return "Re-Assigned";
	}

//	Customers Details
	@GetMapping("admin/GetHotelCustomers")
	public List<CustomersHotelDetails> gethoteldetails(){
		
		return adminservice.getcustomerhoteldetails();
	}



	@GetMapping("admin/GetFlightCustomers")
	public List<CustomerFlightDetails> getflightdetails(){
		return adminservice.getcustomerflightdetails();
	}
	
	@GetMapping("admin/GetOtherCustomers")
	public List<Customerotherdetails> getotherdetails(){
		return adminservice.getotherdetails();
	}
	
	@GetMapping("admin/GetCustomers")
	public List<customerbook> getcustomer(){
		return adminservice.getcustomer();
	}
	
	@GetMapping("admin/SearchFlightCustomer/{number}")
	public List<CustomerFlightDetails> searchflightcustomer(@PathVariable String number){
		return adminservice.searchflightcustomer(number);
	}
	

//	FileUpload

	 
	 @PostMapping("admin/UploadFile")
	    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		 
		 return adminservice.uploadingfile(file);
	       
	    }
	 
	 @PostMapping("admin/DeleteFile/{id}")
	    public ResponseEntity<String> deletefile(@PathVariable int id) {
		 
		 return adminservice.deletefile(id);
	       
	    }
	 
	 @GetMapping("admin/GetFiles")
	 public List<AdminFiles> getfiles(){
		 return adminservice.getallfiles();
	 }
	 
	
//	Daily Report
	 
	 @GetMapping("admin/GetDailyReport/{date}")
		public List<dailyreport> getdailyreport(@PathVariable LocalDate date){
			return adminservice.getdailyreport(date);
		}
		
	 @PostMapping("admin/DeleteDailyReport/{id}")
	 	public String deletedailyreport(@PathVariable int id ) {
		 return adminservice.deletedailyreport(id);
		 
	 }
	 
	 @PostMapping("/admin/NewAirline")
		public ResponseEntity<String> addairline(@RequestParam("logo") MultipartFile logo, @RequestParam("airline") String airline) {
		
			return adminservice.addnewairline(logo,airline);
			
		}
	 
	 
//	 Add Airline and logo
	 @PostMapping("/admin/AddAirline")
		public ResponseEntity<String> addairline(@RequestParam("logo") MultipartFile logo, @RequestParam("airline") String airline, @RequestParam int id) {
		 System.out.println(id);
			return adminservice.addairline(logo,airline,id);
			
		}
	 
//	 Adding airport code
	 @PostMapping("admin/AddAirportCode")
	 public String addairportcode(@RequestBody Airportcodes code) {
		 	adminservice.addairportcode(code);
		 return "";
	 }
	 
//	 Reminder
	 @GetMapping("admin/GetHotelReminder")
	 public List<CustomersHotelDetails> gethotelreminder(){
		return adminservice.gethotelreminder();
	 }
	 
	 @GetMapping("admin/GetFlightReminder")
	 public List<CustomerFlightDetails> getflightreminder(){
		return adminservice.getflightreminder();
	 }
	 
//	 Admin download file
	 @GetMapping("admin/DownloadFile/{id}")
	 public ResponseEntity<org.springframework.core.io.Resource> downloadfile(@PathVariable int id) {
		 
		 	try {
				return adminservice.downloadfile(id);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		 
		    
	 }
	 
	 
//	 Reminder
	 
	 	@PostMapping("/admin/AddReminder")
		public Reminder addreminder(@RequestBody Reminder reminder) {
			return adminservice.addreminder(reminder);
		}
	 	
	 	@GetMapping("/admin/GetReminder")
		public List<Reminder> getreminder() {
			return adminservice.getreminder();	
		}
	 	
	 	@PostMapping("/admin/DeleteReminder/{id}")
		public void deletereminder(@PathVariable int id) {
			adminservice.deletereminder(id);
		}
	 		
	 
//	 Resource
	 	
	 	@PostMapping("/admin/AddResource")
		public Resourcelink addresource(@RequestBody Resourcelink resource) {
			return adminservice.addresource(resource);
		}
	 	
	 	@PostMapping("/admin/DeleteResource/{id}")
		public void deleteresource(@PathVariable int id) {
			 adminservice.deleteresource(id);
		}
	 	
	 	@GetMapping("/admin/GetResource")
		public List<Resourcelink> getresource() {
			return adminservice.getresource();
		}
	 	
}


