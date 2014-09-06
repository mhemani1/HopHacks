package com.example.projectname;

import android.support.v7.app.ActionBarActivity;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	protected static final int DROP_SCREEN_ACTIVITY = 0;
	Button nextButton;
	SeekBar rad;
	TextView radCounter;
	int radius = (int) 0.25;
	double latitude;
	double longitude;
    
    // GPSTracker class
    GPS gps;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        nextButton = (Button) findViewById(R.id.button1);
        rad = (SeekBar) findViewById(R.id.radius_bar);
        radCounter = (TextView) findViewById(R.id.radius);
        radCounter.setText("0 miles");
        gps = new GPS(MainActivity.this);
        
        // check if GPS enabled     
        if(gps.canGetLocation()){
             
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
                         
            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your location has been detected.", Toast.LENGTH_LONG).show();    
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        
        rad.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		    int progress = 0;
        	@Override
        	public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
        	    progress = progressValue;
        	    radius = progress;
        	}

        	@Override
        	public void onStartTrackingTouch(SeekBar seekBar) {
        	    // Do something here, 
        	    //if you want to do anything at the start of
        	    // touching the seekbar
        	}

        	    @Override
        	public void onStopTrackingTouch(SeekBar seekBar) {
        	    radCounter.setText(progress + " miles");
        	}

        });
        
        nextButton.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0){
        		Intent i = new Intent(MainActivity.this, DropScreen.class);
        		i.putExtra("Radius", (int)radius);
        		i.putExtra("Longitude", (double)longitude);
        		i.putExtra("Latitude", (double)latitude);
        		startActivityForResult(i,DROP_SCREEN_ACTIVITY);
        	}
        });
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	if (data != null){
    		if (requestCode == DROP_SCREEN_ACTIVITY){}
        }
    }
}