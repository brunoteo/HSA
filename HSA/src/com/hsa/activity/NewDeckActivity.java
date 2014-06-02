package com.hsa.activity;

import com.hsa.R;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.SaveHandler;
import com.hsa.handler.SearchHandler;
import com.hsa.handler.ViewHandler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewDeckActivity extends ActionBarActivity{

	private Button onClickConfirm;
    private String className;
    
    private HSADatabaseHelper dbHelper;
    private SearchHandler searchHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_deck);
		dbHelper = HSADatabaseHelper.getInstance(this);
	    ViewHandler.getInstance(dbHelper);
	    SaveHandler.getInstance(dbHelper);
	    searchHandler = SearchHandler.getInstance(dbHelper);
	}
	
	public void onRadioButtonClicked(View view){
		// Check which radio button was clicked
	    switch(view.getId()) {
		    case R.id.radio_druid:
	            className="Druid";
	            break;
	        case R.id.radio_hunter:
	            className="Hunter";
	            break;
	        case R.id.radio_mage:
	            className="Mage";
	            break;
	        case R.id.radio_paladin:
	            className="Paladin";
	            break;
	        case R.id.radio_priest:
	            className="Priest";
	            break;
	        case R.id.radio_rogue:
	            className="Rogue";
	            break;
	        case R.id.radio_shaman:
	            className="Shaman";
	            break;
	        case R.id.radio_warlock:
	            className="Warlock";
	            break;
	        case R.id.radio_warrior:
	            className="Warrior";
	            break;
	    }
	}
	
	public void onClickConfirm(View view){
		EditText editText = (EditText) findViewById(R.id.deck_name);
		if(classCheck()){
			boolean nameRight = searchHandler.nameCheck(editText.getText().toString());
			if(nameRight){
				
//		    	Deck deck = SaveHandler.getInstance(dbHelper).createDeck(editText.getText().toString(), className);
//		    	DeckDataAggregation dda = viewHandler.createDeckDataAggregation(deck);
		    	DeckDataAggregation dda = SaveHandler.getInstance(dbHelper).createDeck(editText.getText().toString(), className);
		    	Intent returnIntent = new Intent();
		    	returnIntent.putExtra("deckDataAggregation", dda);
		    	setResult(RESULT_OK,returnIntent);
		    	finish();
			    
			}else{
				AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
				
	            dlgAlert.setMessage("Name already exist or is void.");
	            dlgAlert.setTitle("WARNING");
	            dlgAlert.setPositiveButton("OK", null);
	            dlgAlert.create().show();
	            
	            dlgAlert.setPositiveButton("Ok",
	                    new DialogInterface.OnClickListener() {
	                        public void onClick(DialogInterface dialog, int which) {

	                        }
	                    });
			}		    	
		}else{
			AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
			
            dlgAlert.setMessage("Class empty.");
            dlgAlert.setTitle("Warning");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.create().show();
            
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
		
	}

	
	public void addListenerOnButton() {
	
		onClickConfirm = (Button) findViewById(R.id.buttonConfirm);
	 
		onClickConfirm.setOnClickListener(new OnClickListener() {
	 
			@Override
			public void onClick(View v) {
	 
			}

		});
	 
	  }
	
	public boolean classCheck(){
		if(className != null){
			return true;
		}
		return false;
	}
}
