package com.hsa;

import java.util.List;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.hsa.activity.CompleteInformationActivity;
import com.hsa.activity.FilterActivity;
import com.hsa.activity.NewDeckActivity;
import com.hsa.adapter.TabsPagerAdapter;
import com.hsa.aggregation.CompleteTextualAggregation;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.SearchCriterion;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.fragment.SearchFragment;
import com.hsa.handler.SaveHandler;
import com.hsa.handler.SearchHandler;
import com.hsa.handler.ViewHandler;

public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener, SearchFragment.OnSearchListener{

	public static HSADatabaseHelper dbHelper;
	private TabsPagerAdapter tabsPagerAdapter;
	private ViewPager mViewPager;
	private static SaveHandler saveHandler;
	private static SearchHandler searchHandler;
	public static ViewHandler viewHandler;
	
	public HSADatabaseHelper getDbHelper() {
		return dbHelper;
	}

	public void setDbHelper(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

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
        
        dbHelper = new HSADatabaseHelper(this);
        searchHandler = new SearchHandler(dbHelper);
        saveHandler = new SaveHandler(dbHelper);
        viewHandler = new ViewHandler(dbHelper);
        searchHandler.setSaveHandler(saveHandler);
        searchHandler.setViewHandler(viewHandler);
        viewHandler.setSearchHandler(searchHandler);
        viewHandler.setSaveHandler(saveHandler);
       //Riempimento database
        int emptyDB = searchHandler.search(null).size();
        if(emptyDB==0) {
            saveHandler.fillDB();
        }
        
	}
	
	public SaveHandler getSaveHandler() {
		return saveHandler;
	}

	public void setSaveHandler(SaveHandler saveHandler) {
		this.saveHandler = saveHandler;
	}

	public SearchHandler getSearchHandler() {
		return searchHandler;
	}

	public void setSearchHandler(SearchHandler searchHandler) {
		this.searchHandler = searchHandler;
	}

	public ViewHandler getViewHandler() {
		return viewHandler;
	}

	public void setViewHandler(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
	}

	public void onClickND(View v) {
		Intent intent = new Intent(MainActivity.this, NewDeckActivity.class);
	    startActivity(intent);
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		handleIntent(intent);
	}
	

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            List<String> filters = data.getStringArrayListExtra("result");
	            System.out.println("Pippo");
	        }
	    }
	}

	private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
            SearchCriterion criterion = new SearchCriterion(query, null);
            List<GraphicalAggregation> graphicalsAggregations = viewHandler.searchRequest(criterion, this);
            searchFragment.viewGraphicsAggregations(graphicalsAggregations);
            
        }
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
			case R.id.filter :
				Intent intent = new Intent(this, FilterActivity.class);
				startActivityForResult(intent, 1);
				return true;
			default:
	            return super.onOptionsItemSelected(item);
		}
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

	@Override
	public void onCardSelected(CompleteTextualAggregation completeAggregation) {
		
		Intent intent = new Intent(this, CompleteInformationActivity.class);
		intent.putExtra("completeAggregation", completeAggregation);
		startActivity(intent);

	}
}
