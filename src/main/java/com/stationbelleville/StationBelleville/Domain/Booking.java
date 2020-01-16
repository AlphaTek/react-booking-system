package com.stationbelleville.StationBelleville.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Booking Object
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer bookingSequence = 0;

	private String activityIdentifier;

	private String bookingIdentifier;
	
	@NotBlank(message = "Parent's / Guardian's First Name is Required")
	@Size(min = 2, max = 25)
	private String parentFirstName;

	@NotBlank(message = "Parent's / Guardian's Last Name is Required")
	@Size(min = 2, max = 25)
	private String parentLastName;

	@NotBlank(message = "Attendee's First Name is Required")
	@Size(min = 2, max = 25)
	private String attendeeFirstName;

	@NotBlank(message = "Attendee's Last Name is Required")
	@Size(min = 2, max = 25)
	private String attendeeLastName;

	@NotBlank(message = "Email Address is Required")
	private String emailAddress;
	@NotBlank(message = "You must enter the age of the attendee")
	private String age;
	@NotBlank(message = "Phone Number is Required")
	private String phoneNumber;

	@JsonFormat(pattern = "yyyy-MMM-dd hh:mm:ss aa", locale = "en_US")
	private Date bookingDate;

	private String additionalInfo;

	private Boolean wasWaiverSigned;

	private String booker;

	@JsonFormat(pattern = "yyyy-MMM-dd hh:mm:ss aa", locale = "en_US")
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "activity_id", updatable = false)
	@JsonIgnore
	private Activity activity;

	
	// Adding of children will be implemented in future updates
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "booking")
//	private List<Children> childList = new ArrayList<>();

}
