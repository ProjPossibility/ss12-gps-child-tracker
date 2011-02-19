package com.gpsChildTracker;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;


public class GPSChildTracker extends MapActivity {
	
	LinearLayout linearLayout;
	MapView mapView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        //((TextView) findViewById(R.id.coolid)).setText("cool");
        
        GeoPoint GP = new GeoPoint(12000000, 3000000);
        MapView MV = (MapView) findViewById(R.id.mapview);
        
        MV.getController().animateTo(GP);
        MV.getController().setZoom(6);
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}