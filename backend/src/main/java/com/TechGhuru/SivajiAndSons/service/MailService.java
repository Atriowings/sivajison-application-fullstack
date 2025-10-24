package com.TechGhuru.SivajiAndSons.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.TechGhuru.SivajiAndSons.model.CustomerFlightDetails;
import com.TechGhuru.SivajiAndSons.model.CustomersHotelDetails;
import com.TechGhuru.SivajiAndSons.repository.CustomerFlightRepo;
import com.TechGhuru.SivajiAndSons.repository.CustomerHotelDetailsRepo;

import jakarta.mail.MessagingException;

@Service
public class MailService {

	@Autowired
	CustomerHotelDetailsRepo hotelrepo;
	@Autowired
	SendMail sendmail;
	@Autowired
	CustomerFlightRepo flightrepo;
	
	
	
	@Scheduled(cron = "0 0 10 * * ?")
	public void gethoteldetailsbeforeday() {
		
	List<CustomersHotelDetails> hoteldetails =hotelrepo.gethoteldetailsbeforeday();
	if(!hoteldetails.isEmpty()){
		
		hoteldetails.forEach(a -> 
			{
				try {
					
					String htmlcontent = "<!DOCTYPE html>\r\n"
							+ "<html lang=\"en\">\r\n"
							+ "<head>\r\n"
							+ "  <meta charset=\"UTF-8\">\r\n"
							+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
							+ "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
							+ "  <title>Sivaji Son - Travel Confirmation</title>\r\n"
							+ "  <style>\r\n"
							+ "    body {\r\n"
							+ "      text-align: center;\r\n"
							+ "      margin: 0;\r\n"
							+ "      padding: 10px 0;\r\n"
							+ "      background-color: #f0f8ff;\r\n"
							+ "      color: #333;\r\n"
							+ "      font-family: \"Verdana, Geneva, sans-serif\";\r\n"
							+ "    }\r\n"
							+ "    .container {\r\n"
							+ "      width: 600px;\r\n"
							+ "      max-width: 600px;\r\n"
							+ "      background-color: #ffffff;\r\n"
							+ "      margin: auto;\r\n"
							+ "      border-radius: 10px;\r\n"
							+ "      box-shadow: 0 4px 8px rgba(0,0,0,0.1);\r\n"
							+ "      overflow: hidden;\r\n"
							+ "    }\r\n"
							+ "    .header {\r\n"
							+ "      background-color: #71cac4;\r\n"
							+ "      padding: 20px;\r\n"
							+ "      color: #ffffff;\r\n"
							+ "      font-size: 24px;\r\n"
							+ "      font-weight: bold;\r\n"
							+ "      font-family: \"Trebuchet MS, sans-serif\";\r\n"
							+ "    }\r\n"
							+ "    .content {\r\n"
							+ "      padding: 20px;\r\n"
							+ "      font-family: \"Tahoma, Geneva, sans-serif\";\r\n"
							+ "    }\r\n"
							+ "    .content h1 {\r\n"
							+ "      font-size: 22px;\r\n"
							+ "      color: #71cac4;\r\n"
							+ "    }\r\n"
							+ "    .table-container {\r\n"
							+ "      background-color: #f9f9f9;\r\n"
							+ "      border-radius: 5px;\r\n"
							+ "      padding: 15px;\r\n"
							+ "      margin-top: 15px;\r\n"
							+ "    }\r\n"
							+ "    .table-container table {\r\n"
							+ "      width: 100%;\r\n"
							+ "      border-collapse: collapse;\r\n"
							+ "    }\r\n"
							+ "    .table-container td {\r\n"
							+ "      padding: 10px;\r\n"
							+ "      font-size: 14px;\r\n"
							+ "      border-bottom: 1px solid #ddd;\r\n"
							+ "    }\r\n"
							+ "    .table-container tr:last-child td {\r\n"
							+ "      border-bottom: none;\r\n"
							+ "    }\r\n"
							+ "    .footer {\r\n"
							+ "      background-color: #71cac4;\r\n"
							+ "      color: white;\r\n"
							+ "      padding: 20px;\r\n"
							+ "      font-size: 16px;\r\n"
							+ "    }\r\n"
							+ "    .footer a {\r\n"
							+ "      color: #ffeb3b;\r\n"
							+ "      text-decoration: none;\r\n"
							+ "    }\r\n"
							+ "    .button {\r\n"
							+ "      display: inline-block;\r\n"
							+ "      background-color: #ff5722;\r\n"
							+ "      color: white;\r\n"
							+ "      padding: 10px 20px;\r\n"
							+ "      text-decoration: none;\r\n"
							+ "      border-radius: 5px;\r\n"
							+ "      font-weight: bold;\r\n"
							+ "      margin-bottom: 10px;\r\n"
							+ "    }\r\n"
							+ "  </style>\r\n"
							+ "  \r\n"
							+ "</head>\r\n"
							+ "<body>\r\n"
							+ "  <div class=\"container\">\r\n"
							+ "    <div class=\"header\">\r\n"
							+ "      <img src=\"https://67ee547409f2e1c1ec411380--ubiquitous-mandazi-9072f1.netlify.app/image.png\" width=\"150px\"><br>\r\n"
							+ "      Your Upcoming Hotel Booking on "+a.getCheckindate()+"\r\n"
							+ "    </div>\r\n"
							+ "    <div class=\"content\">\r\n"
							+ "      <h1>Hello "+a.getCustomername()+",</h1>\r\n"
							+ "      <p>We hope you're excited about your upcoming Booking! Here are your Booking details:</p>\r\n"
							+ "      <div class=\"table-container\">\r\n"
							+ "        <table>\r\n"
							+ "          <tr><td><strong>Booking ID:</strong></td><td>"+a.getBookingid()+"</td></tr>\r\n"
							+ "          <tr><td><strong>Hotel Name:</strong></td><td>"+a.getHotelname()+"</td></tr>\r\n"
							+ "          <tr><td><strong>No. of Nights: </strong></td><td>"+a.getNoofnights()+"</td></tr>\r\n"
							+ "          <tr><td><strong>Check-in Date:</strong></td><td>"+a.getCheckindate()+"</td></tr>\r\n"
							+ "\r\n"
							+ "          <tr><td><strong>Check-out Date:</strong></td><td>"+a.getCheckoutdate()+"</td></tr>\r\n"
							+ "         \r\n"
							+ "         \r\n"
							+ "     \r\n"
							+ "       \r\n"
							+ "        </table>\r\n"
							+ "      </div>\r\n"
							+ " \r\n"
							+ "    \r\n"
							+ "    </div>\r\n"
							+ "    <div class=\"footer\">\r\n"
							+ "      <p><span class=\"button\">Regards,</span> <br><strong>SARAN</strong></p>\r\n"
							+ "      <p> <strong>SIVAJI SON TOURS AND TRAVELS PRIVATE LIMITED</strong></p>\r\n"
							+ "      <p style=\"font-size: 14px;\">Email: saranraj@sivajison.com <br>\r\n"
							+ "       \r\n"
							+ "        VSD Plaza, AA Block, No 65/1, 2nd Avenue, Anna Nagar, Chennai, Tamilnadu, India - 600040</p> \r\n"
							+ "      <p>Need help? Call us at <strong>9655150814, 044 - 46856688 </strong> or visit <a href=\"https://admin.sivajison.com/customer\">our portal</a>.</p>\r\n"
							+ "    </div>\r\n"
							+ "  </div>\r\n"
							+ "</body>\r\n"
							+ "</html>";
					String subject = "REMINDER";
					
					sendmail.sendhtmlmail(a.getCustomermail(), subject, htmlcontent);
					System.out.println("mail send successfully");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		
	}
	}
	
	
	
	@Scheduled(cron = "0 0 11 * * ?")
	public void getflightdetailsbeforeday() {
		
	List<CustomerFlightDetails> flightdetails =flightrepo.getflightdetailsbeforeday();
	if(!flightdetails.isEmpty()){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		flightdetails.forEach(a -> 
			{
				StringBuilder names= new StringBuilder();
				StringBuilder  table = new StringBuilder();
				a.getAirlines().forEach(b ->{
					
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
//				String names ="";
				for(int i= 0; i<a.getPassengername().size();i++) {
					names.append(" <tr><td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\" ><strong>Passengers Name:</strong></td><td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\" >"+a.getPassengername().get(i).getPassengername()+"</td></tr>\r\n");
				}
				try {
					System.out.println(table);
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
				                "        <img src=\"https://67ee547409f2e1c1ec411380--ubiquitous-mandazi-9072f1.netlify.app/image.png\" width=\"150\" style=\"background-color:white; display:block;margin:0 auto 10px auto;border-radius:5%;\" alt=\"Logo\">\n" +
				                "        Your Upcoming Trip on " + a.getTraveldate() + "\n" +
				                "      </td>\n" +
				                "    </tr>\n" +
				                "    <tr>\n" +
				                "      <td style=\"padding:20px;font-family:Tahoma,Geneva,sans-serif;\">\n" +
				                "        <h1 style=\"font-size:22px;color:#008179;margin:0 0 15px 0;\">Hello " + a.getPassengername().get(0).getPassengername() + ",</h1>\n" +
				                "        <p style=\"margin:0 0 15px 0;\">We hope you're excited about your upcoming journey! Here are your travel details:</p>\n" +
				                "        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#f9f9f9;border-radius:5px;padding:15px;margin-bottom:15px;\">\n" +
				                names.toString() +
				                "          <tr>\n" +
				                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>Customer ID:</strong></td>\n" +
				                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">" + a.getCutomerid() + "</td>\n" +
				                "          </tr>\n" +
				                "          <tr>\n" +
				                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>Issue Date:</strong></td>\n" +
				                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">" + a.getIssuedate() + "</td>\n" +
				                "          </tr>\n" +
				                "          <tr>\n" +
				                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\"><strong>PNR:</strong></td>\n" +
				                "            <td style=\"padding:10px;font-size:14px;border-bottom:1px solid #ddd;\">" + a.getPnr() + "</td>\n" +
				                "          </tr>\n" +
				                "        </table>\n" +
				                table.toString() +
				                "        <p style=\"margin-top:20px;\">Please arrive at the airport at least <strong>4 hours before</strong> departure to ensure a smooth check-in.</p>\n" +
				                "      </td>\n" +
				                "    </tr>\n" +
				                "    <tr>\n" +
				                "      <td style=\"background-color:#008179;color:white;padding:20px;font-size:16px;text-align:center;\">\n" +
				                "        <p style=\"margin:0 0 10px;\"><span style=\"display:inline-block;background-color:#ff5722;color:#ffffff;padding:10px 20px;border-radius:5px;font-weight:bold;\">Regards,</span><br><strong>SARAN</strong></p>\n" +
				                "        <p style=\"margin:0;\"><strong>SIVAJI SON TOURS AND TRAVELS PRIVATE LIMITED</strong></p>\n" +
				                "        <p style=\"margin:5px 0 10px;font-size:14px;\">\n" +
				                "          Email: saranraj@sivajison.com<br>\n" +
				                "          VSD Plaza, AA Block, No 65/1, 2nd Avenue, Anna Nagar, Chennai, Tamilnadu, India - 600040\n" +
				                "        </p>\n" +
				                "        <p style=\"margin:0;\">Need help? Call us at <strong>9655150814, 044 - 46856688</strong> or visit <a href=\"https://admin.sivajison.com/customer\" style=\"color:#ffeb3b;text-decoration:none;\">our portal</a>.</p>\n" +
				                "      </td>\n" +
				                "    </tr>\n" +
				                "  </table>\n" +
				                "</body>\n" +
				                "</html>";
							
//							+ a.getAirlines().forEach(b -> b.getFlightnumber())
//							+ "        <table>\r\n"
//							+ "          <tr><td><strong>Ticket No:</strong></td><td> [Ticket Number]</td></tr>\r\n"
//							+ "     \r\n"
//							+ "          <tr><td><strong>Airline:</strong></td><td>[Airline]</td></tr>\r\n"
//							+ "\r\n"
//							+ "          <tr><td><strong>Class:</strong></td><td>[Class]</td></tr>\r\n"
//							+ "        \r\n"
//							+ "          <tr><td><strong>Flight Details:</strong></td><td>[Flight No.] | [Departure City] [Departure Time] → [Arrival City] [Arrival Time]</td></tr>\r\n"
//							+ "        </table>\r\n"
//							+ "      </div>\r\n"
//							+ "      <p style=\"margin-top: 20px;\">Please arrive at the airport at least <strong>4 hours before</strong> departure to ensure a smooth check-in.</p>\r\n"
//							+ "    </div>\r\n"
//							+ "    <div class=\"footer\">\r\n"
//							+ "      <p><span class=\"button\">Regards,</span> <br><strong>STAFF NAME</strong></p>\r\n"
//							+ "      <p> <strong>SIVAJI SON TOURS AND TRAVELS PRIVATE LIMITED</strong></p>\r\n"
//							+ "      <p style=\"font-size: 14px;\">Email: saranraj@sivajison.com <br>\r\n"
//							+ "       \r\n"
//							+ "        VSD Plaza, AA Block, No 65/1, 2nd Avenue, Anna Nagar, Chennai, Tamilnadu, India - 600040</p> \r\n"
//							+ "      <p>Need help? Call us at <strong>9655150814, 044 - 46856688 </strong> or visit <a href=\"[WEBSITE LINK - CUSTOMER PORTAL]\">our portal</a>.</p>\r\n"
//			 				+ "    </div>\r\n"
//			 				+ "  </div>\r\n"
//			 				+ "</body>\r\n"
//			 				+ "</html>";
					
		 			String subject = "REMINDER";
			 		String finalhtml = htmlcontent.toString();
 					sendmail.sendhtmlmail(a.getCustomermail(), subject, finalhtml);
				 	System.out.println("mail send successfully") ;
		 		} catch (MessagingException e) {
// 				    	 TODO Auto-generated catch block
 					e.printStackTrace();
	 			}
		 	});
		
		
	}
	
	}
	
	
}
