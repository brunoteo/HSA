package com.hsa;

import java.util.List;

import com.hsa.adapter.TabsPagerAdapter;
import com.hsa.bean.Card;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.manager.SaveManager;
import com.hsa.manager.SearchManager;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	HSADatabaseHelper dbHelper;
	TabsPagerAdapter tabsPagerAdapter;
	ViewPager mViewPager;
	private String[] tabs = { "News", "Search", "Decks" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		tabsPagerAdapter = new TabsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(tabsPagerAdapter);

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
        	if(tab_name.equals("Search")) {
        		actionBar.addTab(actionBar.newTab().setText(tab_name)
                        .setTabListener(this), true);
        	}else {
                actionBar.addTab(actionBar.newTab().setText(tab_name)
                        .setTabListener(this));
        	}
        }
        
      //PROVA DATABASE
//        dbHelper = new HSADatabaseHelper(this);
//        SearchManager searchManager = new SearchManager(dbHelper);
//        int emptyDB = searchManager.search(null).size();
//        if(emptyDB==0) {
//            SaveManager saveManager = new SaveManager(dbHelper);
//            saveManager.fillDB();
//        }
//        List<Card> cards = searchManager.search(null);
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);       
//        textView.setText(Integer.toString(cards.size()));
//
//        // Set the text view as the activity layout
//        setContentView(textView);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}
}
