package com.gpsChildTracker;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class Map extends MapActivity {
	LinearLayout linearLayout;
	MapView mapView;
	Kid jimmy;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapView = (MapView) findViewById(R.id.mapview);

        
        jimmy = new Kid();
        updateMap();

        
        Button findChildBtn = (Button) findViewById(R.id.findChildBtn);
        findChildBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(getApplicationContext(), "Your child is here!", Toast.LENGTH_SHORT).show();
                mapView.getController().animateTo(jimmy.getPoint());
                mapView.getController().setZoom(6);
            }        
        });  //end onClickListener

        Button newTripBtn = (Button) findViewById(R.id.newTripBtn);
        newTripBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(getApplicationContext(), "Tap to set destination", Toast.LENGTH_LONG).show();
                mapView.setOnTouchListener( new OnTouchListener () {
                	public boolean onTouch(View v, MotionEvent event) {
                        if (true /*event.getAction() == 1*/) {                
                            GeoPoint p = mapView.getProjection().fromPixels(
                                (int) event.getX(),
                                (int) event.getY());
                                Toast.makeText(getBaseContext(), 
                                    p.getLatitudeE6() / 1E6 + "," + 
                                    p.getLongitudeE6() /1E6 , 
                                    Toast.LENGTH_LONG).show();

                                mapView.invalidate();
                        }                            
                		return false;
                	}
                });//end ontouch
            } //end onclick        
        });  //end onClickListener
        
        
    }
    
    public void updateMap() {
    	//get jimmy's position from the web, update him and his marker with his new position
    	GeoPoint p = new GeoPoint(12000000, 3000000);
    	jimmy.updatePosition(p);
        mapView.getOverlays().add(jimmy.getOverlay());
        mapView.invalidate();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }


}

