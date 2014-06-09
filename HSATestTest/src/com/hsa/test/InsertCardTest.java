package com.hsa.test;

import com.hsa.activity.DeckActivity;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;

import android.test.ActivityInstrumentationTestCase2;

public class InsertCardTest extends
		ActivityInstrumentationTestCase2<DeckActivity> {
	
	private DeckHandler deckHandler;

	public InsertCardTest(Class<DeckActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception {
		super.setUp();
		deckHandler = DeckHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
	}
	
	public void testDio() {
		if(deckHandler.getCopyFormations().size() == 1) {
			assertTrue(true);
			
		}
	}

}
