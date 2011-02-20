package com.gpsChildTracker;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class GPSChildTracker extends MapActivity {
	
	LinearLayout linearLayout;
	MapView mapView;
	GeoPoint kidGeoPoint;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        kidGeoPoint = new GeoPoint(12000000, 3000000);
        mapView = (MapView) findViewById(R.id.mapview);
        
        Button findChildBtn = (Button) findViewById(R.id.findChildBtn);
        findChildBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(getApplicationContext(), "Pressed!", Toast.LENGTH_SHORT).show();
                mapView.getController().animateTo(kidGeoPoint);
                mapView.getController().setZoom(6);
            }
        });
        //Overlay kidLocation = new Overlay();        
        //mapView.getOverlays().add(kidLocation);

    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}