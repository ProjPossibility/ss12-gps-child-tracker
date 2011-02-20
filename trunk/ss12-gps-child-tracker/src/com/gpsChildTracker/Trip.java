package com.gpsChildTracker;

import com.google.android.maps.GeoPoint;

public class Trip {

	GeoPoint startPoint;
	GeoPoint endPoint;
	
	public Trip(GeoPoint sPoint, GeoPoint ePoint){
		startPoint = sPoint;
		endPoint = ePoint;
	}
	
	public GeoPoint getStartPoint(){
		return startPoint;
	}
	
	public GeoPoint getEndPoint(){
		return endPoint;
	}
	
}
