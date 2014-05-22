package com.hsa.activity;

import java.util.ArrayList;
import java.util.List;

import com.hsa.R;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class FilterActivity extends ActionBarActivity {

//	private CheckBox chkDruid, chkHunter, chkMage, chkPaladin, chkPriest, chkRogue, chkShaman, chkWarlock, chkWarrior, chkZero, chkOne, chkTwo, chkThree, chkFour, chkFive, chkSix, chkSeven, chkBasic, chkCommon, chkRare, chkEpic, chkLegendary, chkMinion, chkSpell, chkWeapon;
	private Button btnAccept, btnClear, btnReset;
	private List<CheckBox> checkboxes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
		
		
		checkboxes = new ArrayList<CheckBox>();
		addListenerOnButtons();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter, menu);
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
	
	public void addListenerOnButtons() {

		checkboxes.add((CheckBox) findViewById(R.id.chkDruid));
		checkboxes.add((CheckBox) findViewById(R.id.chkHunter));
		checkboxes.add((CheckBox) findViewById(R.id.chkMage));
		checkboxes.add((CheckBox) findViewById(R.id.chkNeutral));
		checkboxes.add((CheckBox) findViewById(R.id.chkPaladin));
		checkboxes.add((CheckBox) findViewById(R.id.chkPriest));
		checkboxes.add((CheckBox) findViewById(R.id.chkRogue));
		checkboxes.add((CheckBox) findViewById(R.id.chkShaman));
		checkboxes.add((CheckBox) findViewById(R.id.chkWarlock));
		checkboxes.add((CheckBox) findViewById(R.id.chkWarrior));
		checkboxes.add((CheckBox) findViewById(R.id.chkZero));
		checkboxes.add((CheckBox) findViewById(R.id.chkOne));
		checkboxes.add((CheckBox) findViewById(R.id.chkTwo));
		checkboxes.add((CheckBox) findViewById(R.id.chkThree));
		checkboxes.add((CheckBox) findViewById(R.id.chkFour));
		checkboxes.add((CheckBox) findViewById(R.id.chkFive));
		checkboxes.add((CheckBox) findViewById(R.id.chkSix));
		checkboxes.add((CheckBox) findViewById(R.id.chkSeven));
		checkboxes.add((CheckBox) findViewById(R.id.chkEight));
		checkboxes.add((CheckBox) findViewById(R.id.chkNine));
		checkboxes.add((CheckBox) findViewById(R.id.chkTen));
		checkboxes.add((CheckBox) findViewById(R.id.chkTwelve));
		checkboxes.add((CheckBox) findViewById(R.id.chkTwenty));
		checkboxes.add((CheckBox) findViewById(R.id.chkBasic));
		checkboxes.add((CheckBox) findViewById(R.id.chkCommon));
		checkboxes.add((CheckBox) findViewById(R.id.chkRare));
		checkboxes.add((CheckBox) findViewById(R.id.chkEpic));
		checkboxes.add((CheckBox) findViewById(R.id.chkLegendary));
		checkboxes.add((CheckBox) findViewById(R.id.chkMinion));
		checkboxes.add((CheckBox) findViewById(R.id.chkSpell));
		checkboxes.add((CheckBox) findViewById(R.id.chkWeapon));
		
		Intent intent = getIntent();
		ArrayList<String> classFilters = intent.getExtras().getStringArrayList("classResult");
		String classDeck = (String) intent.getExtras().get("classDeck");
		if(classFilters != null && classFilters.size() != 0){
			for(String s : classFilters){
				switch (s){
					case "Druid": checkboxes.get(0).setChecked(true);
						break;
					case "Hunter": checkboxes.get(1).setChecked(true);
						break;
					case "Mage": checkboxes.get(2).setChecked(true);
						break;
					case "Neutral": checkboxes.get(3).setChecked(true);
						break;
					case "Paladin": checkboxes.get(4).setChecked(true);
						break;
					case "Priest": checkboxes.get(5).setChecked(true);
						break;
					case "Rogue": checkboxes.get(6).setChecked(true);
						break;
					case "Shaman": checkboxes.get(7).setChecked(true);
						break;
					case "Warlock": checkboxes.get(8).setChecked(true);
						break;
					case "Warrior": checkboxes.get(9).setChecked(true);
						break;
				}
			}
		}
		LinearLayout ll = new LinearLayout(this);
		if(classDeck != null){
			if(!classDeck.equals("Druid")) {
				ll = (LinearLayout) findViewById(R.id.layoutDruid);
				ll.removeAllViews();
			}
			if(!classDeck.equals("Hunter")) {
				ll = (LinearLayout) findViewById(R.id.layoutHunter);
				ll.removeAllViews();
			}
			if(!classDeck.equals("Mage")) {
				ll = (LinearLayout) findViewById(R.id.layoutMage);
				ll.removeAllViews();
			}
			if(!classDeck.equals("Paladin")) {
				ll = (LinearLayout) findViewById(R.id.layoutPaladin);
				ll.removeAllViews();
			}
			if(!classDeck.equals("Priest")) {
				ll = (LinearLayout) findViewById(R.id.layoutPriest);
				ll.removeAllViews();
			}
			if(!classDeck.equals("Rogue")) {
				ll = (LinearLayout) findViewById(R.id.layoutRogue);
				ll.removeAllViews();
			}
			if(!classDeck.equals("Shaman")) {
				ll = (LinearLayout) findViewById(R.id.layoutShaman);
				ll.removeAllViews();
			}
			if(!classDeck.equals("Warlock")) {
				ll = (LinearLayout) findViewById(R.id.layoutWarlock);
				ll.removeAllViews();
			}
			if(!classDeck.equals("Warrior")) {
				ll = (LinearLayout) findViewById(R.id.layoutWarrior);
				ll.removeAllViews();
			}
			
		}
		
		ArrayList<String> costFilters = intent.getExtras().getStringArrayList("costResult");
		
		if(costFilters != null && costFilters.size() != 0){
			for(String s : costFilters){
				switch (s){
					case "0": checkboxes.get(10).setChecked(true);
						break;
					case "1": checkboxes.get(11).setChecked(true);
						break;
					case "2": checkboxes.get(12).setChecked(true);
						break;
					case "3": checkboxes.get(13).setChecked(true);
						break;
					case "4": checkboxes.get(14).setChecked(true);
						break;
					case "5": checkboxes.get(15).setChecked(true);
						break;
					case "6": checkboxes.get(16).setChecked(true);
						break;
					case "7": checkboxes.get(17).setChecked(true);
						break;
					case "8": checkboxes.get(18).setChecked(true);
						break;
					case "9": checkboxes.get(19).setChecked(true);
						break;
					case "10": checkboxes.get(20).setChecked(true);
						break;
					case "12": checkboxes.get(21).setChecked(true);
						break;
					case "20": checkboxes.get(22).setChecked(true);
						break;
				}
			}
		}
		
		ArrayList<String> rarityFilters = intent.getExtras().getStringArrayList("rarityResult");
		
		if(rarityFilters != null && rarityFilters.size() != 0){
			for(String s : rarityFilters){
				switch (s){
					case "Basic": checkboxes.get(23).setChecked(true);
						break;
					case "Common": checkboxes.get(24).setChecked(true);
						break;
					case "Rare": checkboxes.get(25).setChecked(true);
						break;
					case "Epic": checkboxes.get(26).setChecked(true);
						break;
					case "Legendary": checkboxes.get(27).setChecked(true);
						break;
				}
			}
		}
		
		ArrayList<String> typeFilters = intent.getExtras().getStringArrayList("typeResult");
		
		if(typeFilters != null && typeFilters.size() != 0){
			for(String s : typeFilters){
				switch (s){
					case "Minion": checkboxes.get(28).setChecked(true);
						break;
					case "Spell": checkboxes.get(29).setChecked(true);
						break;
					case "Weapon": checkboxes.get(30).setChecked(true);
						break;
				}
			}
		}
		
		btnReset = (Button) findViewById(R.id.buttonResetFilter);
				
		btnReset.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					for(CheckBox checkBox : checkboxes) {
						checkBox.setChecked(true);
					}
				}
			});
		
		btnClear = (Button) findViewById(R.id.buttonClearFilter);
		
		btnClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for(CheckBox checkBox : checkboxes) {
					checkBox.setChecked(false);
				}
			}
		});
		
		btnAccept = (Button) findViewById(R.id.buttonAcceptFilter);
		
		btnAccept.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ArrayList<String> classFilters = new ArrayList<String>();
				ArrayList<String> costFilters = new ArrayList<String>();
				ArrayList<String> rarityFilters = new ArrayList<String>();
				ArrayList<String> typeFilters = new ArrayList<String>();
				for(CheckBox checkBox : checkboxes) {
					if(checkBox.isChecked()){
						if(checkBox.getText().toString().equals("Druid") ||
								checkBox.getText().toString().equals("Hunter") ||
								checkBox.getText().toString().equals("Mage") ||
								checkBox.getText().toString().equals("Neutral") ||
								checkBox.getText().toString().equals("Paladin") ||
								checkBox.getText().toString().equals("Priest") ||
								checkBox.getText().toString().equals("Rogue") ||
								checkBox.getText().toString().equals("Shaman") ||
								checkBox.getText().toString().equals("Warlock") ||
								checkBox.getText().toString().equals("Warrior")){
							classFilters.add(checkBox.getText().toString());
						}else if(checkBox.getText().toString().equals("Basic") ||
								checkBox.getText().toString().equals("Common") ||
								checkBox.getText().toString().equals("Rare") ||
								checkBox.getText().toString().equals("Rare") ||
								checkBox.getText().toString().equals("Epic") ||
								checkBox.getText().toString().equals("Legendary")){
							rarityFilters.add(checkBox.getText().toString());
						}else if(checkBox.getText().toString().equals("0") ||
								checkBox.getText().toString().equals("1") ||
								checkBox.getText().toString().equals("2") ||
								checkBox.getText().toString().equals("3") ||
								checkBox.getText().toString().equals("4") ||
								checkBox.getText().toString().equals("5") ||
								checkBox.getText().toString().equals("6") ||
								checkBox.getText().toString().equals("7") ||
								checkBox.getText().toString().equals("8") ||
								checkBox.getText().toString().equals("9") ||
								checkBox.getText().toString().equals("10") ||
								checkBox.getText().toString().equals("12") ||
								checkBox.getText().toString().equals("20")){
							costFilters.add(checkBox.getText().toString());
						}else{
							typeFilters.add(checkBox.getText().toString());
						}
					}
				}
				
				Intent returnIntent = new Intent();
				returnIntent.putStringArrayListExtra("classResult", classFilters);
				returnIntent.putStringArrayListExtra("costResult", costFilters);
				returnIntent.putStringArrayListExtra("rarityResult", rarityFilters);
				returnIntent.putStringArrayListExtra("typeResult", typeFilters);
				
				setResult(RESULT_OK,returnIntent);
				finish();
			}
		});
		
		
	}

}
