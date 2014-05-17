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

public class FilterActivity extends ActionBarActivity {

//	private CheckBox chkDruid, chkHunter, chkMage, chkPaladin, chkPriest, chkRogue, chkShaman, chkWarlock, chkWarrior, chkZero, chkOne, chkTwo, chkThree, chkFour, chkFive, chkSix, chkSeven, chkBasic, chkCommon, chkRare, chkEpic, chkLegendary, chkMinion, chkSpell, chkWeapon;
	private Button btnAccept, btnReset;
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
		
		btnReset = (Button) findViewById(R.id.buttonCancelFilter);
		
		btnReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for(CheckBox checkBox : checkboxes) {
					checkBox.setChecked(false); // TODO settare gli arraylist a vuoti quando saranno fatti
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
					String verifica = checkBox.getText().toString();
					if(checkBox.isChecked())
						if(checkBox.getText().toString().equals("Druid") ||
								checkBox.getText().toString().equals("Hunter") ||
								checkBox.getText().toString().equals("Mage") ||
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
