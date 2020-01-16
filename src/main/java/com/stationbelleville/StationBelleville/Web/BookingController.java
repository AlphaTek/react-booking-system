package com.stationbelleville.StationBelleville.Web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stationbelleville.StationBelleville.Domain.Activity;
import com.stationbelleville.StationBelleville.Domain.Booking;
import com.stationbelleville.StationBelleville.Domain.User;
import com.stationbelleville.StationBelleville.Services.ActivityService;
import com.stationbelleville.StationBelleville.Services.BookingService;
import com.stationbelleville.StationBelleville.Services.CalendarService;
import com.stationbelleville.StationBelleville.Services.EmailService;
import com.stationbelleville.StationBelleville.Services.ErrorValidationService;

//contains mappings for CRUD operations
@RestController
@RequestMapping("/api/station/booking")
@CrossOrigin
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private CalendarService calendarService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ErrorValidationService errorValidationService;

	@PostMapping("")
	public ResponseEntity<?> createNewBooking(@Valid @RequestBody Booking booking,
			BindingResult result, @AuthenticationPrincipal User user) throws Exception {

		ResponseEntity<?> errorMap = errorValidationService.ErrorValidationService(result);
		if (errorMap != null)
			return errorMap;
		System.out.println("PRINC NAMEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE : " + user.getEmail());
		Booking newBooking = bookingService.saveOrUpdateBooking(booking, user.getEmail());

		Activity a = activityService.findByIdentifier(booking.getActivityIdentifier());

		int currCap = a.getActivityCurrentCapacity();

		currCap++;

		a.setActivityCurrentCapacity(currCap);
		activityService.saveOrUpdateActivity(a);

		calendarService.generateICSFile(booking, a);
		emailService.sendConfirmEmail(booking);

		return new ResponseEntity<Booking>(newBooking, HttpStatus.CREATED);
	}

	@GetMapping("/allBookings")
	public Iterable<Booking> getAllBookings(@AuthenticationPrincipal User user) {
		return bookingService.findAllBookings(user.getEmail());
	}

//	@GetMapping("/{bookingId}")
//
//	public ResponseEntity<?> getBookingById(@PathVariable Long bookingId) {
//
//		Booking booking = bookingService.findBookingById(bookingId);
//
//		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
//
//	}
	
	@GetMapping("/bookingsByProgram/{aID}")
	public Iterable<Booking> getBookingsByProgram(@PathVariable String aID) {
		return bookingService.findAllBookingsByActivityIdentifier(aID);
	}
	
	@GetMapping("/adminAllBookings")
	public Iterable<Booking> getAllBookings() {
		return bookingService.findAllBookings();
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<?> deleteBooking(@PathVariable Long bookingId) throws Exception {
		Booking booking = bookingService.findBookingById(bookingId);

		Activity a = activityService.findByIdentifier(booking.getActivityIdentifier());
		System.out.println("ACTIVITYYYYYYYYYYYYYYYYYYYYYYY " + a);
		int decreaseCurrentCap = a.getActivityCurrentCapacity();

		decreaseCurrentCap--;

		a.setActivityCurrentCapacity(decreaseCurrentCap);

		activityService.saveOrUpdateActivity(a);

		bookingService.deleteBookingById(bookingId);

		return new ResponseEntity<String>("Booking with ID: '" + bookingId + "' was deleted", HttpStatus.OK);
	}

//	@PostMapping("/{booking_id}")
//	public ResponseEntity<?> addChildtoBooking(@Valid @RequestBody Children child, BindingResult result,
//			@PathVariable Long booking_id) {
//
//		Children child1 = childrenService.addChildrenToBooking(booking_id, child);
//
//		return new ResponseEntity<Children>(child1, HttpStatus.CREATED);
//	}

	@GetMapping("/mostRecentBooking")
	public ResponseEntity<?> getMostRecentBooking() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");
		Booking sendBooking = new Booking();
		Date book = new Date();
		String convertDate = "";
		Iterable<Booking> bList = bookingService.findAllBookings();

		for (Booking bookingCheck : bList) {
			convertDate = sf.format(bookingCheck.getCreatedDate());
			try {
				book = sf.parse(convertDate);
				sendBooking = bookingService.findBookingById(bookingCheck.getId());
				System.out.println("DATEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE " + book);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new ResponseEntity<Booking>(sendBooking, HttpStatus.OK);
	}

}
