package com.stationbelleville.StationBelleville.Services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stationbelleville.StationBelleville.Domain.Activity;
import com.stationbelleville.StationBelleville.Domain.Booking;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;

@Service
public class CalendarService {

	
	
	public void generateICSFile(Booking booking, Activity a) throws IOException, ValidationException, ParserException, URISyntaxException {
		String calFile = "booking.ics";
		
		String name = booking.getAttendeeFirstName();
	

		// Creating a new calendar
		net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
		calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);
	

		VEvent bookingEv = new VEvent();
		bookingEv.getProperties().add(new Summary(a.getActivityName()));
		bookingEv.getProperties().add(new Description("Hello " + name + "! Thank you for booking with Station Belleville."));
		bookingEv.getProperties().add(new Location("Station Belleville"));
		bookingEv.getProperties().add(new Organizer("mailto:stationbellevillecapstone@gmail.com"));
		final DtStart dtStart = new DtStart(new net.fortuna.ical4j.model.DateTime(a.getStart_date()));
		final DtEnd dtEnd = new DtEnd(new net.fortuna.ical4j.model.DateTime(a.getEnd_date()));
		bookingEv.getProperties().add(dtStart);
		bookingEv.getProperties().add(dtEnd);

		calendar.getComponents().add(bookingEv);

		// Saving an iCalendar file
		FileOutputStream fout = new FileOutputStream(calFile);

		CalendarOutputter outputter = new CalendarOutputter();
		outputter.setValidating(false);
		outputter.output(calendar, fout);

		// Now Parsing an iCalendar file
		FileInputStream fin = new FileInputStream(calFile);

		CalendarBuilder builder = new CalendarBuilder();

		calendar = builder.build(fin);

		// Iterating over a Calendar
		for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
			Component component = (Component) i.next();
			System.out.println("Component [" + component.getName() + "]");

			for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
				Property property = (Property) j.next();
				System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
			}
		} // for
		
	}
}
