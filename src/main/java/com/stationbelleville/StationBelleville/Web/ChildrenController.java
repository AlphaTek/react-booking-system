
/***
 * This controller has been commented out for a future update. 
 */

//package com.stationbelleville.StationBelleville.Web;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.stationbelleville.StationBelleville.Domain.Booking;
//import com.stationbelleville.StationBelleville.Domain.Children;
//import com.stationbelleville.StationBelleville.Domain.User;
//import com.stationbelleville.StationBelleville.Services.BookingService;
//import com.stationbelleville.StationBelleville.Services.ChildrenService;
//import com.stationbelleville.StationBelleville.Services.ErrorValidationService;
//
////contains mappings for CRUD operations
//@RestController
//@RequestMapping("/api/station/child")
//@CrossOrigin
//public class ChildrenController {
//	
//	@Autowired
//	private BookingService bookingService;
//	
//	@Autowired 
//	private ChildrenService childrenService;
//	
//	@Autowired
//	private ErrorValidationService errorValidationService;
//	
//	
//	@PostMapping("")
//	public ResponseEntity<?> createNewChildren(@Valid @RequestBody Children child, BindingResult result) throws Exception {
//
//		ResponseEntity<?> errorMap = errorValidationService.ErrorValidationService(result);
//		if (errorMap != null)
//			return errorMap;
//		
//		Children newChildren = childrenService.saveOrUpdateChildren(child);
//		
//
//		return new ResponseEntity<Children>(newChildren, HttpStatus.CREATED);
//	}
//	
//	
//	
//	@PostMapping("/{booking_id}")
//    public ResponseEntity<?> addChildtoBooking(@Valid @RequestBody Children child,
//                                            BindingResult result, @PathVariable Long booking_id){
//       
//
//        Children child1 = childrenService.addChildrenToBooking(booking_id, child);
//
//        return new ResponseEntity<Children>(child1, HttpStatus.CREATED);
//
//    }
//
//}
