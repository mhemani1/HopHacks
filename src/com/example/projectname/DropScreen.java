package com.example.projectname;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class DropScreen extends ActionBarActivity {
	protected static final int TINDER_SCREEN = 0;
	
	ExpandableList listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	
	Button nextButton;
    ArrayList<String> chosen = new ArrayList<String>();
    int radius;
    double latitude;
    double longitude;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drop_screen);
        nextButton = (Button) findViewById(R.id.button1);
		radius = getIntent().getExtras().getInt("Radius");
		latitude = getIntent().getExtras().getDouble("Latitude");
		longitude = getIntent().getExtras().getDouble("Longitude");
		
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableList(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				/*Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();*/
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				/*Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();*/
			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
						+ " : "
						+ listDataChild.get(
								listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
										.show();
				chosen.add(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
				return false;
			}
		});

        nextButton.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0){
        		Intent i = new Intent(DropScreen.this, TinderScreen.class);
        		i.putExtra("Radius", (int)radius);
        		i.putExtra("Longitude", (double)longitude);
        		i.putExtra("Latitude", (double)latitude);
        		i.putExtra("Chosen", chosen);
        		startActivityForResult(i,TINDER_SCREEN);
        	}
        });
	}
	
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Food");
		listDataHeader.add("Drink");
		listDataHeader.add("Attractions");
		listDataHeader.add("Shopping");
		listDataHeader.add("Miscellaneous");
		
		// Adding child data
		List<String> food = new ArrayList<String>();
		food.add("Bakeries");
		food.add("Fast Food");
		food.add("Restaurants");
		
		List<String> drink = new ArrayList<String>();
		drink.add("Bars");
		drink.add("Cafes");
		drink.add("Liquor Stores");
		
		List<String> attractions = new ArrayList<String>();
		attractions.add("Amusement Parks");
		attractions.add("Aquariums");
		attractions.add("Art Galleries");
		attractions.add("Bowling Alleys");
		attractions.add("Casinos");
		attractions.add("Movie Theaters");
		attractions.add("Museums");
		attractions.add("Night Clubs");
		attractions.add("Parks");
		attractions.add("Stadiums");
		attractions.add("Zoos");
		
		List<String> shop = new ArrayList<String>();
		shop.add("Clothing");
		shop.add("Convenience");
		shop.add("Books");
		shop.add("Pets");
		shop.add("Department Stores");
		shop.add("Electronics");
		shop.add("Furniture");
		shop.add("Grocery");
		shop.add("Jewelry");
		shop.add("Shoes");
		shop.add("Malls");
		
		List<String> misc = new ArrayList<String>();
		misc.add("Salons");
		misc.add("Churches");
		misc.add("Libraries");
		misc.add("Lodging");
		misc.add("Parking");
		misc.add("Spas");
		
		listDataChild.put(listDataHeader.get(0), food); // Header, Child data
		listDataChild.put(listDataHeader.get(1), drink);
		listDataChild.put(listDataHeader.get(2), attractions);
		listDataChild.put(listDataHeader.get(3), shop);
		listDataChild.put(listDataHeader.get(4), misc);
	}
	  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drop_screen, menu);
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
	
	public void setRadius(int rad)
	{
		this.radius = rad;
	}
	
	public void setLongitude(double l)
	{
		this.longitude = l;
	}
	
	public void setLatitude(double l)
	{
		this.latitude = l;
	}
}
