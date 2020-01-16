package com.stationbelleville.StationBelleville.Web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stationbelleville.StationBelleville.Domain.Activity;
import com.stationbelleville.StationBelleville.Exceptions.ResourceNotFoundException;
import com.stationbelleville.StationBelleville.Services.ActivityService;

//contains mappings for CRUD operations
@RestController
@RequestMapping("/api/station")
@CrossOrigin
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@PostMapping("")
	public ResponseEntity<?> createNewActivity(@Valid @RequestBody Activity activity, BindingResult result) {

		Activity activityNew = activityService.saveOrUpdateActivity(activity);
		return new ResponseEntity<Activity>(activityNew, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public Iterable<Activity> getAllProjects() {
		return activityService.findAllProjects();
	}
	
    @PutMapping("/activities/{id}")
    public Activity updateActivity(@PathVariable Long id, @Valid @RequestBody Activity activityRequest) {
        
        return activityService.findById(id).map(activity ->{
            
            activity.setActivityName(activityRequest.getActivityName());
            activity.setActivityType(activityRequest.getActivityType());
            activity.setActivityMaxCapacity(activityRequest.getActivityMaxCapacity());
            activity.setActivityCurrentCapacity(activityRequest.getActivityCurrentCapacity());
            activity.setStart_date(activityRequest.getStart_date());
            activity.setEnd_date(activityRequest.getEnd_date());
            
            return activityService.saveOrUpdateActivity(activity);
        }).orElseThrow(() -> new ResourceNotFoundException("ActivityId " + id + " not found"));
    }
    
    @PostMapping("/activities")
    public ResponseEntity<?> InsertActivity(@Valid @RequestBody Activity activity, BindingResult result) {

        Activity activityNew = activityService.saveOrUpdateActivity(activity);
        long newID = activityNew.getId();
        String newActivityID = "IND"+newID;
        System.out.println(newActivityID);
        System.out.println("-----------------------");
        System.out.println(newID);
        activityNew.setActivityIdentifier(newActivityID);
        activityService.saveOrUpdateActivity(activityNew);
        
        return new ResponseEntity<Activity>(activityNew, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/activities/{id}")
	public ResponseEntity<?> deleteActivity(@PathVariable Long id) {
		
		return activityService.findById(id).map(activity ->{
			activityService.delete(activity);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("ActivityId" + id + " not found."));
	}
}
