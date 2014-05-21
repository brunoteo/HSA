package com.hsa.activity;

import com.hsa.MainActivity;
import com.hsa.R;
import com.hsa.adapter.DeckTabsPagerAdapter;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;
import com.hsa.handler.SaveHandler;
import com.hsa.handler.SearchHandler;
import com.hsa.handler.TrackHandler;
import com.hsa.handler.ViewHandler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DeckActivity extends ActionBarActivity implements
ActionBar.TabListener{
	
	private HSADatabaseHelper dbHelper;
	private DeckTabsPagerAdapter deckTabsPagerAdapter;
	private ViewPager mViewPager;

	//FIXME forse non servono
	private SaveHandler saveHandler;
	private SearchHandler searchHandler;
	private ViewHandler viewHandler;
	private DeckHandler deckHandler;
	private TrackHandler trackHandler;
	//FIXME forse non serve a niente anche questo
	private DeckDataAggregation deckData;
	
	private String[] tabs = { "Note", "Deck", "Deck Info" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck);
		Intent intent = getIntent();
		deckData = intent.getParcelableExtra("deckDataAggregation");
		setTitle(deckData.getName());
		
		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		deckTabsPagerAdapter = new DeckTabsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager2);
		mViewPager.setAdapter(deckTabsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// Adding Tabs
        for (String tab_name : tabs) {
        	if(tab_name.equals("Deck")) {
        		actionBar.addTab(actionBar.newTab().setText(tab_name)
                        .setTabListener(this), true);
        	}else {
                actionBar.addTab(actionBar.newTab().setText(tab_name)
                        .setTabListener(this));
        	}
        }
		        
        //Istanzio una volta sola gli handler
        dbHelper = HSADatabaseHelper.getInstance(this);
        searchHandler = SearchHandler.getInstance(dbHelper);
        saveHandler = SaveHandler.getInstance(dbHelper);
        viewHandler = ViewHandler.getInstance(dbHelper);
        deckHandler = DeckHandler.getInstance(dbHelper);
        trackHandler = TrackHandler.getInstance(dbHelper);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id) {
			case R.id.action_settings : 
				return true;
			case R.id.track :
				int cardNumber = deckHandler.cardNumberRequest();
				if(cardNumber == 30){
					Intent intent = new Intent(this, TrackActivity.class);
					startActivity(intent);
				}else{
					AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
					
		            dlgAlert.setMessage("Error: Deck not contains 30 cards.");
		            dlgAlert.setTitle("Error Message...");
		            dlgAlert.setPositiveButton("OK", null);
		            dlgAlert.create().show();
		            
		            dlgAlert.setPositiveButton("Ok",
		                    new DialogInterface.OnClickListener() {
		                        public void onClick(DialogInterface dialog, int which) {

		                        }
		                    });
				}
				return true;
			case R.id.delete :
				AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
				
	            dlgAlert.setMessage("Are you sure?");
	            dlgAlert.setTitle("Delete deck");
	            dlgAlert.setPositiveButton("OK", null);
	            dlgAlert.create().show();
	            
	            dlgAlert.setPositiveButton("Ok",
	                    new DialogInterface.OnClickListener() {
	                        public void onClick(DialogInterface dialog, int which) {

	                        }
	                    });
				deckHandler.deckDeletionRequest();
				Intent intent1 = new Intent(this, MainActivity.class);
				startActivity(intent1);
				return true;
			default:
	            return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		
	}
	
	public DeckDataAggregation getDeckDataAggregation() {
		return this.deckData;
	}
}
