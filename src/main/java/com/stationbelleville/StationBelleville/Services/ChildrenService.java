/***
 * This service class has been commented out for a future update. 
 */

//package com.stationbelleville.StationBelleville.Services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.stationbelleville.StationBelleville.Domain.Booking;
//import com.stationbelleville.StationBelleville.Domain.Children;
//import com.stationbelleville.StationBelleville.Repositories.BookingInterface;
//import com.stationbelleville.StationBelleville.Repositories.ChildrenRepository;
//
//@Service
//public class ChildrenService {
//	
//	@Autowired
//	private ChildrenRepository childrenRepository;
//	
//	@Autowired
//	private BookingInterface bookingInterface;
//	
//	
//	public Children saveOrUpdateChildren(Children child) {
//		
//		return childrenRepository.save(child);
//	}
//	
//	public Children addChildrenToBooking(Long id, Children child) {
//		
//		
//		Booking booking = bookingInterface.findBookingById(id);
//		child.setBooking(booking);
//		
//	//	Integer BookingSequence = booking.getBookingSequence();
//	//	BookingSequence++;
//		
//		//add sequence to children
//		//child.setBkSequence(booking.getBookingIdentifier() + "-"+BookingSequence);
//		//child.setBookingIdentifier(booking.getBookingIdentifier());
//		
//		return childrenRepository.save(child);
//	}
//	
//	
//
//	
//
//}
