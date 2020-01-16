/***
 * This class is commented out for a future update. 
 */

//package com.stationbelleville.StationBelleville.Domain;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//
//public class Children {
//	
//	 @Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)	
//private Long id;
//
//private String bookingIdentifier;
//private String bkSequence;
//private String childFirstName;
//private String childLastName;
//private String childAge;
//
//@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
//@JoinColumn(name="booking_id")
//@JsonIgnore
//private Booking booking;
//
//}
