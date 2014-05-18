package com.hsa.activity;

import com.hsa.R;
import com.hsa.adapter.DeckTabsPagerAdapter;
import com.hsa.adapter.MainTabsPagerAdapter;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;
import com.hsa.handler.SaveHandler;
import com.hsa.handler.SearchHandler;
import com.hsa.handler.TrackHandler;
import com.hsa.handler.ViewHandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;

public class DeckActivity extends ActionBarActivity implements
ActionBar.TabListener{
	
	private HSADatabaseHelper dbHelper;
	private DeckTabsPagerAdapter deckTabsPagerAdapter;
	private ViewPager mViewPager;

	private SaveHandler saveHandler;
	private SearchHandler searchHandler;
	private ViewHandler viewHandler;
	private DeckHandler deckHandler;
	private TrackHandler trackHandler;
	
	private String[] tabs = { "Note", "Deck", "Deck Info" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck);
		Intent intent = getIntent();
		DeckDataAggregation deckData = intent.getParcelableExtra("deckDataAggregation");
		
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
		        
		viewHandler.viewDeckRequest(deckData);
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
}