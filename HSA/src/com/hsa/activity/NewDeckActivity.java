package com.hsa.activity;

import com.hsa.R;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.fragment.SearchFragment;
import com.hsa.handler.SaveHandler;
import com.hsa.handler.ViewHandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NewDeckActivity extends ActionBarActivity{

	private RadioGroup radioClass;
    private RadioButton radioClassButton;
    private Button onClickConfirm;
    private String className;
    private ViewHandler viewHandler;
    private SaveHandler saveHandler;
    private SearchFragment searchFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_deck);
		Intent intent = getIntent();
//	    viewHandler = (ViewHandler) intent.getSerializableExtra("ViewHandler");
	    saveHandler = (SaveHandler) intent.getSerializableExtra("SaveHandler");
//	    searchFragment = (SearchFragment) intent.getSerializableExtra("SearchFragment");
//		className = null;
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
		//FIXME fare la creazione
//	    DeckDataAggregation dda = viewHandler.deckCreationRequest(editText.getText().toString(), className);
	    	
//	    if(dda != null){
//	    	Intent intent = new Intent(this, ModifyDeckActivity.class);
//	    	intent.putExtra("NewDeck", dda);
//	    	intent.putExtra("SearchHandler", getIntent().getSerializableExtra("SearchHandler"));
//	    	intent.putExtra("ViewHandler", viewHandler);
//	    	intent.putExtra("SaveHandler", getIntent().getSerializableExtra("SaveHandler"));
//		    startActivity(intent);
//	    }else{
//	    	AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
//
//            dlgAlert.setMessage("Error: Name already exist or name empty or class empty.");
//            dlgAlert.setTitle("Error Message...");
//            dlgAlert.setPositiveButton("OK", null);
//            dlgAlert.setCancelable(true);
//            dlgAlert.create().show();
//            
//            dlgAlert.setPositiveButton("Ok",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//            }
	    }
	    //fare i controlli
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
//	    startActivity(intent);

	
	public void addListenerOnButton() {
		 
		radioClass = (RadioGroup) findViewById(R.id.radioClass);
		onClickConfirm = (Button) findViewById(R.id.buttonConfirm);
	 
		onClickConfirm.setOnClickListener(new OnClickListener() {
	 
			@Override
			public void onClick(View v) {
	 
			    // get selected radio button from radioGroup
				int selectedId = radioClass.getCheckedRadioButtonId();
	 
				// find the radiobutton by returned id
				radioClassButton = (RadioButton) findViewById(selectedId);
	 
			}

		});
	 
	  }
}
