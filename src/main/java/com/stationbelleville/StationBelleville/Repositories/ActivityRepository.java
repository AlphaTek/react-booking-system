package com.stationbelleville.StationBelleville.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stationbelleville.StationBelleville.Domain.Activity;
import com.stationbelleville.StationBelleville.Domain.Booking;

//Interface for CRUD operations
@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

	@Override
	Iterable<Activity> findAll();

	
	public Activity findActivityById(Long activityId);
	
	public Activity findByActivityIdentifier(String aIdentifier);


	@Query("SELECT id FROM Activity WHERE activityIdentifier = :aId")
	long findIdByIdentifier(@Param("aId") String aId);
	
	

}


