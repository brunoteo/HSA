package com.hsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.hsa.activity.CompleteInformationActivity;
import com.hsa.activity.FilterActivity;
import com.hsa.activity.DeckActivity;
import com.hsa.activity.NewDeckActivity;
import com.hsa.adapter.MainTabsPagerAdapter;
import com.hsa.aggregation.CompleteTextualAggregation;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.SearchCriterion;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.fragment.SearchFragment;
import com.hsa.fragment.DecksFragment;
import com.hsa.handler.DeckHandler;
import com.hsa.handler.SaveHandler;
import com.hsa.handler.SearchHandler;
import com.hsa.handler.TrackHandler;
import com.hsa.handler.ViewHandler;

public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener, SearchFragment.OnSearchListener, DecksFragment.OnDecksListener{

	private HSADatabaseHelper dbHelper;
	private MainTabsPagerAdapter mainTabsPagerAdapter;
	private ViewPager mViewPager;

	private SaveHandler saveHandler;
	private SearchHandler searchHandler;
	private ViewHandler viewHandler;
	
	private ArrayList<String> classFilters;
    private ArrayList<String> costFilters;
    private ArrayList<String> rarityFilters;
    private ArrayList<String> typeFilters;
    private String nameFilter;
    
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
		mainTabsPagerAdapter = new MainTabsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mainTabsPagerAdapter);

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
        
        //Istanzio una volta sola gli handler
        dbHelper = HSADatabaseHelper.getInstance(this);
        searchHandler = SearchHandler.getInstance(dbHelper);
        saveHandler = SaveHandler.getInstance(dbHelper);
        viewHandler = ViewHandler.getInstance(dbHelper);
        DeckHandler.getInstance(dbHelper);
        TrackHandler.getInstance(dbHelper);
        
        resetFilters();
        
       //Riempimento database
        int emptyDB = searchHandler.cardsSearch(null).size();
        if(emptyDB==0) {
            saveHandler.fillDB();
        }
        
	}
	
	public void resetFilters(){
		//Filtri classe checkati
        classFilters = new ArrayList<String>();
        classFilters.add("Druid");
        classFilters.add("Hunter");
        classFilters.add("Mage");
        classFilters.add("Neutral");
        classFilters.add("Paladin");
        classFilters.add("Priest");
        classFilters.add("Rogue");
        classFilters.add("Shaman");
        classFilters.add("Warlock");
        classFilters.add("Warrior");
        
        costFilters = new ArrayList<String>();
        costFilters.add("0");
        costFilters.add("1");
        costFilters.add("2");
        costFilters.add("3");
        costFilters.add("4");
        costFilters.add("5");
        costFilters.add("6");
        costFilters.add("7");
        costFilters.add("8");
        costFilters.add("9");
        costFilters.add("10");
        costFilters.add("12");
        costFilters.add("20");
        
        rarityFilters = new ArrayList<String>();
        rarityFilters.add("Basic");
        rarityFilters.add("Common");
        rarityFilters.add("Rare");
        rarityFilters.add("Epic");
        rarityFilters.add("Legendary");
        
        typeFilters = new ArrayList<String>();
        typeFilters.add("Minion");
        typeFilters.add("Spell");
        typeFilters.add("Weapon");
	}

	public void onClickND(View v) {
		Intent intent = new Intent(MainActivity.this, NewDeckActivity.class);
	    startActivityForResult(intent, 2);
	    
	}
	
//	@Override
//	protected void onNewIntent(Intent intent) {
//		handleIntent(intent);
//	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		//return from filterActivity
	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            classFilters = data.getStringArrayListExtra("classResult");
	            costFilters = data.getStringArrayListExtra("costResult");
	            rarityFilters = data.getStringArrayListExtra("rarityResult");
	            typeFilters = data.getStringArrayListExtra("typeResult");

	            Map<String, ArrayList<String>> filters = new HashMap<String, ArrayList<String>>();
	            if(classFilters != null) {
	            	if(classFilters.size() != 0) filters.put("className", new ArrayList<String>(classFilters));
	            }
	            if(costFilters != null){
	            	if(costFilters.size() != 0) filters.put("cost", new ArrayList<String>(costFilters));
	            }
	            if(rarityFilters != null){
	            	if(rarityFilters.size() != 0) filters.put("rarity", new ArrayList<String>(rarityFilters));
	            }
	            if(typeFilters != null){
	            	if(typeFilters.size() != 0) filters.put("type", new ArrayList<String>(typeFilters));
	            }
	            SearchCriterion searchCriterion = new SearchCriterion(nameFilter, filters);
	            List<GraphicalAggregation> graphicalsAggregations = viewHandler.cardsSearchRequest(searchCriterion, this);
	            SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
	            searchFragment.viewGraphicsAggregations(graphicalsAggregations);
	        }
	    }else if (requestCode == 2){
	    	//query for decks list
	    	if(resultCode == RESULT_OK){
	            DecksFragment decksFragment = (DecksFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
	            List<DeckDataAggregation> deckDataAggregations = viewHandler.decksRequest(this);
	            decksFragment.viewDeckDataAggregations(deckDataAggregations);
	            DeckDataAggregation dda = new DeckDataAggregation();
	            dda = data.getExtras().getParcelable("deckDataAggregation");
	            Intent intent = new Intent(this, DeckActivity.class);
	            intent.putExtra("deckDataAggregation", dda);
	    		startActivity(intent);
	    	}
	    }
	}

//	private void handleIntent(Intent intent) {
//
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            nameFilter = intent.getStringExtra(SearchManager.QUERY);
//            SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
//            Map<String, ArrayList<String>> filters = new HashMap<String, ArrayList<String>>();
//            if(classFilters != null) {
//            	if(classFilters.size() != 0) filters.put("className", new ArrayList<String>(classFilters));
//            }
//            if(costFilters != null){
//            	if(costFilters.size() != 0) filters.put("cost", new ArrayList<String>(costFilters));
//            }
//            if(rarityFilters != null){
//            	if(rarityFilters.size() != 0) filters.put("rarity", new ArrayList<String>(rarityFilters));
//            }
//            if(typeFilters != null){
//            	if(typeFilters.size() != 0) filters.put("type", new ArrayList<String>(typeFilters));
//            }
//            SearchCriterion criterion = new SearchCriterion(nameFilter, filters);
//            List<GraphicalAggregation> graphicalsAggregations = viewHandler.cardsSearchRequest(criterion, this);
//            searchFragment.viewGraphicsAggregations(graphicalsAggregations);
//            
//        }
//    }


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
				intent.putStringArrayListExtra("classResult", classFilters);
				intent.putStringArrayListExtra("costResult", costFilters);
				intent.putStringArrayListExtra("rarityResult", rarityFilters);
				intent.putStringArrayListExtra("typeResult", typeFilters);
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

	@Override
	public void onDeckSelected(DeckDataAggregation deckDataAggregation) {
		Intent intent = new Intent(this, DeckActivity.class);
		intent.putExtra("deckDataAggregation", deckDataAggregation);
		startActivity(intent);
		
	}

	public ArrayList<String> getClassFilters() {
		return classFilters;
	}

	public void setClassFilters(ArrayList<String> classFilters) {
		this.classFilters = classFilters;
	}

	public ArrayList<String> getCostFilters() {
		return costFilters;
	}

	public void setCostFilters(ArrayList<String> costFilters) {
		this.costFilters = costFilters;
	}

	public ArrayList<String> getRarityFilters() {
		return rarityFilters;
	}

	public void setRarityFilters(ArrayList<String> rarityFilters) {
		this.rarityFilters = rarityFilters;
	}

	public ArrayList<String> getTypeFilters() {
		return typeFilters;
	}

	public void setTypeFilters(ArrayList<String> typeFilters) {
		this.typeFilters = typeFilters;
	}

	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}
}
