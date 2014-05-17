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
		
//		chkDruid = (CheckBox) findViewById(R.id.chkDruid);
//		chkHunter = (CheckBox) findViewById(R.id.chkHunter);
//		chkMage = (CheckBox) findViewById(R.id.chkMage);
//		chkPaladin = (CheckBox) findViewById(R.id.chkPaladin);
//		chkPriest = (CheckBox) findViewById(R.id.chkPriest);
//		chkRogue = (CheckBox) findViewById(R.id.chkRogue);
//		chkShaman = (CheckBox) findViewById(R.id.chkShaman);
//		chkWarlock = (CheckBox) findViewById(R.id.chkWarlock);
//		chkWarrior = (CheckBox) findViewById(R.id.chkWarrior);
//		chkZero = (CheckBox) findViewById(R.id.chkZero);
//		chkOne = (CheckBox) findViewById(R.id.chkOne);
//		chkTwo = (CheckBox) findViewById(R.id.chkTwo);
//		chkThree = (CheckBox) findViewById(R.id.chkThree);
//		chkFour = (CheckBox) findViewById(R.id.chkFour);
//		chkFive = (CheckBox) findViewById(R.id.chkFive);
//		chkSix = (CheckBox) findViewById(R.id.chkSix);
//		chkSeven = (CheckBox) findViewById(R.id.chkSeven);
//		chkBasic = (CheckBox) findViewById(R.id.chkBasic);
//		chkCommon = (CheckBox) findViewById(R.id.chkCommon);
//		chkRare = (CheckBox) findViewById(R.id.chkRare);
//		chkEpic = (CheckBox) findViewById(R.id.chkEpic);
//		chkLegendary = (CheckBox) findViewById(R.id.chkLegendary);
//		chkMinion = (CheckBox) findViewById(R.id.chkMinion);
//		chkSpell = (CheckBox) findViewById(R.id.chkSpell);
//		chkWeapon = (CheckBox) findViewById(R.id.chkWeapon);
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
					checkBox.setChecked(false);
				}
			}
		});
		
		btnAccept = (Button) findViewById(R.id.buttonAcceptFilter);
		
		btnAccept.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ArrayList<String> filters = new ArrayList<String>();
				for(CheckBox checkBox : checkboxes) {
					if(checkBox.isChecked())
						filters.add(checkBox.getText().toString());
				}
				
				Intent returnIntent = new Intent();
				returnIntent.putStringArrayListExtra("result", filters);
				setResult(RESULT_OK,returnIntent);
				finish();
			}
		});
		
		
	}

}
