package com.gpsChildTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class GPSChildTracker extends Activity {
	
	boolean checked = false; 
	Button loginBtn;
	Map map;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //from example in check box section:   http://developer.android.com/resources/tutorials/views/hello-formstuff.html#Checkbox
        final CheckBox checkbox = (CheckBox) findViewById(R.id.childCheck);
        checkbox.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(checkbox.isChecked()==false)
            	{
            	checkbox.setChecked(true);
            	{
            		if(checkbox.isChecked()== true)
            		{
            			checkbox.setChecked(false);
            		}
            		}
            	}
            }
            
        });
        

        
        loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		
        		map = new Map();
        		//Toast.makeText(getApplicationContext(), "cool", Toast.LENGTH_SHORT).show();
        			if(checkbox.isChecked() == false) //if it is not the child's phone the map is pulled up 
        			{	
        				
        				Intent myIntent = new Intent(v.getContext(), Map.class);
        				startActivityForResult(myIntent, 0);
        			}
        			else //if it is the child's phone the kid can not add trips. Only his location is updated
        			{
        				
        				Intent aIntent = new Intent(v.getContext(), KidTrack.class);
        				startActivityForResult(aIntent, 1);
        			}
        			
        			
        	}
        });
    }
}