package com.stationbelleville.StationBelleville.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.stationbelleville.StationBelleville.Domain.Booking;

//Interface for CRUD operations
@Repository
public interface BookingInterface extends CrudRepository<Booking, Long> {

	@Override
	Iterable<Booking> findAll();

	public Booking findBookingById(Long bookingId);

	public void deleteBookingById(Long bookingId);

	Iterable<Booking> findAllByBooker(String username);
	
	Iterable<Booking> findAllBookingsByActivityIdentifier(String aId);

}
