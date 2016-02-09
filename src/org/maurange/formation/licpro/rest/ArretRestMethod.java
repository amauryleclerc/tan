package org.maurange.formation.licpro.rest;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.maurange.formation.licpro.R;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;




import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class ArretRestMethod {
		private static String LOG_TAG="ArretRestMethod";
		Context mContext=null;
		
		public ArretRestMethod(Context context) {
			mContext = context.getApplicationContext();
		}
		
		
		public  ListArret getArretsRest( double latitude, double longitude ){
					
			// The URL for making the GET request
			final String url =  mContext.getString(R.string.arret_rest_url);

			Log.d(LOG_TAG, "Invoke url " + url + " with params : " + latitude + ", " + longitude);
			
			// Set the Accept header for "application/json"
			HttpHeaders requestHeaders = new HttpHeaders();
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(acceptableMediaTypes);
			requestHeaders.setAcceptLanguage("fr_FR");
			
			// Populate the headers in an HttpEntity object to use for the request
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			
			// Perform the HTTP GET request
			ListArret arrets = new ListArret();
			try {
				Log.i("test", "test");
				ResponseEntity<ListArret> responseEntity = restTemplate.exchange(
						url, HttpMethod.GET, requestEntity, ListArret.class, "json", latitude, longitude );

				Log.d(LOG_TAG, "nb arrets: " + responseEntity.getBody().size());
				arrets.addAll(responseEntity.getBody());
			} catch (RestClientException e) {
				Log.e(LOG_TAG,"RestException dans le chargement des donnees serveur ARRET",e);
			
				
			}
			
			return arrets;
		}
		public  ListTempsArret getTempsArretsRest( String  codeLieu ){
			
			// The URL for making the GET request
			final String url =  mContext.getString(R.string.temps_rest_url);

			Log.d(LOG_TAG, "Invoke url " + url + " with params : " + codeLieu);
			
			// Set the Accept header for "application/json"
			HttpHeaders requestHeaders = new HttpHeaders();
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(acceptableMediaTypes);
			requestHeaders.setAcceptLanguage("fr_FR");
			
			// Populate the headers in an HttpEntity object to use for the request
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			
			// Perform the HTTP GET request
			ListTempsArret tempsArrets = new ListTempsArret();
			try {
				Log.i("test", "test: " + url);
				Log.i("test", "test: " + codeLieu);
				ResponseEntity<ListTempsArret> responseEntity = restTemplate.exchange(
						url, HttpMethod.GET, requestEntity, ListTempsArret.class, codeLieu );

				Log.d(LOG_TAG, "nb tempsarret: " + responseEntity.getBody().size());
				tempsArrets.addAll(responseEntity.getBody());
			} catch (RestClientException e) {
				Log.e(LOG_TAG,"RestException dans le chargement des donnees serveur TEMPSARRET",e);
			}
			
			return tempsArrets;
		}
	
}
