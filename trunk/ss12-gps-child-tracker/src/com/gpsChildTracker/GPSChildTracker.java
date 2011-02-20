package com.gpsChildTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GPSChildTracker extends Activity {
	
	Button loginBtn;
	Map map;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		map = new Map();
        		//Toast.makeText(getApplicationContext(), "cool", Toast.LENGTH_SHORT).show();
        		Intent myIntent = new Intent(v.getContext(), Map.class);
        		startActivityForResult(myIntent, 0);
        	}
        });
    }
}