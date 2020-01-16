package com.stationbelleville.StationBelleville.Web;


import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stationbelleville.StationBelleville.Domain.Booking;
import com.stationbelleville.StationBelleville.Services.BookingService;
import com.stationbelleville.StationBelleville.Services.EmailService;

@RestController
@RequestMapping("/api/emails")

public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BookingService bookingServ;
	
	/*@GetMapping("/sendemail")
	public String sendBookingEmail(HttpServletRequest request) throws AddressException, MessagingException, IOException {
		
		String bid = request.getParameter("id");
		
		Booking b = bookingServ.findBookingById(Long.parseLong(bid));
		
		//Activity a = b.getActivity();
		
		String to = b.getEmailAddress();
		String body = "";
		String html = "";
		String subject = "Station Belleville Booking";
		String attachment = "";
		String mimeType = "text/calendar";
		String fileName ="booking.ics";
		//a.getName();
		
		
		emailService.sendEmail(to, subject, body, html, attachment, mimeType, fileName);
		
		String response="index";
		
		return response;
	}*/
}
