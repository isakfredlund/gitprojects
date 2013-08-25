package se.mah.k3.skanetrafiken.view;

import java.util.ArrayList;

import se.mah.k3.skanetrafiken.R;
import se.mah.k3.skanetrafiken.model.Journey;
import se.mah.k3.skanetrafiken.xmlparser.Parser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainList extends ListActivity {
	private ProgressBar progressBar1;
	private ImageView refresh;
	private MainAdapter adapter;
	ArrayList<Journey> journeys;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlist);
        journeys = new ArrayList<Journey>();
        adapter = new MainAdapter(this, journeys);
        adapter.setNotifyOnChange(true);
        setListAdapter(adapter);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        refresh = (ImageView)findViewById(R.id.refreshIcon);
      
    	View refresh = findViewById(R.id.refreshIcon);
    	refresh.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String searchURL = Constants.getURL("80000","93070",20);
				new DoInBackground().execute(searchURL);
			}
		});
    	
    	String searchURL = Constants.getURL("80000","93070",10); //Malmö C = 80000,  Malmö GAtorg 80100, Hässleholm C 93070
    	new DoInBackground().execute(searchURL);
    }

    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Journey j = (Journey)l.getItemAtPosition(position);
		Toast.makeText(this, "Restid: "+ j.getTravelMinutes(), Toast.LENGTH_SHORT).show();
	}
    
    //Inner class for async task
    private class DoInBackground extends AsyncTask<String,Integer,Long>{
		@Override
		protected Long doInBackground(String... params) {
			publishProgress(1);
			journeys.clear();  //Clear last search
			journeys.addAll(Parser.getJourneys(params[0])); //Add all stations found
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			progressBar1.setVisibility(View.VISIBLE);
			refresh.setVisibility(View.GONE);
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onPostExecute(Long result) {
			progressBar1.setVisibility(View.GONE);
			refresh.setVisibility(View.VISIBLE);
			super.onPostExecute(result);
		}
    }
    



}
