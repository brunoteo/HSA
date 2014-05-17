package com.hsa.activity;

import java.io.Serializable;

import com.hsa.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class ModifyDeckActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_deck);
		
		Intent intent = getIntent();
	}
}
