package com.hsa.manager;

import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hsa.bean.Card;
import com.hsa.contract.CardEntry;
import com.hsa.database.HSADatabaseHelper;

public class SaveManager {

	private HSADatabaseHelper dbHelper;
	
	private final Card card1 = new Card("Goldshire Footman", "Minion", 1, "Basic", "Taunt.", "Neutral", 1, 2, null, "", "");
	private final Card card2 = new Card("Inner Fire", "Spell", 1, "Common", "Change a minion's Attack to be equal to its Health.", "Priest", null, null, null, null, null);
	
	private List<Card> cards = Arrays.asList(card1, card2);
	
	
	public SaveManager(Context context) {
		dbHelper = new HSADatabaseHelper(context);
	}
	
	public void fillDB(){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		for (int i=0; i<cards.size(); i++){
			
			ContentValues values = new ContentValues();
			//values.put(CardEntry.COLUMN_NAME_ENTRY_ID, cards.get(i).get_id());
			values.put(CardEntry.COLUMN_NAME_NAME, cards.get(i).getName());
			values.put(CardEntry.COLUMN_NAME_TYPE, cards.get(i).getType());
			values.put(CardEntry.COLUMN_NAME_COST, cards.get(i).getCost());
			values.put(CardEntry.COLUMN_NAME_RARITY, cards.get(i).getRarity());
			values.put(CardEntry.COLUMN_NAME_EFFECT, cards.get(i).getEffect());
			values.put(CardEntry.COLUMN_NAME_CLASS, cards.get(i).getClassName());
			values.put(CardEntry.COLUMN_NAME_ATTACK, cards.get(i).getAttack());
			values.put(CardEntry.COLUMN_NAME_HEALTH, cards.get(i).getHealth());
			values.put(CardEntry.COLUMN_NAME_DURABILITY, cards.get(i).getDurability());
			values.put(CardEntry.COLUMN_NAME_RACE, cards.get(i).getRace());
			values.put(CardEntry.COLUMN_NAME_PATH, cards.get(i).getPath());
			
			//db.insert(CardEntry.TABLE_NAME, null, values);
			db.close();
		}
		
	}
	
}
