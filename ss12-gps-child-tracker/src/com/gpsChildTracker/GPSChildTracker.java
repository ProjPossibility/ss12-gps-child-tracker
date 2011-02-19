package com.gpsChildTracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GPSChildTracker extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ((TextView) findViewById(R.id.coolid)).setText("cool");
    }
}