package org.maurange.formation.licpro;

import java.util.List;
import java.util.Locale;

import org.maurange.formation.licpro.rest.TempsArret;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TempsArretAdapter extends BaseAdapter {

	private static String LOG_TAG="ArretAdapter";
	private Context mContext;
	private List<TempsArret> mListTempsArret;
	private LayoutInflater inflater;

	public TempsArretAdapter(Context p_oContext, List<TempsArret> p_oListTempsArret){
		super();
		mContext = p_oContext;
		mListTempsArret = p_oListTempsArret;
		this.inflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return mListTempsArret.size();
	}

	@Override
	public TempsArret getItem(int position) {
		return mListTempsArret.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int p_iPosition, View p_oConvertView, ViewGroup p_oParentView) {
		
//		Log.d(LOG_TAG, "getView() - position: " + p_iPosition);
		
		if (p_oConvertView == null) {
			p_oConvertView = inflater.inflate(R.layout.tempsarret_item, null);
		}
		final TempsArret mTempsArret=this.getItem(p_iPosition);

//		Log.d(LOG_TAG, "getView() - mArret: " + mArret);
//		Log.d(LOG_TAG, "getView() - mArret: " + mArret.getLibelle());
//		Log.d(LOG_TAG, "getView() - mArret: " + mArret.getDistance());
		
//		Log.d(LOG_TAG, "getView() - uiArretDistance: " + uiArretDistance);

		

		TextView tvLibelle = (TextView) p_oConvertView.findViewById(R.id.direction);
		tvLibelle.setText("vers "+mTempsArret.getTerminus());
		TextView tvDistance = (TextView) p_oConvertView.findViewById(R.id.temps);
		tvDistance.setText(mTempsArret.getTemps());
		ImageView ivligne = (ImageView) p_oConvertView.findViewById(R.id.imgligne);
		String imgName = "l_"+mTempsArret.getLigne().getNumLigne();
		imgName = imgName.toLowerCase();
		
		int resID =  mContext.getResources().getIdentifier( imgName , "drawable", mContext.getPackageName());
		ivligne.setImageResource( resID);
		
		return p_oConvertView;
	}

	public List<TempsArret> getListTempsArret() {
		return mListTempsArret;
	}
}
