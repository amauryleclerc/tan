package org.maurange.formation.licpro;

import java.util.concurrent.ExecutionException;

import org.maurange.formation.licpro.rest.ListTempsArret;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class ListTempsArretsActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	       Intent intent = getIntent();
	       String codeLieu =	intent.getStringExtra("codeLieu");
	       String nomLieu =	intent.getStringExtra("nomLieu");
		this.setContentView(R.layout.tempsarret_layout);
		ListView lv = (ListView) this.findViewById(R.id.listTempsArrets);
		TextView tv = (TextView) this.findViewById(R.id.titreTempsArret);
		tv.setText(nomLieu);
		
		
		ListTempsArret lesTempsArrets = new ListTempsArret();
		
		GetTempsArretsTask task = new GetTempsArretsTask(this);
		task.execute( codeLieu);
		try {
			lesTempsArrets =	task.get();
			task.cancel(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	TempsArretAdapter tempsArretAdapter = new TempsArretAdapter(this, lesTempsArrets);
	lv.setAdapter(tempsArretAdapter);
	}
	

}
