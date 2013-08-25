package se.mah.k3.skanetrafiken.view;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class Helpers{
    //Takes  a two dateTime Strings in format 2012-10-15T08:17:00 and calculates difference between them in minutes
    public static String getTravelTimeinMinutes(String startTime, String endTime){
    	int diffMinutes= 0;
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    	Date startDate = null, endDate = null;
		try {
			startDate = dateFormat.parse(startTime);
			endDate = dateFormat.parse(endTime);
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(startDate);
			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(endDate);
			long millisTravel = calEnd.getTimeInMillis() - calStart.getTimeInMillis();
			diffMinutes = ((int)millisTravel/(60*1000));
		} catch (ParseException e) {
			e.printStackTrace();
		}	
    	return String.valueOf(diffMinutes);
    }
    
    public static String timeToDeparture(String startTime){
    	int diffMinutes=-1;
    	Calendar now = Calendar.getInstance();
    	now.setTime(new Date());
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    	Date date =null;
		try {
			date = dateFormat.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		long millisDiff = cal.getTimeInMillis() - now.getTimeInMillis();
		if (millisDiff>0){
			diffMinutes = ((int)millisDiff/(60*1000));
		}
    	return String.valueOf(diffMinutes);
    }
	
  //Takes a date String in format 2012-10-15T08:17:00 and converts it to a calendar object
    public static Calendar parseCalendarString(String dateTimeString){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    	Date date =null;
		try {
			date = dateFormat.parse(dateTimeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
		cal.setTime(date);
		return cal;
    }
    
}
