package com.hsa.test;

import com.hsa.MainActivity;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;

import android.test.ActivityInstrumentationTestCase2;

public class InsertCardTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private DeckHandler deckHandler;
	
	public InsertCardTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		deckHandler = DeckHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
	}
	
	public void test1() {
		assertTrue(deckHandler!=null);
	}
	
	public void test2() {
		assertTrue(deckHandler.getCopyFormations()!=null);
	}

}
