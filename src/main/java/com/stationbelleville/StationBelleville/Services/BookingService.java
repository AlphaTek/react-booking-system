package com.stationbelleville.StationBelleville.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationbelleville.StationBelleville.Domain.Activity;
import com.stationbelleville.StationBelleville.Domain.Booking;
import com.stationbelleville.StationBelleville.Domain.User;
import com.stationbelleville.StationBelleville.Repositories.ActivityRepository;
import com.stationbelleville.StationBelleville.Repositories.BookingInterface;
import com.stationbelleville.StationBelleville.Repositories.UserRepositoryInterface;

//like the DAO, contains most logic for retrieving info
@Service
public class BookingService {

	@Autowired
	private BookingInterface bookingInterface;

	@Autowired
	private UserRepositoryInterface userRepo;

	@Autowired
	private ActivityRepository activityRepo;

	public Booking saveOrUpdateBooking(Booking booking, String username) {

		User user = userRepo.findByEmail(username);

		//Activity activity = activityRepo.findByActivityIdentifier(aIdentifier);
		

		booking.setUser(user);
		booking.setBooker(user.getEmail());
		//booking.setActivity(activity);
		
		//Integer BookingSequence = booking.getBookingSequence();
		//BookingSequence++;
		
		//booking.setBookingIdentifier(aIdentifier + "-" + BookingSequence);
		//booking.setActivityIdentifier(aIdentifier);
		

		return bookingInterface.save(booking);
	}

	public Iterable<Booking> findAllBookings(String username) {
		return bookingInterface.findAllByBooker(username);
	}
	
	public Iterable<Booking> findAllBookingsByActivityIdentifier(String aId){
		return bookingInterface.findAllBookingsByActivityIdentifier(aId.toUpperCase());
	}

	public Booking findBookingById(Long bookingId) {
		Booking booking = bookingInterface.findBookingById(bookingId);

		return booking;
	}
	

	public void deleteBookingById(Long bookingId) {
		Booking booking = bookingInterface.findBookingById(bookingId);

		bookingInterface.delete(booking);
	}

	public Booking addBookingToActivity(Long id, Booking booking) {

		Activity activity = activityRepo.findActivityById(id);
		booking.setActivity(activity);

		return bookingInterface.save(booking);

	}

	public Iterable<Booking> findAllBookings() {
		return bookingInterface.findAll();
	}


//	public Booking addBookingWithIdentifier(String id, Booking booking) {
//		
//
//		
//		Booking booking = bookingInterface.
//		
//	}


}
