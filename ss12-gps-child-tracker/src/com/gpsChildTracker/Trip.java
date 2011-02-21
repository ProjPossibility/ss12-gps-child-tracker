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
		startOverlay.setRed(0);
		startOverlay.setBlue(255);
		endOverlay = new KidLocationOverlay(ePoint);
		endOverlay.setRed(0);
		endOverlay.setBlue(255);
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
