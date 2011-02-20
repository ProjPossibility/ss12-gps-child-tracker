package com.gpsChildTracker;

import com.google.android.maps.GeoPoint;

public class Trip {

	GeoPoint startPoint;
	GeoPoint endPoint;
	KidLocationOverlay startOverlay;
	KidLocationOverlay endOverlay;
	
	public Trip(GeoPoint sPoint, GeoPoint ePoint){
		startPoint = sPoint;
		endPoint = ePoint;
		startOverlay = new KidLocationOverlay(sPoint);
		endOverlay = new KidLocationOverlay(ePoint);
	}
	
	public GeoPoint getStartPoint(){
		return startPoint;
	}
	
	public GeoPoint getEndPoint(){
		return endPoint;
	}
	
	public KidLocationOverlay getStartOverlay(){
		return startOverlay;
	}
	
	public KidLocationOverlay getEndOverlay(){
		return endOverlay;
	}
	
}
