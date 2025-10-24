package com.TechGhuru.SivajiAndSons.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
import com.TechGhuru.SivajiAndSons.model.airlinesWithLogo;
import com.TechGhuru.SivajiAndSons.model.customerbook;
import com.TechGhuru.SivajiAndSons.model.dailyreport;
import com.TechGhuru.SivajiAndSons.model.users;
import com.TechGhuru.SivajiAndSons.repository.AdminFilesRepo;
import com.TechGhuru.SivajiAndSons.repository.Airportcoderepo;
import com.TechGhuru.SivajiAndSons.repository.AttendenceRepo;
import com.TechGhuru.SivajiAndSons.repository.CustomerFlightRepo;
import com.TechGhuru.SivajiAndSons.repository.CustomerHotelDetailsRepo;
import com.TechGhuru.SivajiAndSons.repository.Customerotherdetailsrepo;
import com.TechGhuru.SivajiAndSons.repository.DailyReportRepo;
import com.TechGhuru.SivajiAndSons.repository.ReminderRepo;
import com.TechGhuru.SivajiAndSons.repository.ResourceRepo;
import com.TechGhuru.SivajiAndSons.repository.TaskListrepo;
import com.TechGhuru.SivajiAndSons.repository.Usersrepo;
import com.TechGhuru.SivajiAndSons.repository.airlinelogorepo;
import com.TechGhuru.SivajiAndSons.repository.customerbookrepo;

import jakarta.servlet.ServletContext;

@Service
public class AdminService {

	@Autowired
	AttendenceRepo attendencerepo;
	@Autowired
	Usersrepo userrepo;
	@Autowired
	TaskListrepo tasklistrepo;
	@Autowired
	CustomerHotelDetailsRepo hotelrepo;
	@Autowired
	CustomerFlightRepo flightrepo;
	@Autowired
	AdminFilesRepo adminfilesrepo;
	@Autowired
	DailyReportRepo reportrepo;
	@Autowired
	ServletContext servletContext;
	@Autowired
	airlinelogorepo logorepo;
	@Autowired
	customerbookrepo customerrepo;
	@Autowired
	Customerotherdetailsrepo otherdetailsrepo;
	@Autowired
	Airportcoderepo coderepo;
	@Autowired
	ReminderRepo reminderrepo;
	@Autowired
	ResourceRepo resoucerepo;


//	Attendance
	public List<Attendence> getattendence() {

		return attendencerepo.findAll();
	}

	public List<Attendence> getcurrentdayattendance() {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
		System.out.println(today);
		return attendencerepo.getcurrentdayattendance(today);
	}

	public List<users> getcurrentdayabsentees() {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();

		return userrepo.currentdayabsentees(today);
	}

	public List<Attendence> getmonthlyattendence(MonthlyAttendence monthlyattendence) {
		// TODO Auto-generated method stub
		int month = monthlyattendence.getMonth();
		String empid = monthlyattendence.getEmpid();
		int year = monthlyattendence.getYear();
		return attendencerepo.getmonthlyattendence(month,empid,year);
	}

//	Staff Management
	public users editStaff(users user) {

		return userrepo.save(user);
	}

	public void deleteStaff(String empid) {

		  userrepo.DeleteUserByEmpId(empid);
	}

	public List<users> getallstaff() {


				List<users> u =	userrepo.findAll();
//				u.forEach(a -> a.setRole("staff"));
				return u;
	}

	public TaskList addtask(TaskList task) {
		task.setStatus("pending");
		return tasklistrepo.save(task);

	}

  
   


	public List<CustomersHotelDetails> getcustomerhoteldetails(){
		// return hotelrepo.findAll();
		// new line added
		return hotelrepo.findAllByOrderByIdDesc();
	}

	public List<CustomerFlightDetails> getcustomerflightdetails() {
		// TODO Auto-generated method stub
		// return flightrepo.findAll();
		// new line added
		return flightrepo.findAllByOrderByIdDesc();
	}

	public List<TaskList> getalltask() {
		return tasklistrepo.findAll();
		}

	public void deletetask(int id) {
		// TODO Auto-generated method stub
		tasklistrepo.deleteById(id);
	}

	public List<CustomerFlightDetails> searchflightcustomer(String number) {
		// TODO Auto-generated method stub
		return flightrepo.findbyCustomernumber(number);
	}


	 @Value("${file.upload-dir}")
	    private String uploadDir;
	public ResponseEntity<String> uploadingfile(MultipartFile file) {
		// TODO Auto-generated method stub


		 try {
	            // Ensure the upload directory exists
	            File directory = new File(uploadDir);
	            if (!directory.exists()) {
	                directory.mkdirs();  // Create the directory if it doesn't exist
	            }

	            // Get the file name
	            String fileName = file.getOriginalFilename();

	            // Create a file object to save the uploaded file
	            File destinationFile = new File(directory.getAbsolutePath() + File.separator + fileName);
	            String filepath = directory.getAbsolutePath() + File.separator + fileName;
	            // Save the file
	            file.transferTo(destinationFile);

	            AdminFiles filetosave = new AdminFiles();
	            filetosave.setFilename(fileName);
	            filetosave.setFilepath(filepath);
	            adminfilesrepo.save(filetosave);

	            return new ResponseEntity<>("File uploaded successfully: " + fileName, HttpStatus.OK);
	        } catch (IOException e) {
	            return new ResponseEntity<>("Failed to upload file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	}

//	Daily Report

	public List<dailyreport> getdailyreport(LocalDate date) {
		// TODO Auto-generated method stub
		return reportrepo.getreportbydate(date);
	}

	public String deletedailyreport(int id) {
		// TODO Auto-generated method stub
		reportrepo.deleteById(id);
		return "Deleted Successfully";
	}

	public void reassigntask(TaskList task) {
		// TODO Auto-generated method stub
		System.out.println(task.getEmpid());
		System.out.println(task.getId());
		System.out.println(task.getStaffname());
		System.out.println(task.getTask());
		System.out.println(task.getEmpid());
		task.setStatus("reassigned");
		tasklistrepo.save(task);
	}

//	add airline
	@Value("${file.upload-dir2}")
	private String uploaddirectory2;
	public ResponseEntity<String> addairline(MultipartFile logo,String airline,int id) {


		 try {
	            // Ensure the upload directory exists
			 System.out.println(uploaddirectory2);
	            File directory = new File(uploaddirectory2);
	            if (!directory.exists()) {
	                directory.mkdirs();  // Create the directory if it doesn't exist
	            }

	            // Get the file name
	            String fileName = logo.getOriginalFilename();

	            // Create a file object to save the uploaded file
	            File destinationFile = new File(directory.getAbsolutePath() + File.separator + fileName);
	            String filepath = directory.getAbsolutePath() + File.separator + fileName;
	            // Save the file
	            logo.transferTo(destinationFile);

	            airlinesWithLogo filetosave = new airlinesWithLogo();
	            filetosave.setAirline(airline);
	            filetosave.setLogopath(filepath);

	            airlinesWithLogo air = new airlinesWithLogo();
	            air.setAirline(airline);
	            air.setLogopath(filepath);
	            air.setId(id);

	            logorepo.save(air);

	            return new ResponseEntity<>("File uploaded successfully: " + fileName, HttpStatus.OK);
	        } catch (IOException e) {
	            return new ResponseEntity<>("Failed to upload file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	}


//	@Value("${file.upload-dir2}")
//	private String uploaddirectory2;
	public ResponseEntity<String> addnewairline(MultipartFile logo,String airline) {


		 try {
	            // Ensure the upload directory exists
			 System.out.println(uploaddirectory2);
	            File directory = new File(uploaddirectory2);
	            if (!directory.exists()) {
	                directory.mkdirs();  // Create the directory if it doesn't exist
	            }

	            // Get the file name
	            String fileName = logo.getOriginalFilename();

	            // Create a file object to save the uploaded file
	            File destinationFile = new File(directory.getAbsolutePath() + File.separator + fileName);
	            String filepath = directory.getAbsolutePath() + File.separator + fileName;
	            // Save the file
	            logo.transferTo(destinationFile);

	            airlinesWithLogo filetosave = new airlinesWithLogo();
	            filetosave.setAirline(airline);
	            filetosave.setLogopath(filepath);

	            airlinesWithLogo air = new airlinesWithLogo();
	            air.setAirline(airline);
	            air.setLogopath(filepath);
//	            air.setId(id);

	            logorepo.save(air);

	            return new ResponseEntity<>("File uploaded successfully: " + fileName, HttpStatus.OK);
	        } catch (IOException e) {
	            return new ResponseEntity<>("Failed to upload file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	}



	public List<AdminFiles> getallfiles() {
		// TODO Auto-generated method stub
		return adminfilesrepo.findAll();
	}

	public List<customerbook> getcustomer() {
		// TODO Auto-generated method stub
		return customerrepo.findAll();
	}

	public List<Customerotherdetails> getotherdetails() {
		// TODO Auto-generated method stub
		// return otherdetailsrepo.findAll();
		// new line added
		return otherdetailsrepo.findAllByOrderByIdDesc();
	}

	public ResponseEntity<String> deletefile(int id) {
		// TODO Auto-generated method stub
		Optional<AdminFiles> adminfile = adminfilesrepo.findById(id);
		String filepath = adminfile.get().getFilepath();
		File deletingfile = new File(filepath);
		if( deletingfile.delete()) {
			adminfilesrepo.deleteById(id);
			return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);

		}
		return new ResponseEntity<>("Try again", HttpStatus.OK);
	}

	public void addairportcode(Airportcodes code) {
		// TODO Auto-generated method stub
		coderepo.save(code);

	}

	public List<CustomersHotelDetails> gethotelreminder() {
		// TODO Auto-generated method stub
		return hotelrepo.gethoteldetailsbeforeday();
	}

	public List<CustomerFlightDetails> getflightreminder() {
		// TODO Auto-generated method stub
		return flightrepo.getflightdetailsbeforeday();
	}

	public ResponseEntity<Resource> downloadfile(int id) throws IOException {
		// TODO Auto-generated method stub
		try {
		Optional<AdminFiles> file = adminfilesrepo.findById(id);
		String filepath = file.get().getFilepath();
		Path filepath1 = Paths.get(filepath);
		String contentType = Files.probeContentType(filepath1);
        if (contentType == null) {
            contentType = "application/octet-stream"; // Default if content type can't be determined
        }

		InputStreamResource resource = new InputStreamResource(new FileInputStream(filepath));

		  String contentDisposition = "attachment; filename=\"" + resource.getFilename() + "\"";
	    return ResponseEntity.ok()
	    		  .contentType(MediaType.parseMediaType(contentType))
	            .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
	            .body(resource);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	public Reminder addreminder(Reminder reminder) {
		// TODO Auto-generated method stub
		return reminderrepo.save(reminder);
	}

	public List<Reminder> getreminder() {
		// TODO Auto-generated method stub
		return reminderrepo.findAll();
		// newly added below 
		// return reminderrepo.findAllByOrderByIdDesc();
		// return reminderrepo.findAllByOrderByDateAsc();
	}


	public void deletereminder(int id) {
		// TODO Auto-generated method stub
		reminderrepo.deleteById(id);
	}

	public void markasabsent(int id) {
		// TODO Auto-generated method stub
		attendencerepo.deleteById(id);
	}

	public Resourcelink addresource(Resourcelink resource) {
		// TODO Auto-generated method stub
		return resoucerepo.save(resource);
	}

	public List<Resourcelink> getresource() {
		// TODO Auto-generated method stub
		return resoucerepo.findAll();
	}

	public void deleteresource(int id) {
		// TODO Auto-generated method stub
		resoucerepo.deleteById(id);
	}

}
