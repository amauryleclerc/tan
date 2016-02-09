package org.maurange.formation.licpro;

import org.maurange.formation.licpro.rest.ArretRestMethod;
import org.maurange.formation.licpro.rest.ListTempsArret;

import android.content.Context;
import android.os.AsyncTask;

public class GetTempsArretsTask  extends AsyncTask<Object, Void, ListTempsArret>  {


	private Context context;
	
	public GetTempsArretsTask(Context context){
		this.context = context;
	}
	@Override
	protected ListTempsArret doInBackground(Object... params) {

		String codeLieu = (String) params[0];
		 ArretRestMethod arretRestMethod = new ArretRestMethod(context);
		 ListTempsArret lesTempsArrets =   arretRestMethod.getTempsArretsRest(codeLieu);

		return lesTempsArrets;
	}




}
