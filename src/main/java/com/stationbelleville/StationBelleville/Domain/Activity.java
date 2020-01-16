package com.stationbelleville.StationBelleville.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Activity Object
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String activityName;
	// @Column(unique = true)
	private String activityIdentifier;
	private String activityType;
	private String activityDuration;
	private int activityMaxCapacity;
	private int activityCurrentCapacity;

	@JsonFormat(pattern = "yyyy-MMM-dd", locale = "en_US")
	private Date start_date;
	@JsonFormat(pattern = "yyyy-MMM-dd", locale = "en_US")

	private Date end_date;

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Booking> bookingList = new ArrayList<>();
}
