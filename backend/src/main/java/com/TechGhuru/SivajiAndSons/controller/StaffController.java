package com.TechGhuru.SivajiAndSons.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TechGhuru.SivajiAndSons.model.Airportcodes;
import com.TechGhuru.SivajiAndSons.model.Attendence;
import com.TechGhuru.SivajiAndSons.model.CustomerFlightDetails;
import com.TechGhuru.SivajiAndSons.model.Customerotherdetails;
import com.TechGhuru.SivajiAndSons.model.CustomersHotelDetails;
import com.TechGhuru.SivajiAndSons.model.TaskList;
import com.TechGhuru.SivajiAndSons.model.airlinesWithLogo;
import com.TechGhuru.SivajiAndSons.model.dailyreport;
import com.TechGhuru.SivajiAndSons.service.MailService;
import com.TechGhuru.SivajiAndSons.service.SendMail;
import com.TechGhuru.SivajiAndSons.service.StaffService;

import jakarta.mail.MessagingException;


// @CrossOrigin(origins = "https://admin.sivajison.com")
// @CrossOrigin(origins = "https://localhost:3000")
@RestController
@CrossOrigin(origins = "*")
public class StaffController {
	
	@Autowired
	StaffService staffservice;
	
	@Autowired
	MailService mail;
	
	@Autowired
	SendMail sendmail;
	
	@GetMapping("/staff")	
	public String home() {
		return "staffhome";
	}
	
//	Attendance
	
	@PostMapping("/staff/RegisterAttendence")
	public Attendence AttendenceRegister(@RequestBody Attendence  att) {
			
		return staffservice.registerAttendence(att);
		
	}
	
	@GetMapping("/staff/RegisterAttendence/{empid}/{date}")
	public List<Attendence> AttendenceChecking(@PathVariable String empid,@PathVariable LocalDate date) {
		System.out.println(empid);
		System.out.println(date);
		return staffservice.attendencechecking(empid,date);
		
	}
	
//	Task
	
	@GetMapping("/staff/ViewTask/{empid}")
	public List<TaskList> ViewTask(@PathVariable String empid) {
		return staffservice.viewtask(empid);
	}
	
	@PutMapping("/staff/UpdateTask")
	public TaskList updatetask(@RequestBody TaskList task) {
		return staffservice.updatetask(task);
	}
	
	@PostMapping("/staff/SendRemark")
	public String sendremark(@RequestBody TaskList task) {
		staffservice.sendremark(task);
		return "Remark Send";
	}
	
	@GetMapping("/ho")
	public void ho() {
		mail.gethoteldetailsbeforeday();
	}
	
//	Data Entry
//	Don't blame me for this ugly code, My front end dev asked to do this .
	@PostMapping("/staff/AddHotelDetails")
	public String addhoteldetails(@RequestBody CustomersHotelDetails hoteldetails) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			
				StringBuilder names=new StringBuilder();
		    for(int i= 0; i<hoteldetails.getPassengername().size();i++) {
			names.append(" <tr><td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\" ><strong>Additional Guest:</strong></td><td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\" >"+hoteldetails.getPassengername().get(i).getPassengername()+"</td></tr>\r\n");
		}
			String html = "<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "  <meta charset=\"UTF-8\">\r\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
					+ "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "  <title>Sivaji Son - Booking Confirmation</title>\r\n"
					+ "</head>\r\n"
					+ "<body style=\"text-align:center;margin:0;padding:10px 0;background-color:#f0f8ff;color:#333;font-family:Verdana,Geneva,sans-serif;\">\r\n"
					+ "  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;background-color:#ffffff;margin:auto;border-radius:10px;box-shadow:0 4px 8px rgba(0,0,0,0.1);overflow:hidden;\">\r\n"
					+ "    <tr>\r\n"
					+ "      <td style=\"background-color:#178079;padding:20px;color:#ffffff;font-size:24px;font-weight:bold;font-family:'Trebuchet MS',sans-serif;\">\r\n"
					+ "        <img src=\"https://sivajison.com/mainlogo/image.png\" width=\"150\" style=\"background-color:#178079; display:block;margin:0 auto 10px auto;border-radius:5%;\" alt=\"Logo\">\r\n"
					+ "        Your Upcoming Hotel Booking on "+hoteldetails.getCheckindate().format(formatter)+"\r\n"
					+ "      </td>\r\n"
					+ "    </tr>\r\n"
					+ "    <tr>\r\n"
					+ "      <td style=\"padding:20px;font-family:Tahoma,Geneva,sans-serif;\">\r\n"
					+ "        <h1 style=\"font-size:22px;color:#178079;margin:0 0 15px 0;\">Hello " +hoteldetails.getCustomername()+"</h1>\r\n"
					+ "        <p style=\"margin:0 0 15px 0;\">We hope you're excited about your upcoming booking! Here are your booking details:</p>\r\n"
					+ "        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#f9f9f9;border-radius:5px;padding:15px;margin-bottom:20px;\">\r\n"+
					// newly added line below famiy members
					  names.toString() 
					+ "          <tr>\r\n"
					+ "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>Booking ID:</strong></td>\r\n"
					+ "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">"+hoteldetails.getBookingid()+"</td>\r\n"
					+ "          </tr>\r\n"
					+ "          <tr>\r\n"
					+ "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>Hotel Name:</strong></td>\r\n"
					+ "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">"+hoteldetails.getHotelname()+"</td>\r\n"
					+ "          </tr>\r\n"
					+ "          <tr>\r\n"
					+ "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>No. of Nights:</strong></td>\r\n"
					+ "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">"+hoteldetails.getNoofnights()+"</td>\r\n"
					+ "          </tr>\r\n"
					+ "          <tr>\r\n"
					+ "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>Check‑in Date:</strong></td>\r\n"
					+ "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">"+hoteldetails.getCheckindate().format(formatter)+"</td>\r\n"
					+ "          </tr>\r\n"
					+ "          <tr>\r\n"
					+ "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>Check‑out Date:</strong></td>\r\n"
					+ "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">"+hoteldetails.getCheckoutdate().format(formatter)+"</td>\r\n"
					+ "          </tr>\r\n"
					+ "        </table>\r\n"
					+ "      </td>\r\n"
					+ "    </tr>\r\n"
					+ "    <tr>\r\n"
					+ "      <td style=\"background-color:#178079;color:white;padding:20px;font-size:16px;text-align:center;\">\r\n"
					+ "        <p style=\"margin:0 0 10px;\">\r\n"
					+ "         <a href=\"https://admin.sivajison.com/customer\" style=\"display:inline-block;background-color:#ff5722;color:#ffffff;padding:10px 20px;border-radius:5px;font-weight:bold;text-decoration:none\">Download Voucher</a><br>\r\n"
					+ "        </p>\r\n"
					+ "        <p style=\"margin:0 0 10px;\"><strong>SIVAJI SON TOURS AND TRAVELS PRIVATE LIMITED</strong></p>\r\n"
					+ "        <p style=\"margin:0 0 10px;font-size:14px;color:#ffeb3b;\">\r\n"
					+ "          Email: saranraj@sivajison.com<br>\r\n"
					+ "          VSD Plaza, AA Block, 2nd Avenue, Anna Nagar, Chennai-40, Tamilnadu\r\n"
					+ "        </p>\r\n"
					+ "        <p style=\"margin:0;font-size:14px;\">\r\n"
					+ "          Need help? Call us at <strong>9655150814, 044 – 46856688</strong> or visit\r\n"
					+ "          <a href=\"https://sivajison.com\" style=\"color:#ffeb3b;text-decoration:none;\">Our Website</a>.\r\n"
					+ "        </p>\r\n"
					+ "      </td>\r\n"
					+ "    </tr>\r\n"
					+ "  </table>\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "";
			
			
			
			sendmail.sendhtmlmail(hoteldetails.getCustomermail(), "Booking Details", html);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		
		return staffservice.savehoteldetails(hoteldetails);
	}

	@PostMapping("/staff/AddFlightDetails")
	public String addflightdetails(@RequestBody CustomerFlightDetails flightDetails) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		StringBuilder  table = new StringBuilder();
		flightDetails.getAirlines().forEach(b ->{
			
			String add =   "  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#f9f9f9;border-radius:5px;padding:15px;margin-bottom:20px;\">\n" 
					+ "          <tr><td  style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\" ><strong>Ticket No:</strong></td><td  style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\" > "+b.getTicketnumber()+"</td></tr>\r\n"
					+ "     \r\n"
					+ "          <tr><td  style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>Airline:</strong></td><td  style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">"+b.getAirline()+"</td></tr>\r\n"
					+ "\r\n"
					+ "          <tr><td  style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>Class:</strong></td><td   style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">"+b.getTravelclass()+"</td></tr>\r\n"
					+ "        \r\n"
					+ "          <tr><td><strong>Flight Details:</strong></td><td>"+b.getFlightnumber()+" | ["+b.getDepartureairport()+"] ["+b.getDeparturetime().format(formatter)+"] → ["+b.getArrivalairport()+"] ["+b.getArrivaltime().format(formatter)+"]</td></tr>\r\n"
					+ "        </table>\r\n";
			table.append(add);
			
			
		});
		StringBuilder names=new StringBuilder();
		for(int i= 0; i<flightDetails.getPassengername().size();i++) {
			names.append(" <tr><td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\" ><strong>Passengers Name:</strong></td><td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\" >"+flightDetails.getPassengername().get(i).getPassengername()+"</td></tr>\r\n");
		}
		
		String htmlcontent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <title>Sivaji Son - Travel Confirmation</title>\n" +
                "</head>\n" +
                "<body style=\"text-align:center;margin:0;padding:10px 0;background-color:#f0f8ff;color:#333;font-family:Verdana,Geneva,sans-serif;\">\n" +
                "  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;background-color:#ffffff;margin:auto;border-radius:10px;box-shadow:0 4px 8px rgba(0,0,0,0.1);overflow:hidden;\">\n" +
                "    <tr>\n" +
                "      <td style=\"background-color:#008179;padding:20px;color:#ffffff;font-size:24px;font-weight:bold;font-family:'Trebuchet MS',sans-serif;\">\n" +
                "        <img src=\"https://sivajison.com/mainlogo/image.png\" width=\"150\" style=\"background-color:#178079; display:block;margin:0 auto 10px auto;border-radius:5%;\" alt=\"Logo\">\n" +
                "        Your Upcoming Trip on " + flightDetails.getAirlines().get(0).getDeparturetime().toLocalDate()+ "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"padding:20px;font-family:Tahoma,Geneva,sans-serif;\">\n" +
                "        <h1 style=\"font-size:22px;color:#008179;margin:0 0 15px 0;\">Hello ! your details has been registered successfully "  + ",</h1>\n" +
                "        <p style=\"margin:0 0 15px 0;\">We hope you're excited about your upcoming journey! Here are your travel details:</p>\n" +
                "        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#f9f9f9;border-radius:5px;padding:15px;margin-bottom:15px;\">\n" +
                names.toString() +
                "          <tr>\n" +
                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>Customer ID:</strong></td>\n" +
                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">" + flightDetails.getCutomerid()+ "</td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>Issue Date:</strong></td>\n" +
                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">" + flightDetails.getIssuedate()+ "</td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>PNR:</strong></td>\n" +
                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">" + flightDetails.getPnr() + "</td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                table.toString() +
                "        <p style=\"margin-top:20px;\">Please arrive at the airport at least <strong>4 hours before</strong> departure to ensure a smooth check-in.</p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"background-color:#008179;color:white;padding:20px;font-size:16px;text-align:center;\">\n" +
                "        <p style=\"margin:0 0 10px;\">\r\n"+
			    "         <a href=\"https://admin.sivajison.com/customer\" style=\"display:inline-block;background-color:#ff5722;color:#ffffff;padding:10px 20px;border-radius:5px;font-weight:bold;text-decoration:none\">Download Ticket</a><br>\r\n"+
			    "        </p>\r\n" +
                "        <p style=\"margin:0;\"><strong>SIVAJI SON TOURS AND TRAVELS PRIVATE LIMITED</strong></p>\n" +
                "        <p style=\"margin:5px 0 10px;font-size:14px;color:#ffeb3b;\">\n" +
                "          Email: saranraj@sivajison.com<br>\n" +
                "          VSD Plaza, AA Block, 2nd Avenue, Anna Nagar, Chennai-40, Tamilnadu\n" +
                "        </p>\n" +
                "        <p style=\"margin:0;font-size:14px;\">Need help? Call us at <strong>9655150814, 044 - 46856688</strong> or visit <a href=\"https://sivajison.com\" style=\"color:#ffeb3b;text-decoration:none;\">our website</a>.</p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";
		
		String finalhtml = htmlcontent.toString();
		try {
			sendmail.sendhtmlmail(flightDetails.getCustomermail(), "Booking Details", finalhtml);
		} catch (MessagingException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return staffservice.saveflightdetails(flightDetails);
//		System.out.println(flightDetails.getCustomername());
//		System.out.println(flightDetails.getAirlines().get(0).getArivaltime());
//		return "";
	}
	
	@PostMapping("/staff/AddOtherDetails")
	public String addotherdetails(@RequestBody Customerotherdetails otherdetails) {
		return staffservice.saveotherdetails(otherdetails);
	}
	
//	Daily report
	
	@PostMapping("/staff/SendDailyReport")
	public String addflightdetails(@RequestBody dailyreport report) {
		return staffservice.senddailyreport(report);
	}
	
//	Getting Airport Code
	
	@GetMapping("/staff/GetAirportCode")
	public List<Airportcodes> getairportcode(){
		return staffservice.getairportcode();
	}
	
//	get airlineswithlogo
	@GetMapping("/staff/GetAirline")
	public List<airlinesWithLogo> getairline(){
		return staffservice.getairline();
	}
	
	@GetMapping("/staff/GetAirlineLogo/{name}")
	public List<airlinesWithLogo> getairlinelogo(@PathVariable String name){
		return staffservice.getairlinelogo(name);
	}
	
}
