package com.hsa.test;

import com.hsa.MainActivity;
import com.hsa.activity.DeckActivity;
import com.hsa.activity.NewDeckActivity;
import com.hsa.contract.DeckEntry;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.SearchHandler;
import com.robotium.solo.Solo;

import android.app.Instrumentation.ActivityMonitor;
import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.EditText;
import android.widget.RadioButton;

public class CreateDeckTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;
	
	public CreateDeckTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnButton("New Deck");
	}
	
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();	
	}
	
	public void testError1() {
		//Arrange
		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
		solo.clickOnView(rb);
		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
		solo.clearEditText(et);
		solo.enterText(et, "");
		
		//Act
		solo.clickOnButton("Create");
		
		//Assume
		assertTrue("Name already exist or is void.", solo.searchText("WARNING"));
	}
	
	public void testValid1() {
		//Arrange
		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
		solo.clickOnView(rb);
		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
		solo.clearEditText(et);
		solo.enterText(et, "deckValid1");
		
		//Act
		solo.clickOnButton("Create");
		
//		solo.scrollViewToSide(solo.getView("pager2"), Solo.RIGHT);
		//Assume
		assertTrue(solo.waitForActivity(DeckActivity.class));
//		solo.assertCurrentActivity("string", DeckActivity.class.getName());
	}
}
