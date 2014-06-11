package com.hsa.test;

import com.hsa.MainActivity;
import com.hsa.R;
import com.hsa.activity.NewDeckActivity;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;
import com.hsa.handler.SaveHandler;
import com.hsa.handler.SearchHandler;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class CreateDeckTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private SearchHandler searchHandler;
	private MainActivity mainActivity;
	private HSADatabaseHelper dbHelper;
//	private SaveHandler saveHandler;
	private NewDeckActivity nda;
	private EditText et;
	
	public CreateDeckTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		nda = new NewDeckActivity();
		mainActivity = getActivity();
		et = (EditText) nda.findViewById(com.hsa.R.id.deck_name);
		searchHandler = SearchHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
//		saveHandler = SaveHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
	}

//	public void testError1(){
//		//Arrange
//		nda.runOnUiThread(
//			      new Runnable() {
//			        public void run() {
//			          et.requestFocus();
//			          et.setText("");
//			        } // end of run() method definition
//			      } // end of anonymous Runnable object instantiation
//			    ); // end of invocation of runOnUiThread
//		nda.setClassName("Priest");
//		
//		//Act
//		nda.onClickConfirm(null);
//
//		//Assume
//		assertNull(searchHandler.deckSearch(""));
//	}
//	
//	public void testError2(){
//		//Arrange
//		nda.runOnUiThread(
//			      new Runnable() {
//			        public void run() {
//			          et.requestFocus();
//			          et.setText("   ");
//			        } // end of run() method definition
//			      } // end of anonymous Runnable object instantiation
//			    ); // end of invocation of runOnUiThread
//		nda.setClassName("Priest");
//		
//		//Act
//		nda.onClickConfirm(null);
//
//		//Assume
//		assertNull(searchHandler.deckSearch("   "));
//	}
//	
//	public void testError3(){
//		//Arrange
//		nda.runOnUiThread(
//			      new Runnable() {
//			        public void run() {
//			          et.requestFocus();
//			          et.setText(" deck1");
//			        } // end of run() method definition
//			      } // end of anonymous Runnable object instantiation
//			    ); // end of invocation of runOnUiThread
//		nda.setClassName("Priest");
//		
//		//Act
//		nda.onClickConfirm(null);
//
//		//Assume
//		assertNull(searchHandler.deckSearch(" deck1"));
//	}
	
	public void testValid1(){
		//Arrange
		nda.runOnUiThread(
			      new Runnable() {
			        public void run() {
			          et.requestFocus();
			          et.setText("deck");
			        } // end of run() method definition
			      } // end of anonymous Runnable object instantiation
			    ); // end of invocation of runOnUiThread
		nda.setClassName("Priest");
		
		//Act
		nda.onClickConfirm(null);

		//Assume
		//assertNotNull(searchHandler.deckSearch("deck"));
	}
	
}
