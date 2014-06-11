package com.hsa.test;

import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.CheckBox;

import com.hsa.MainActivity;
import com.hsa.activity.DeckActivity;
import com.hsa.activity.NewDeckActivity;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.SaveHandler;
import com.robotium.solo.Solo;


public class ViewDeckCardsTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	private SaveHandler saveHandler;
	
	public ViewDeckCardsTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		saveHandler = SaveHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
		saveHandler.createDeck("MyDruid", "Druid");

	}
	
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();	
	}
	
	public void testValid1() {
		//Arrange

		DeckDataAggregation dda = new DeckDataAggregation();
		dda.setName("MyDruid");
		dda.setClassName("Druid");
		dda.setCardNumber(0);
		dda.setDate(null);
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor("DeckActivity", null, false);
		getActivity().onDeckSelected(dda);
		DeckActivity da = (DeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
		
		
		//Act
		solo.clickOnView(da.findViewById(com.hsa.R.id.filterDeck));
//		solo.clickOnActionBarItem(com.hsa.R.id.filterDeck);

		
		//Assume
		CheckBox chkClass = (CheckBox) solo.getView(com.hsa.R.id.chkDruid);
		CheckBox chkNeutral = (CheckBox) solo.getView(com.hsa.R.id.chkNeutral);
		assertTrue(chkClass.isChecked());
		assertTrue(chkNeutral.isChecked());
	}

}
