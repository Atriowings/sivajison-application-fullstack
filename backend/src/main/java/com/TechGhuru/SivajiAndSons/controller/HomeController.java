package com.TechGhuru.SivajiAndSons.controller;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import com.TechGhuru.SivajiAndSons.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TechGhuru.SivajiAndSons.dto.LoggedInUser;
import com.TechGhuru.SivajiAndSons.dto.customMail;

import com.TechGhuru.SivajiAndSons.model.CustomerFlightDetails;
import com.TechGhuru.SivajiAndSons.model.CustomersHotelDetails;
import com.TechGhuru.SivajiAndSons.model.Reminder;
import com.TechGhuru.SivajiAndSons.model.airlinesWithLogo;
import com.TechGhuru.SivajiAndSons.model.users;
import com.TechGhuru.SivajiAndSons.repository.airlinelogorepo;
import com.TechGhuru.SivajiAndSons.repository.customerbookrepo;
import com.TechGhuru.SivajiAndSons.service.MailService;
import com.TechGhuru.SivajiAndSons.service.SendMail;
import com.TechGhuru.SivajiAndSons.service.userService;

import jakarta.mail.MessagingException;

// @CrossOrigin(origins ="https://admin.sivajison.com")
@RestController
@CrossOrigin(origins ="*")
public class HomeController {

	@Autowired
	private userService userservice;
	@Autowired
	SendMail sendmail;
	@Autowired
	customerbookrepo bookrepo;
	@Autowired
	airlinelogorepo logorepo;
	@Autowired
	MailService mservice;
	@Autowired
	private TwilioService
			twilioService;
			
	@GetMapping("/customer/hotel/{number}")
	public List<CustomersHotelDetails> customerhotel(@PathVariable String number) {
		return bookrepo.findByCustomernumberOrderByIdDesc(number);
	}
	
	@GetMapping("/customer/flight/{number}")
	public List<CustomerFlightDetails> customerflight(@PathVariable String number) {	
		  if (number.matches("\\d{10}")) { // If it's a 10-digit phone number
        return bookrepo.findByCustomernumberFlightOrderByIdDesc(number);
    } else {
        return bookrepo.findBypnrFlightOrderByIdDesc(number);
    }
	}


	@GetMapping("/admin/home")
	public String adminhome() {
		
		return "admin home";
	}

	@PostMapping("/mail")
	public String mail(@RequestBody customMail custommail) {
		// Import required for time formatting
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
		String formattedDateTime = now.format(formatter);
			String htmlcontent = "<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "  <meta charset=\"UTF-8\">\r\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
					+ "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "  <title>Sivaji Son - Custom Mail</title>\r\n"
					+ "</head>\r\n"
					+ "<body style=\"margin:0;padding:10px 0;background-color:#f0f8ff;color:#333;text-align:center;font-family:Verdana,Geneva,sans-serif;\">\r\n"
					+ "  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;margin:auto;background-color:#ffffff;border-radius:10px;box-shadow:0 4px 8px rgba(0,0,0,0.1);overflow:hidden;\">\r\n"
					+ "    <!-- Header -->\r\n"
					+ "    <tr>\r\n"
					+ "      <td style=\"background-color:#178079;padding:20px;text-align:center;\">\r\n"
					+ "        <img src=\"https://sivajison.com/mainlogo/image.png\" width=\"150\" alt=\"Logo\" style=\"display:block;margin:0 auto;border-radius:5%;background-color:#178079;\">\r\n"
					+ "      </td>\r\n"
					+ "    </tr>\r\n"
					+ "\r\n"
					+ "    <!-- Content -->\r\n"
					+ "    <tr>\r\n"
					+ "      <td style=\"padding:20px;font-family:Tahoma,Geneva,sans-serif;text-align:left;\">\r\n"
					+ "        <h1 style=\"margin:0 0 15px;font-size:22px;color:#178079;\">"+custommail.getSubject()+"</h1>\r\n"
					+ "        <div style=\"background-color:#e9e4e4; width: 600px; \">\r\n"
					+ "      "+custommail.getContent()+"\r\n"
					+ "        </div>\r\n"
					+ "      </td>\r\n"
					+ "    </tr>\r\n"
					+ "\r\n"
					+ "    <!-- Footer -->\r\n"
					+ "    <tr>\r\n"
					+ "      <td style=\"background-color:#178079;padding:20px;text-align:center;color:#ffffff;font-size:16px;\">\r\n"
					+ "        <p style=\"margin:0 0 10px;\">\r\n"
					+ "          <span style=\"display:inline-block;background-color:#ff5722;color:#ffffff;padding:10px 20px;border-radius:5px;font-weight:bold;\">\r\n"
					+ "            SIVAJI SON TOURS AND TRAVELS PRIVATE LIMITED\r\n"
					+ "          </span>\r\n"
					+ "        </p>\r\n"
					+ "        <p style=\"margin:0 0 10px;font-size:14px;\">\r\n"
					+ "          Email: saranraj@sivajison.com<br>\r\n"
					+ "          VSD Plaza, AA Block, 2nd Avenue, Anna Nagar, Chennai, Tamilnadu, India – 600040\r\n"
					+ "        </p>\r\n"
					+ "        <p style=\"margin:0;font-size:14px;\">\r\n"
					+ "          Need help? Call us at <strong>9655150814</strong> or visit\r\n"
					+ "          <a href=\"https://admin.sivajison.com/customer\" style=\"color:#ffeb3b;text-decoration:none;\">our portal</a>.\r\n"
					+ "        </p>\r\n"
					+ "      </td>\r\n"
					+ "    </tr>\r\n"
					+ "  </table>\r\n"
					+ "</body>\r\n"
					+ "</html>";
			custommail.getToaddress().forEach(a -> {
				try {
					sendmail.sendhtmlmail(a, custommail.getSubject(), htmlcontent);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}
			});


		return "All mail sent";
	}

	@PostMapping("/register")
	public users register(@RequestBody users user) {
//		System.out.println("asdfasdf "+ user.getPassword());
		return userservice.register(user);
		
	}
	
	@PostMapping("/login")
	public LoggedInUser login(@RequestBody users user) {
		
		return userservice.verify(user);
		
	}



	@GetMapping("/logo/{name}")
	public ResponseEntity<Resource> logoprint( @PathVariable String name){

		try {
			List<airlinesWithLogo> file = logorepo.getlogo(name);
			String filepath = file.get(0).getLogopath();
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

	@PostMapping("/testmail")
	public static void testmail() {
		
	}

	
}
