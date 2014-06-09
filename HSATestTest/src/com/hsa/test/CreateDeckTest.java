package com.hsa.test;

import android.test.ActivityInstrumentationTestCase2;

import com.hsa.MainActivity;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;

import junit.framework.TestCase;

public class CreateDeckTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private DeckHandler deckHandler;
	
	public CreateDeckTest(){
		super(MainActivity.class);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		deckHandler = DeckHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
	}
	
	public void testError1(){
		
	}

}
