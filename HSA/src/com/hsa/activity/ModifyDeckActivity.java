package com.hsa.activity;

import com.hsa.R;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.ViewHandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class ModifyDeckActivity extends ActionBarActivity{
	
	HSADatabaseHelper dbHelper;
	ViewHandler viewHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_deck);
		dbHelper = HSADatabaseHelper.getInstance(this);
	    viewHandler = ViewHandler.getInstance(dbHelper);
		Intent intent = getIntent();
		DeckDataAggregation deckData = intent.getParcelableExtra("deckDataAggregation");
		viewHandler.viewDeckRequest(deckData);
	}
}
