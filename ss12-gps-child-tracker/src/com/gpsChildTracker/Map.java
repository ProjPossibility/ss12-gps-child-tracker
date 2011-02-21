package com.gpsChildTracker;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
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
import com.google.android.maps.Overlay;

public class Map extends MapActivity {
	LinearLayout linearLayout;
	MapView mapView;
	Kid jimmy;
	Button newTripBtn;
	Button streetViewBtn;
	Button findChildBtn;
	Button locHistoryBtn;
	//CountDownTimer updateCounter;
	final int LEFT_BOUND = -118291700-500;
	final int RIGHT_BOUND = -118291700+500;
	Drawable drawable;
	MyItemizedOverlay myItemizedOverlay;
	List<Overlay> mapOverlays;
	
	//private NotificationManager NotifManage;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        
        //set up map with controls and control
        /*
        String note = Context.NOTIFICATION_SERVICE;
        NotifManage = (NotificationManager)getSystemService(note);
        int icon = R.drawable.notificationicon;
        CharSequence tripText = "You added a new trip";
        long currTime = System.currentTimeMillis();
        final Notification notif = new Notification(icon, tripText, currTime);
        notif.defaults |= Notification.DEFAULT_VIBRATE;
        notif.defaults = Notification.DEFAULT_ALL;
        //Context context = getApplicationContext();
        */
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapView = (MapView) findViewById(R.id.mapview);

        mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.icon);
        myItemizedOverlay = new MyItemizedOverlay(drawable);
                
        //instantiate a kid object to track and set his initial location
        jimmy = new Kid();
        jimmy.updatePosition(new GeoPoint(34022002, -118291700));
    	myItemizedOverlay.addOverlay(jimmy.getOverlay());
    	mapOverlays.add(myItemizedOverlay);
    	
        updateMap(); //draw location on map
        
        initializeTimers(); //start timers to track his location history and track his current position on map
        
        initializeButtons(); //start all 4 buttons and adds applicable listeners
        
    }
    
    public void updateMap() {
    	//get jimmy's position from the web, update him and his marker with his new position
    	
    	int jimmyLat = jimmy.getPoint().getLatitudeE6();
    	int jimmyLng = jimmy.getPoint().getLongitudeE6();
    	
    	if(jimmyLat > 34024002){
    		jimmyLat += 0;
    		jimmyLng += 123;
    	}
    	else if(jimmyLat > 34024502){
    		jimmyLat += 0;
    		jimmyLng -= 123;
    	}
    	jimmyLat += 123;
    	jimmyLng += 0;
    	
    	//GeoPoint p = new GeoPoint((jimmyLat+1234), (jimmyLng+1234));
    	GeoPoint p = new GeoPoint(jimmyLat, jimmyLng);
    	
    	
    	//mapOverlays.remove(myItemizedOverlay);  //Maybe remove this?
    	//myItemizedOverlay = new MyItemizedOverlay(drawable);
    	//jimmy.updatePosition(p);
    	//myItemizedOverlay.addOverlay(jimmy.getOverlay());
    	//mapOverlays.add(myItemizedOverlay);
    	
    	myItemizedOverlay.addOverlay(jimmy.getOverlay());
    	mapOverlays.add(myItemizedOverlay);
    	
    	jimmy.updatePosition(p);
    	
        mapView.getOverlays();
        mapView.invalidate();
        
        checkOutOfBounds();
    }
    
    public void updateLocationHistory(){
    	jimmy.addLocationHistoryPoint(jimmy.getPoint());
    }

    public void checkOutOfBounds(){    	
    	if (jimmy.getPoint().getLongitudeE6() < LEFT_BOUND || jimmy.getPoint().getLongitudeE6() > RIGHT_BOUND){
    		//notify parent 
    		Toast.makeText(getApplicationContext(), "jimmy IS OUT OF BOUNDS!", Toast.LENGTH_SHORT).show();
    		showDialog(0);
    	}
    }
    
    public void initializeTimers() {
    	
    	//timer to update location history
    	new CountDownTimer(1500000, 40000) {

            public void onTick(long millisUntilFinished) {
            	updateLocationHistory();
            }

            public void onFinish() {
            	//do nothing
            }
         }.start();
    	
    	//update timer will currently run for 15 minutes
    	new CountDownTimer(1500000, 6000) {

            public void onTick(long millisUntilFinished) {
                updateMap();
            }

            public void onFinish() {
            	//do nothing
            }
         }.start();

    }
    
    public void initializeButtons(){
    	
    	//STREET VIEW BUTTON
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
        
        //FIND CHILD BUTTON
        findChildBtn= (Button) findViewById(R.id.findChildBtn);
        findChildBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(getApplicationContext(), "Your child is here!", Toast.LENGTH_SHORT).show();
                mapView.getController().animateTo(jimmy.getPoint());
                mapView.getController().setZoom(18);
       
             
              /*
               Context context = getApplicationContext();
               CharSequence contentTitle = "Trip Notification";
               CharSequence contentText = "You added a brand new trip!";
               Intent notifIntent = new Intent(Map.this, Map.class);
               PendingIntent contentIntent = PendingIntent.getActivity(Map.this, 0, notifIntent, 0); 
               //android.content.Intent.FLAG_ACTIVITY_NEW_TASK
               
               notif.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
               //NotifManage.notify(1,notif);
               */
            }       
        });  //end onClickListener

        //NEW TRIP BUTTON

        newTripBtn = (Button) findViewById(R.id.newTripBtn);
        newTripBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
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
        
      //LOCATION HISTORY BUTTON
        locHistoryBtn = (Button) findViewById(R.id.locHistoryBtn);
        locHistoryBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Toast.makeText(getApplicationContext(), "This will show kid's location history over past 5 minutes", Toast.LENGTH_SHORT).show();
            	mapOverlays.addAll(jimmy.getLocationHistory());
            }        
        });  //end onClickListener   
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    
    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);  
        v.vibrate(500);
        switch(id) {
        case 0:
        	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
             builder.setMessage("Jimmy Is Out of Bounds!")
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        	removeDialog(0);
                        }
                    });
             AlertDialog alert = builder.create();
             dialog = alert;
             break;
             /*
        case 1:
        	AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("New Trip!")
                   .setCancelable(false)
                   .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                       	//dialog.cancel();
                            removeDialog(1);
                       }
                   });
            alert = builder1.create();
            dialog = alert;
            break;
            */
        default:
            dialog = null;
        }
        return dialog;
    }
}

