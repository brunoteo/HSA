package com.hsa.activity;

import com.hsa.MainActivity;
import com.hsa.R;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.ViewHandler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewDeckActivity extends ActionBarActivity {

	private RadioGroup radioClass;
    private RadioButton radioClassButton;
    private Button onClickConfirm;
    private String className;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_deck);
		className = null;
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
	    @SuppressWarnings("unused")
		EditText editText = (EditText) findViewById(R.id.deck_name);
	    HSADatabaseHelper dbh = new HSADatabaseHelper(this);
	    ViewHandler viewHandler = new ViewHandler(dbh);
	    DeckDataAggregation dda = viewHandler.deckCreationRequest(editText.getText().toString(), className);
	    	
	    if(dda != null){
	    	Intent intent = new Intent(this, ModifyDeckActivity.class);
	    	intent.putExtra("NewDeck", dda);
		    startActivity(intent);
	    }else{
	    	
	    }
	    //fare i controlli
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
//	    startActivity(intent);
	}
	
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
