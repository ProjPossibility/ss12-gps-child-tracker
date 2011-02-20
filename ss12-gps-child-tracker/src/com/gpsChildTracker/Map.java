package com.gpsChildTracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
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
	CountDownTimer updateCounter;
	String test;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapView = (MapView) findViewById(R.id.mapview);
/*
        ItemizedOverlay itemizedOverlay = new ItemizedOverlay(android.graphics.drawable.Drawable, defaultMarker);
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
        HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable);
  */      
        
        jimmy = new Kid();
        jimmy.setPoint(new GeoPoint(34022002, -118291963));
        updateMap();
        
        new CountDownTimer(30000, 6000) {

            public void onTick(long millisUntilFinished) {
                //Toast.makeText(getApplicationContext(), "tick", Toast.LENGTH_SHORT);
                test = Long.toString(millisUntilFinished);
                updateMap();
            	//mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
            	//Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT);
            	//mTextField.setText("done!");
            }
         }.start();


        
        streetViewBtn = (Button) findViewById(R.id.streetViewBtn);
        streetViewBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
            	Intent intent = new Intent(Intent.ACTION_VIEW);
            	String uri = "google.streetview:cbll=" + (double)((double) jimmy.getPoint().getLatitudeE6() / 1E6) + "," + (double)((double) jimmy.getPoint().getLongitudeE6() / 1E6) + "&cbp=11,42.04,,0,-6.66";  //"google.streetview:cbll=40.758437,-73.985164&cbp=11,42.04,,0,-6.66";
            			//"cbll=34022411,-118283983&cbp=1,1,,0,1.0&mz=mapZoom";
            	intent.setData(Uri.parse(uri));
            	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	startActivity(intent);
            }        
        });  //end onClickListener
        findChildBtn= (Button) findViewById(R.id.findChildBtn);
        findChildBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(getApplicationContext(), "Your child is here!" /* + test*/, Toast.LENGTH_SHORT).show();
                mapView.getController().animateTo(jimmy.getPoint());
                mapView.getController().setZoom(18);
               showDialog(0);
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
                                Trip trip = new Trip(jimmy.point, p);
                                jimmy.setTrip(trip);
                                mapView.getOverlays().add(trip.getStartOverlay());
                                mapView.getOverlays().add(trip.getEndOverlay());
                        }                            
                		return false;
                	}
                });//end ontouch
            } //end onclick        
        });  //end onClickListener
        
        
               
      //2000 is the starting number (in milliseconds)
      //2000 is the number to count down each time (in milliseconds) CountDownTimer? updateCounter = new CountDownTimer?(20000, 2000){
      /*
      updateCounter = new CountDownTimer(20000,2000){  
      @Override 
      public void onFinish() {
      //do nothing
      }
      
      @Override
      public void onTick(long millisUntilFinished) {
      updateMap(); Toast.makeText(getApplicationContext(), "update", Toast.LENGTH_LONG);
      }};
      updateCounter.start();
        */
    }
    
    public void updateMap() {
    	//get jimmy's position from the web, update him and his marker with his new position
    	
    	int jimmyLat = jimmy.getPoint().getLatitudeE6();
    	int jimmyLng = jimmy.getPoint().getLongitudeE6();
    	jimmyLat += 123;
    	jimmyLng += 123;
    	
    	//GeoPoint p = new GeoPoint((jimmyLat+1234), (jimmyLng+1234));
    	GeoPoint p = new GeoPoint(jimmyLat, jimmyLng);
    	
    	mapView.getOverlays().remove(jimmy.getOverlay());
    	
    	jimmy.updatePosition(p);
        mapView.getOverlays().add(jimmy.getOverlay());
        mapView.invalidate();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        switch(id) {
        case 0:
        	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
             builder.setMessage("You Found Your Child!")
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                             dialog.cancel();
                        }
                    });
             AlertDialog alert = builder.create();
             dialog = alert;
             break;
        default:
            dialog = null;
        }
        return dialog;
    }
}

