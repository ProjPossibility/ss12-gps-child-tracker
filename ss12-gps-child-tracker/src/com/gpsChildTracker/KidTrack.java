package com.gpsChildTracker;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;

public class KidTrack extends Activity{
	
	GeoPoint position;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kidtrack);
          
    }

}
