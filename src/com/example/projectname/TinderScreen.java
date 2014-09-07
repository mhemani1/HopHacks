package com.example.projectname;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class TinderScreen extends ActionBarActivity {
	int radius;
	double latitude;
	double longitude;
	ArrayList<String> options;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tinder_screen);
		
		radius = getIntent().getExtras().getInt("Radius");
		latitude = getIntent().getExtras().getDouble("Latitude");
		longitude = getIntent().getExtras().getDouble("Longitude");
		options = getIntent().getStringArrayListExtra("Chosen");
		System.out.println(options);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tinder_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
