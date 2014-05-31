package com.hsa.activity;

import java.util.List;

import com.hsa.R;
import com.hsa.adapter.TrackTabsPagerAdapter;
import com.hsa.aggregation.PartialTextualAggregation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;
import com.hsa.handler.SaveHandler;
import com.hsa.handler.SearchHandler;
import com.hsa.handler.TrackHandler;
import com.hsa.handler.ViewHandler;
import com.hsa.fragment.TrackFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class TrackActivity extends ActionBarActivity implements ActionBar.TabListener, TrackFragment.OnTracksListener{
	
	private HSADatabaseHelper dbHelper;
	private TrackTabsPagerAdapter trackTabsPagerAdapter;
	private ViewPager mViewPager;

	private TrackHandler trackHandler;
	
	private String[] tabs = { "Note", "Track"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track);
		
		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		trackTabsPagerAdapter = new TrackTabsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager3);
		mViewPager.setAdapter(trackTabsPagerAdapter);

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
		   	if(tab_name.equals("Track")) {
	    		actionBar.addTab(actionBar.newTab().setText(tab_name)
	                      .setTabListener(this), true);
	      	}else {
	              actionBar.addTab(actionBar.newTab().setText(tab_name)
	                      .setTabListener(this));
	      	}
		}
				        
        //Istanzio una volta sola gli handler
        dbHelper = HSADatabaseHelper.getInstance(this);
        SearchHandler.getInstance(dbHelper);
        SaveHandler.getInstance(dbHelper);
        ViewHandler.getInstance(dbHelper);
        DeckHandler.getInstance(dbHelper);
        trackHandler = TrackHandler.getInstance(dbHelper);   
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
	
	public void onTrackSelected(PartialTextualAggregation partial){
		List<PartialTextualAggregation> partials = trackHandler.trackCard(partial);
        TrackFragment trackFragment = (TrackFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager3 + ":" + mViewPager.getCurrentItem());
        trackFragment.viewPartialTextualAggregation(partials);
        trackFragment.viewDeckCardsNumber(partials);
        String nameLast = trackHandler.lastTrackRequest();
        String[] splitName = nameLast.split(" ");
        nameLast = "";
		for(String split : splitName) {
			nameLast+= split.substring(0, 1).toUpperCase() + split.substring(1) + " ";
		}
        trackFragment.viewLastCardTracked(nameLast);
	}
	
	public void onClickUndo(View v){
		List<PartialTextualAggregation> partials = trackHandler.lastCardRestorationRequest();
		if(partials == null){
			AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
			
            dlgAlert.setMessage("No cards to be restored.");
            dlgAlert.setTitle("WARNING");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.create().show();
            
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
		}else{
			TrackFragment trackFragment = (TrackFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager3 + ":" + mViewPager.getCurrentItem());
	        trackFragment.viewPartialTextualAggregation(partials);
	        trackFragment.viewDeckCardsNumber(partials);
	        
	        String nameLast = trackHandler.lastTrackRequest();
	        if(nameLast != null){
	        	String[] splitName = nameLast.split(" ");
	        	nameLast = "";
				for(String split : splitName) {
					nameLast+= split.substring(0, 1).toUpperCase() + split.substring(1) + " ";
				}
	        }else{
	        	nameLast = "";
	        }
	        
	        
	        trackFragment.viewLastCardTracked(nameLast);
		}
	}
	
	public void onClickReset(View v){
		List<PartialTextualAggregation> partials = trackHandler.partialTextualAggregationsRequest(null);
		if(partials == null){
			AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
			
            dlgAlert.setMessage("Deck is already reseted.");
            dlgAlert.setTitle("WARNING");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.create().show();
            
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
		}else{
			TrackFragment trackFragment = (TrackFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager3 + ":" + mViewPager.getCurrentItem());
	        trackFragment.viewPartialTextualAggregation(partials);
	        trackFragment.viewDeckCardsNumber(partials);
	        trackFragment.viewLastCardTracked(null);
		}
	}

}
