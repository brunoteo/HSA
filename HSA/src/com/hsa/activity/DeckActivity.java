package com.hsa.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsa.MainActivity;
import com.hsa.R;
import com.hsa.adapter.DeckTabsPagerAdapter;
import com.hsa.aggregation.CompleteTextualAggregation;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.SearchCriterion;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.fragment.DeckFragment;
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
ActionBar.TabListener, DeckFragment.OnDeckListener{
	
	private HSADatabaseHelper dbHelper;
	private DeckTabsPagerAdapter deckTabsPagerAdapter;
	private ViewPager mViewPager;

	//FIXME forse non servono
	private SaveHandler saveHandler;
	private SearchHandler searchHandler;
	private ViewHandler viewHandler;
	private DeckHandler deckHandler;
	private TrackHandler trackHandler;
	private DeckDataAggregation deckData;
	
	private ArrayList<String> classFilters;
    private ArrayList<String> costFilters;
    private ArrayList<String> rarityFilters;
    private ArrayList<String> typeFilters;
    private String nameFilter;
	
	private String[] tabs = { "Note", "Deck", "Deck Info" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck);
		Intent intent = getIntent();
		deckData = intent.getParcelableExtra("deckDataAggregation");
		classFilters = new ArrayList<String>();
		classFilters.add(deckData.getClassName());
		classFilters.add("Neutral");
		
		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(deckData.getName());
		actionBar.setSubtitle(Integer.toString(deckData.getCardNumber())+"/30");
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
        inflater.inflate(R.menu.deck, menu);
        return super.onCreateOptionsMenu(menu);

	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	        	if(data.getStringArrayListExtra("classResult").size()!=0)
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
	            DeckFragment deckFragment = (DeckFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager2 + ":" + mViewPager.getCurrentItem());
	            deckFragment.viewGraphicsAggregations(graphicalsAggregations);
	        }
	    }
	}

	public void launchIntent(){
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
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
			case R.id.save://TODO aggiornare la lista mazzi quando si torna indietro
				if(!deckHandler.controlModifyRequest()) {
					AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
				
		            dlgAlert.setMessage("INFO: Deck up to date.");
		            dlgAlert.setTitle("Info Message...");
		            	            
		            dlgAlert.setPositiveButton("Ok",
		                    new DialogInterface.OnClickListener() {
		                        public void onClick(DialogInterface dialog, int which) {
		                        	
		                        }
		                    });
		            
		            dlgAlert.create().show();
		            
				} else {
					AlertDialog.Builder dlgAlertSave  = new AlertDialog.Builder(this);
					
					dlgAlertSave.setMessage("INFO: Do you want to save?");
					dlgAlertSave.setTitle("Info Save...");
					
					dlgAlertSave.setNegativeButton("CANCEL", 
							new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
								}
							});
						            
					dlgAlertSave.setPositiveButton("Ok",
		                    new DialogInterface.OnClickListener() {
		                        public void onClick(DialogInterface dialog, int which) {
		                        	deckHandler.saveRequest();
		                        	launchIntent();
		                        }
		                    });
					
					dlgAlertSave.create().show();
				}
				return true;
			case R.id.filterDeck :			
				Intent intent = new Intent(this, FilterActivity.class);
				intent.putExtra("classDeck", deckData.getClassName());
				intent.putStringArrayListExtra("classResult", classFilters);
				intent.putStringArrayListExtra("costResult", costFilters);
				intent.putStringArrayListExtra("rarityResult", rarityFilters);
				intent.putStringArrayListExtra("typeResult", typeFilters);
				startActivityForResult(intent, 1);
				return true;
//			case R.id.all_cardDeck:
//				classFilters = null;
//				costFilters = null;
//				rarityFilters = null;
//				typeFilters = null;
//				nameFilter = null;
//	            DeckFragment deckFragment = (DeckFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager2 + ":" + mViewPager.getCurrentItem());
//				List<GraphicalAggregation> graphicalsAggregations = viewHandler.cardsSearchRequest(null, this);
//	            deckFragment.viewGraphicsAggregations(graphicalsAggregations);
//				return true;
			case R.id.track :
				int cardNumber = deckHandler.cardNumberRequest();
				if(cardNumber == 30){
					Intent intent2 = new Intent(this, TrackActivity.class);
					startActivity(intent2);
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
			case R.id.delete ://TODO conferma di eliminazione
				
				AlertDialog.Builder dlgAlertSave  = new AlertDialog.Builder(this);
				
				dlgAlertSave.setMessage("INFO: Do you want to delete?");
				dlgAlertSave.setTitle("Delete Deck...");
				
				dlgAlertSave.setNegativeButton("CANCEL", 
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
							}
						});
					            
				dlgAlertSave.setPositiveButton("Ok",
	                    new DialogInterface.OnClickListener() {
	                        public void onClick(DialogInterface dialog, int which) {
	                        	deckHandler.deckDeletionRequest();
	                        	launchIntent();
	                        }
	                    });
				
				dlgAlertSave.create().show();
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

	@Override
	public void onCardSelected(CompleteTextualAggregation completeAggregation) {
		Intent intent = new Intent(this, CompleteInformationActivity.class);
		intent.putExtra("completeAggregation", completeAggregation);
		startActivity(intent);
	}
	
	public void viewNumCards(List<GraphicalAggregation> graphicalsAggregations) {
		int numCards = 0;
		for(GraphicalAggregation ga : graphicalsAggregations) {
			numCards += ga.getOccurence();
		}
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setSubtitle(Integer.toString(numCards)+"/30");
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
