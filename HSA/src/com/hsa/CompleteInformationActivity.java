package com.hsa;

import com.hsa.aggregation.CompleteTextualAggregation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class CompleteInformationActivity extends ActionBarActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complete_textual);
		
		Intent intent = getIntent();
		CompleteTextualAggregation completeAggregation = intent.getParcelableExtra("completeAggregation");
		viewCompleteTextualAggregation(completeAggregation);
	}
	
	private void viewCompleteTextualAggregation(CompleteTextualAggregation completeAggregation) {
		TextView textView = (TextView) findViewById(R.id.textviewName);
		String name = completeAggregation.getName();
		String[] splitName = name.split(" ");
		name = "";
		for(String split : splitName) {
			name+= split.substring(0, 1).toUpperCase() + split.substring(1) + " ";
		}
		textView.setText("Name: " + name);
		textView = (TextView) findViewById(R.id.textviewType);
		textView.setText("Type: " + completeAggregation.getType());
		textView = (TextView) findViewById(R.id.textviewCost);
		textView.setText("Cost: " + Integer.toString(completeAggregation.getCost()));
		textView = (TextView) findViewById(R.id.textviewClassName);
		textView.setText("Class: " + completeAggregation.getClassName());
		textView = (TextView) findViewById(R.id.textviewRarity);
		textView.setText("Rarity: " + completeAggregation.getRarity());
		textView = (TextView) findViewById(R.id.textviewEffect);
		if(completeAggregation.getEffect()!=null)
			textView.setText("Effect: " + completeAggregation.getEffect());
		else
			textView.setText("Effect: -");
			textView = (TextView) findViewById(R.id.textviewAttack);
		if(completeAggregation.getAttack()>-1)
			textView.setText("Attack: " + Integer.toString(completeAggregation.getAttack()));
		else
			textView.setText("Attack: -");
		textView = (TextView) findViewById(R.id.textviewHealth);
		if(completeAggregation.getHealth()>-1)
			textView.setText("Health: " + Integer.toString(completeAggregation.getHealth()));
		else
			textView.setText("Health: -");
		textView = (TextView) findViewById(R.id.textviewDurability);
		if(completeAggregation.getDurability()>-1)
			textView.setText("Durability: " + Integer.toString(completeAggregation.getDurability()));
		else
			textView.setText("Durability: -");
		textView = (TextView) findViewById(R.id.textviewRace);
		if(completeAggregation.getRace()!=null)
			textView.setText("Race: " + completeAggregation.getRace());
		else
			textView.setText("Race: -");
	}
}
