package com.hsa.test;

import com.hsa.MainActivity;
import com.hsa.activity.NewDeckActivity;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.SearchHandler;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class NewDeckTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private SearchHandler searchHandler;
	private NewDeckActivity nda;
	private EditText et;
	private Button btn;
	private RadioButton rb;
	
	public NewDeckTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		searchHandler = SearchHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
		
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(NewDeckActivity.class.getName(), null, false);

		MainActivity mainActivity = getActivity();
		mainActivity.onClickND(null);
		
		nda = (NewDeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
		btn = (Button) nda.findViewById(com.hsa.R.id.buttonConfirm);
		rb = (RadioButton)nda.findViewById(com.hsa.R.id.radio_priest);
		et = (EditText) nda.findViewById(com.hsa.R.id.deck_name);
		 
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testValid1(){
		//Arrange
		nda.runOnUiThread(
			      new Runnable() {
			        public void run() {
			          et.requestFocus();
			          et.setText("deck");
			          rb.requestFocus();
			          rb.performClick();
			          btn.requestFocus();
			          btn.performClick();
			        } // end of run() method definition
			      } // end of anonymous Runnable object instantiation
			    ); // end of invocation of runOnUiThread
		nda.setClassName("Priest");
		
		//Act
//		nda.onClickConfirm(null);

		//Assume
//		assertNotNull(searchHandler.deckSearch("deck"));
	}

}
