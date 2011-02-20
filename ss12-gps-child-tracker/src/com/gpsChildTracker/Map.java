package com.gpsChildTracker;

import android.content.Intent;
import android.net.Uri;
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
	Button newTripBtn;
	Button streetViewBtn;
	Button findChildBtn;
	
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

        
        findChildBtn = (Button) findViewById(R.id.findChildBtn);
        streetViewBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
            	Intent intent = new Intent(Intent.ACTION_VIEW);
            	String uri = "google.streetview:cbll=lat,lng&cbp=1,yaw,,pitch,zoom&mz=mapZoom";
            	intent.setData(Uri.parse(uri));
            	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	startActivity(intent);
            }        
        });  //end onClickListener
        streetViewBtn= (Button) findViewById(R.id.streetViewBtn);
        findChildBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(getApplicationContext(), "Your child is here!", Toast.LENGTH_SHORT).show();
                mapView.getController().animateTo(jimmy.getPoint());
                mapView.getController().setZoom(6);
            }        
        });  //end onClickListener

        newTripBtn = (Button) findViewById(R.id.newTripBtn);
        newTripBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(getApplicationContext(), "Tap to set destination", Toast.LENGTH_SHORT).show();
                mapView.setOnTouchListener( new OnTouchListener () {
                	public boolean onTouch(View v, MotionEvent event) {
                        if (true /*event.getAction() == 1*/) {                
                            GeoPoint p = mapView.getProjection().fromPixels(
                                (int) event.getX(),
                                (int) event.getY());
                                Toast.makeText(getBaseContext(), 
                                    p.getLatitudeE6() / 1E6 + "," + 
                                    p.getLongitudeE6() /1E6 , 
                                    (Toast.LENGTH_LONG + Toast.LENGTH_SHORT) / 2).show();

                                mapView.invalidate();
                                jimmy.setTrip(jimmy.point, p);
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

