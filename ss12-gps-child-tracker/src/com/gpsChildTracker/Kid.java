package com.gpsChildTracker;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

public class Kid {
	
	//Variables
	GeoPoint point;  //Stores the kid's current latitude and longitude
	KidLocationOverlay overlay;         //Stores the overlay to display for this kid
	Trip trip;    //Stores the kid's trip start and end points
	ArrayList<KidLocationOverlay> locationHistory;
	
	//default constructor
	public Kid() {
		locationHistory = new ArrayList<KidLocationOverlay>();
		//nothing happens
	}
	
	//Functions
	public void updatePosition(GeoPoint p){
		point = p;
		overlay = new KidLocationOverlay(p);
	}
	
	public GeoPoint getPoint(){
		return point;
	}
	
	public void setPoint(GeoPoint p){
		point = p;
	}
	
	public KidLocationOverlay getOverlay() {
		return overlay;
	}
	
	public void setOverlay(KidLocationOverlay o) {
		overlay = o;
	}

	public void setTrip(Trip tripIn){
		trip = tripIn;
	}
	
	public void addLocationHistoryPoint(GeoPoint p){
		locationHistory.add(new KidLocationOverlay(p));
	}
	
	public ArrayList<KidLocationOverlay> getLocationHistory(){
		return locationHistory;
	}
	
}
