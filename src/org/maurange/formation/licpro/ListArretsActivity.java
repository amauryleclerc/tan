package org.maurange.formation.licpro;

import java.util.concurrent.ExecutionException;

import org.maurange.formation.licpro.rest.Arret;
import org.maurange.formation.licpro.rest.ListArret;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListArretsActivity extends Activity implements LocationListener, OnItemClickListener {
	private static String LOG_TAG = "ListArretsActivity";
	private GetArretsTask task;
	private ListArret lesArrets ;
	private LocationManager lm;
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.arret_layout);
		lv = (ListView) this.findViewById(R.id.listArrets);
		lesArrets = new ListArret();
		lv.setOnItemClickListener(this);
		///this.refresh( 47.2135322, -1.5568826);
		 lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
	
	}
	

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(LOG_TAG, "enregistrement des listeners");
		if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, this);
		}
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10,this);
	


	}

	// arret des updates
	@Override
	protected void onPause() {
		super.onPause();
		Log.d(LOG_TAG, "desenregitrement des listeners");
		lm.removeUpdates(this);
	}


	@Override
	public void onLocationChanged(Location location) {

		this.refresh( location.getLatitude() , location.getLongitude());
	}
	


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	private void refresh(double latitude, double longitude){
		Log.d(LOG_TAG, "rafaichisement des données");
		task = new GetArretsTask(this);
		
		task.execute( latitude , longitude);
	
	try {
		lesArrets = task.get();
		task.cancel(true);
	
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block

		e.printStackTrace();
	
	}
	ArretAdapter arretAdapter = new ArretAdapter(this, lesArrets);
	Log.d(LOG_TAG, "adaptateur");
	lv.setAdapter(arretAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arretAdapter, View v, int position, long arg3) {
		
		Arret arret = (Arret) arretAdapter.getItemAtPosition(position);
		Intent intent = new Intent(this, ListTempsArretsActivity.class);
		intent.putExtra("codeLieu", arret.getCodeLieu());
		intent.putExtra("nomLieu", arret.getLibelle());
		this.startActivity(intent);
		
	}
	



}
