package org.maurange.formation.licpro;

import org.maurange.formation.licpro.rest.ArretRestMethod;
import org.maurange.formation.licpro.rest.ListArret;

import android.content.Context;
import android.os.AsyncTask;

public class GetArretsTask  extends AsyncTask<Object, Void, ListArret>  {
	
	private Context context;
	
	public GetArretsTask(Context context){
		this.context = context;
	}

	@Override
	protected ListArret doInBackground(Object... params) {
		
			double lat = (Double) params[0];
		double lon = (Double) params[1];
		 ArretRestMethod arretRestMethod = new ArretRestMethod(context);
		 ListArret lesArrets =   arretRestMethod.getArretsRest(lat, lon);
		return lesArrets;
	}




}
