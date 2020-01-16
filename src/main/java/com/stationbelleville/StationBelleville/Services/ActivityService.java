package com.stationbelleville.StationBelleville.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationbelleville.StationBelleville.Domain.Activity;
import com.stationbelleville.StationBelleville.Repositories.ActivityRepository;

//like the DAO, contains most logic for retrieving info
@Service
public class ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	public Activity saveOrUpdateActivity(Activity activity) {

		return activityRepository.save(activity);
	}

	public Iterable<Activity> findAllProjects() {
		return activityRepository.findAll();
	}
	

	public Activity findActivityByIdentifier(String aIdentifier) {
		
		return activityRepository.findByActivityIdentifier(aIdentifier.toUpperCase());
	}
	

	public Activity findByIdentifier(String aId) throws Exception {
		long id = activityRepository.findIdByIdentifier(aId);
		Optional<Activity> oa =  activityRepository.findById(id);
		Activity a = oa.orElseThrow(() -> new Exception("Activity not found."));
		return a;
	}

	    public Optional<Activity> findById(Long id) {
	        Optional<Activity> activity = activityRepository.findById(id);

	        return activity;
	    }

	    public void deleteById(Long id) {
	    	activityRepository.deleteById(id);
	    }

	    public void delete(Activity activity) {
	    	activityRepository.delete(activity);
	    }

	


}
