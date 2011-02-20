package com.gpsChildTracker;

import com.google.android.maps.GeoPoint;

public class Kid {
	
	//Variables
	GeoPoint KidPoint = new GeoPoint(0,0);  //Stores the kid's current location
	kidLocationOverlay KidLocation;         //Stores the overlay to display for this kid  ...?
	
	//Functions
	public GeoPoint getKidPoint(){
		return KidPoint;
	}
	
	public void setKidPoint(GeoPoint GP){
		KidPoint = GP;
	}

	
	
}
