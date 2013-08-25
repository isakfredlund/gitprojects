package se.mah.k3.skanetrafiken.model;

import java.util.Calendar;

public class Journey {
	private Calendar depDateTime, arrDateTime;
	private String noOfChanges;
	private String lineOnFirstJourney;
	private String travelMinutes;
	private String timeToDeparture;
	private String noOfZones;
	private String lineTypeName;
	private String depTimeDeviation;
	private String arrTimeDeviation;
	private Station startStation;
	private Station endStation;
	
	public Journey(Station startStation, Station endStation) {
		this.startStation = startStation;
		this.endStation = endStation;
		// TODO Auto-generated constructor stub
	}
	public Calendar getDepDateTime() {
		return depDateTime;
	}
	public void setDepDateTime(Calendar depDateTime) {
		this.depDateTime = depDateTime;
	}
	public Calendar getArrDateTime() {
		return arrDateTime;
	}
	public void setArrDateTime(Calendar arrTime) {
		this.arrDateTime = arrTime;
	}
	public String getNoOfChanges() {
		return noOfChanges;
	}
	public void setNoOfChanges(String number) {
		this.noOfChanges = number;
	}
	
	public Station getStartStation(){
		return startStation;
	}
	
	public Station getEndStation(){
		return endStation;
	}
	
	public String getLineOnFirstJourney() {
		return lineOnFirstJourney;
	}
	
	public void setLineOnFirstJourney(String lineOnFirstJourney) {
		this.lineOnFirstJourney = lineOnFirstJourney;
	}
	
	public void setTravelTime(String travelMinutes) {
		this.travelMinutes = travelMinutes;	
	}
	
	public String getTravelMinutes(){
		return this.travelMinutes;
	}
	public String getTimeToDeparture() {
		return timeToDeparture;
	}
	public void setTimeToDeparture(String timeToDeparture) {
		this.timeToDeparture = timeToDeparture;
	}
	public String getNoOfZones() {
		return noOfZones;
	}
	public void setNoOfZones(String noOfZones) {
		this.noOfZones = noOfZones;
	}
	public String getLineTypeName() {
		return lineTypeName;
	}
	public void setLineTypeName(String lineTypeName) {
		this.lineTypeName = lineTypeName;
	}
	public String getDepTimeDeviation() {
		return depTimeDeviation;
	}
	public void setDepTimeDeviation(String depTimeDeviation) {
		this.depTimeDeviation = depTimeDeviation;
	}
	public String getArrTimeDeviation() {
		return arrTimeDeviation;
	}
	public void setArrTimeDeviation(String arrTimeDeviation) {
		this.arrTimeDeviation = arrTimeDeviation;
	}
	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}
	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}
}
