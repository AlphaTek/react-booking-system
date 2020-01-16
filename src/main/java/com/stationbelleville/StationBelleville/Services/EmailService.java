package com.stationbelleville.StationBelleville.Services;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.stationbelleville.StationBelleville.Domain.Booking;

@Service
public class EmailService
{
	private static String PATHOFDIR = System.getProperty("user.dir");
	private static String attachment =  PATHOFDIR + "\\booking.ics";
	
	public void sendConfirmEmail(Booking booking)  throws AddressException, MessagingException, IOException{
		String to = booking.getEmailAddress();
		String subject = "Station Belleville Booking";
		String body = "Hi "+ booking.getAttendeeFirstName() + "Thank you for booking with station belleville!";
		String html = "<p> Thank you for booking with station belleville! </p>";
	    if (to == null) return;
	    
	    
	    Session session = Session.getDefaultInstance(new Properties());
	    MimeMessage message = new MimeMessage(session);
	    try {
	        message.setSubject(subject, "UTF-8");
	        message.setFrom(new InternetAddress("Station Belleville <stationbellevillecapstone@gmail.com>"));
	        message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to)); 
	        
	            MimeMultipart msg = new MimeMultipart("mixed");
	                MimeBodyPart wrap = new MimeBodyPart();
	                    MimeMultipart msgBody = new MimeMultipart("alternative");
	                        MimeBodyPart textPart = new MimeBodyPart();
	                        MimeBodyPart htmlPart = new MimeBodyPart();
	                        textPart.setContent(body, "text/plain; charset=UTF-8");
	                        htmlPart.setContent(html,"text/html; charset=UTF-8");
	                    msgBody.addBodyPart(textPart);
	                    msgBody.addBodyPart(htmlPart);
	                wrap.setContent(msgBody);
	            msg.addBodyPart(wrap);
	                MimeBodyPart att = new MimeBodyPart();
	                att.setDataHandler(new DataHandler(attachment, "text/calendar"));
	                att.setFileName("booking.ics");
                  DataSource fds = new FileDataSource(attachment);
                  att.setDataHandler(new DataHandler(fds));
                  att.setFileName(fds.getName());
                  
	            msg.addBodyPart(att);
	        message.setContent(msg);

	        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
	                .standard().withRegion(Regions.US_EAST_1).build();
	        message.writeTo(System.out);
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        message.writeTo(outputStream);
	        RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
	        SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
//	                      .withConfigurationSetName(CONFIGURATION_SET);
	        client.sendRawEmail(rawEmailRequest);

	}catch(Exception ex) {
		 System.out.println("Email Failed");
         System.err.println("Error message: " + ex.getMessage());
         ex.printStackTrace();
		}
	}
}
