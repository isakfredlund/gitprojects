package se.mah.k3.skanetrafiken.view;

import java.util.Calendar;
import java.util.List;

import se.mah.k3.skanetrafiken.R;
import se.mah.k3.skanetrafiken.model.Journey;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainAdapter extends ArrayAdapter<Journey> {
	  private final Context context;
	  private final List<Journey> journeys;
	  
	public MainAdapter(Context context, List<Journey> journeys) {
		super(context, R.layout.listrow, journeys);
		this.context = context;
		this.journeys = journeys;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.listrow, parent,false);
		//Get all views to use
		TextView depStation = (TextView)rowView.findViewById(R.id.depstation);
		TextView arrStation = (TextView)rowView.findViewById(R.id.arrstation);
		TextView depTime = (TextView)rowView.findViewById(R.id.deptime);
		TextView carrier = (TextView)rowView.findViewById(R.id.carrier);
		//Get the journey and assign correct values
		Journey journey = journeys.get(position);
		depStation.setText(journey.getStartStation().getStationName());
		arrStation.setText(journey.getEndStation().getStationName());
		Calendar depCalObject = journey.getDepDateTime();
		int hour=0;
		int min = 0;
		try {
			hour = depCalObject.get(Calendar.HOUR_OF_DAY);
			min = depCalObject.get(Calendar.MINUTE);
		} catch (Exception e) {}
        depTime.setText(""+hour + ":"+ min);
		carrier.setText(journey.getLineOnFirstJourney());
		return rowView;
	}
}
