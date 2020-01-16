package com.stationbelleville.StationBelleville;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.stationbelleville.StationBelleville.Services.CalendarService;
import com.stationbelleville.StationBelleville.Services.EmailService;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.ValidationException;

@SpringBootApplication
public class StationBellevilleApplication {

	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) throws AddressException, MessagingException, IOException, ValidationException, ParserException {
		SpringApplication.run(StationBellevilleApplication.class, args);
		
	}

}
