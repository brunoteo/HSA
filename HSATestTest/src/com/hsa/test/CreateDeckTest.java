package com.hsa.test;

import com.hsa.activity.NewDeckActivity;
import com.hsa.contract.DeckEntry;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.SearchHandler;
import com.robotium.solo.Solo;

import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.EditText;
import android.widget.RadioButton;

public class CreateDeckTest extends ActivityInstrumentationTestCase2<NewDeckActivity> {
	
	private Solo solo;
	private SearchHandler searchHandler;
	
	public CreateDeckTest() {
		super(NewDeckActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		searchHandler = SearchHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		
//		solo.finishOpenedActivities();
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
	
//	public void testValid1() {
//		//Arrange
//		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
//		solo.clickOnView(rb);
//		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
//		solo.clearEditText(et);
//		solo.enterText(et, "deckValid1");
//		
//		//Act
//		solo.clickOnButton("Create");
//
//		//Assume
//		assertNotNull(searchHandler.deckSearch("deckValid1"));	
//	}
}
