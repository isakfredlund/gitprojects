package se.mah.k3.skanetrafiken.xmlparser;

import java.util.ArrayList;
import java.util.Calendar;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.util.Log;

import se.mah.k3.skanetrafiken.model.Journey;
import se.mah.k3.skanetrafiken.model.Station;
import se.mah.k3.skanetrafiken.view.Constants;
import se.mah.k3.skanetrafiken.view.Helpers;

public class Parser {

	public static ArrayList<Journey> getJourneys(String searchURL){
    	ArrayList<Journey> journeys = new ArrayList<Journey>();
        XMLParser parser = new XMLParser();
        Station toStation;
        Station fromStation;
        String line;
        Calendar depTime;
        Calendar arrTime;
		String xml = parser.getXmlFromUrl(searchURL); // getting XML
		if (xml!=null){
			Document doc = parser.getDomElement(xml); // getting DOM element
			//Get departure and arrival time
			NodeList nl = doc.getElementsByTagName("Journey");
			// looping through all item nodes <item>
			for (int i = 0; i < nl.getLength(); i++) {
				Element e = (Element) nl.item(i);			
				//Get the first station
				NodeList fromNode = e.getElementsByTagName("From");
				String fromStationName ="";
				String fromStationID ="";
				for (int j =0;j < fromNode.getLength();j++){
					Element e2 = (Element) fromNode.item(j);
					if (j==0){
						fromStationName = parser.getValue(e2, "Name");
						fromStationID =parser.getValue(e2, "Id");
					}else{
						//Here is the place to get the intermediate stops
					}
				}
				fromStation = new Station(fromStationName, fromStationID);	
				//Get the last station on the journey
				NodeList toNode = e.getElementsByTagName("To");
				String toStationName ="";
				String toStationID ="";
				for (int j =0;j < toNode.getLength();j++){
					Element e2 = (Element) toNode.item(j);
					if (j==(toNode.getLength()-1)){	//get the last stop
						toStationName = parser.getValue(e2, "Name");  
						toStationID = parser.getValue(e2, "Id");
					}else{
						//Here is the place to get the intermediate stops
					}
				}
				toStation = new Station(toStationName, toStationID);
				//Get the line for the first journey
				NodeList lineNode = e.getElementsByTagName("Line");
				line = "";
				for (int j =0;j < lineNode.getLength();j++){
					Element e2 = (Element) lineNode.item(j);
					if (j==0){
						line = parser.getValue(e2, "Name");
					}else{
						//Here is the place to get Line for other legs in the journey
					}	
				}
				
				String depTimeString = parser.getValue(e, "DepDateTime");
			    String arrTimeString = parser.getValue(e, "ArrDateTime");
			    String travelMinutes = Helpers.getTravelTimeinMinutes(depTimeString, arrTimeString);
			    depTime = Helpers.parseCalendarString(depTimeString);
			    arrTime = Helpers.parseCalendarString(arrTimeString);
			    Journey thisJourney = new Journey(fromStation,toStation);
			    thisJourney.setLineOnFirstJourney(line);
			    thisJourney.setDepDateTime(depTime);
			    thisJourney.setArrDateTime(arrTime);
			    thisJourney.setNoOfChanges(parser.getValue(e, "NoOfChanges"));
			    thisJourney.setTravelTime(travelMinutes);
			    thisJourney.setTimeToDeparture(Helpers.timeToDeparture(depTimeString));
			    thisJourney.setNoOfZones(parser.getValue(e, "NoOfZones"));
			    thisJourney.setLineTypeName(parser.getValue(e, "TransportModeName"));
			    thisJourney.setArrTimeDeviation( parser.getValue(e, "DepTimeDeviation"));
			    thisJourney.setDepTimeDeviation( parser.getValue(e, "ArrTimeDeviation"));
			    journeys.add(thisJourney);
			}
		}
    	return journeys;
    }
}
