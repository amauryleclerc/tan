package org.maurange.formation.licpro;

import java.util.List;

import org.maurange.formation.licpro.rest.Arret;
import org.maurange.formation.licpro.rest.NumLigne;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ArretAdapter extends BaseAdapter {

	private static String LOG_TAG="ArretAdapter";
	private Context mContext;
	private List<Arret> mListArret;
	private LayoutInflater inflater;

	public ArretAdapter(Context p_oContext, List<Arret> p_oListArret){
		super();
		mContext = p_oContext;
		mListArret = p_oListArret;
		this.inflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return mListArret.size();
	}

	@Override
	public Arret getItem(int position) {
		return mListArret.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int p_iPosition, View p_oConvertView, ViewGroup p_oParentView) {
		
		Log.e(LOG_TAG, "getView() - position: " + p_iPosition);
		
		if (p_oConvertView == null) {
			p_oConvertView = inflater.inflate(R.layout.arret_item, null);
		}
		final Arret mArret=this.getItem(p_iPosition);

//		Log.d(LOG_TAG, "getView() - mArret: " + mArret);
//		Log.d(LOG_TAG, "getView() - mArret: " + mArret.getLibelle());
//		Log.d(LOG_TAG, "getView() - mArret: " + mArret.getDistance());
		
//		Log.d(LOG_TAG, "getView() - uiArretDistance: " + uiArretDistance);

		

		TextView tvLibelle = (TextView) p_oConvertView.findViewById(R.id.libelle);
		tvLibelle.setText(mArret.getLibelle());
		TextView tvDistance = (TextView) p_oConvertView.findViewById(R.id.distance);
		tvDistance.setText(mArret.getDistance());
		 LinearLayout lyLignes = ( LinearLayout ) p_oConvertView.findViewById(R.id.imglayout );  
		for(NumLigne ligne : mArret.getLigne()){
		
			String imgName = "l_"+ligne.getNumLigne();
			imgName = imgName.toLowerCase();
			int resID =  mContext.getResources().getIdentifier( imgName , "drawable", mContext.getPackageName());
			DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
			float dp = 40f;
			float fpixels = metrics.density * dp;
			int pixels = (int) (fpixels + 0.5f);

			if(resID !=0){
			
				ImageView imgLignes = new ImageView( mContext );
				imgLignes.setImageResource(resID);
				imgLignes.setLayoutParams(new LayoutParams(pixels, pixels));
				lyLignes.addView(imgLignes);
	
			}
		}

		return p_oConvertView;
	}

	public List<Arret> getListArret() {
		return mListArret;
	}
}
