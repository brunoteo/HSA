package com.hsa.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hsa.MainActivity;
import com.hsa.contract.DeckEntry;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.SaveHandler;
import com.hsa.handler.SearchHandler;
import com.robotium.solo.Solo;

import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.RadioButton;

public class CreateDeckTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;
	private SearchHandler searchHandler;
	private SaveHandler saveHandler;
	
	public CreateDeckTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnButton("New Deck");
		searchHandler = SearchHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
		saveHandler = SaveHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
	}
	
	protected void tearDown() throws Exception {
		SQLiteDatabase db = HSADatabaseHelper.getInstance(getActivity()).getWritableDatabase();
//		String[] args = new String[]{"deckValid1", "deck Error 7"};
		db.delete(DeckEntry.TABLE_NAME, null, null);
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

		solo.sleep(5000);
		//Assume
		assertNull(searchHandler.deckSearch(""));
//		assertTrue("Name already exist or is unvalid.", solo.searchText("WARNING"));
	}
	
	public void testError2() {
		//Arrange
		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
		solo.clickOnView(rb);
		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
		solo.clearEditText(et);
		solo.enterText(et, "   ");
		
		//Act
		solo.clickOnButton("Create");
		solo.sleep(5000);
		//Assume
		assertNull(searchHandler.deckSearch("   "));
//		assertTrue("Name already exist or is unvalid.", solo.searchText("WARNING"));
	}
	
	public void testError3() {
		//Arrange
		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
		solo.clickOnView(rb);
		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
		solo.clearEditText(et);
		solo.enterText(et, " deckError3");
		
		//Act
		solo.clickOnButton("Create");
		solo.sleep(5000);
		//Assume
		assertNull(searchHandler.deckSearch(" deckError3"));
//		assertTrue("Name already exist or is unvalid.", solo.searchText("WARNING"));
	}
	
	public void testError4() {
		//Arrange
		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
		solo.clickOnView(rb);
		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
		solo.clearEditText(et);
		solo.enterText(et, "deckError4 ");
		
		//Act
		solo.clickOnButton("Create");
		solo.sleep(5000);
		//Assume
		assertNull(searchHandler.deckSearch("deckError4 "));
//		assertTrue("Name already exist or is unvalid.", solo.searchText("WARNING"));

	}
	
	public void testError5() {
		//Arrange
		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
		solo.clickOnView(rb);
		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
		solo.clearEditText(et);
		solo.enterText(et, "deck   Error5");
		
		//Act
		solo.clickOnButton("Create");
		solo.sleep(5000);
		//Assume
		assertNull(searchHandler.deckSearch("deck   Error5"));
//		assertTrue("Name already exist or is unvalid.", solo.searchText("WARNING"));

	}
	
	public void testError6() {
		//Arrange
//		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
//		solo.clickOnView(rb);
		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
		solo.clearEditText(et);
		solo.enterText(et, "deck Error 6");
		
		//Act
		solo.clickOnButton("Create");
		solo.sleep(5000);
		//Assume
		assertNull(searchHandler.deckSearch("deck Error 6"));
//		assertTrue("Class empty.", solo.searchText("WARNING"));

	}
	
	public void testError7() throws ParseException {
		//Arrange
		saveHandler.createDeck("deck Error 7", "Warrior");
		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
		solo.clickOnView(rb);
		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
		solo.clearEditText(et);
		solo.enterText(et, "deck Error 7");
		
		//date boundary
		solo.sleep(1000);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString1 = s.format(new Date());
		Date date1 = s.parse(dateString1);
		solo.sleep(1000);
		
		//Act
		solo.clickOnButton("Create");
		solo.sleep(5000);
		String dateString2 = searchHandler.deckSearch("deck Error 7").getDate();
		Date date2 = s.parse(dateString2);
		//Assume
		assertTrue(date2.compareTo(date1)<0);
//		assertNull(searchHandler.deckSearch("deck Error 7"));
//		assertTrue("Name already exist or is unvalid.", solo.searchText("WARNING"));
	}
	
	public void testSingol1() {
		//Arrange
		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
		solo.clickOnView(rb);
		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
		solo.clearEditText(et);
		solo.enterText(et, "Z");

		//Act
		solo.clickOnButton("Create");
		solo.sleep(5000);

		//Assume
		assertNotNull(searchHandler.deckSearch("Z"));
		
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
		solo.sleep(5000);

		//Assume
		assertNotNull(searchHandler.deckSearch("deckValid1"));
		
	}
	
	public void testValid2() {
		//Arrange
		RadioButton rb = (RadioButton) solo.getView(com.hsa.R.id.radio_priest);
		solo.clickOnView(rb);
		EditText et = (EditText) solo.getView(com.hsa.R.id.deck_name);
		solo.clearEditText(et);
		solo.enterText(et, "deck Valid 2");

		//Act
		solo.clickOnButton("Create");
		solo.sleep(5000);

		//Assume
		assertNotNull(searchHandler.deckSearch("deck Valid 2"));
		
	}
}
