package com.hsa.manager;

import java.util.ArrayList;
import java.util.List;

import com.hsa.bean.Card;
import com.hsa.bean.SearchCriterion;
import com.hsa.contract.CardEntry;
import com.hsa.database.HSADatabaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SearchManager {

	private HSADatabaseHelper dbHelper;
	private String[] cardProjection = {	
			CardEntry.COLUMN_NAME_ENTRY_ID,
			CardEntry.COLUMN_NAME_NAME, 
			CardEntry.COLUMN_NAME_TYPE, 
			CardEntry.COLUMN_NAME_COST, 
			CardEntry.COLUMN_NAME_RARITY, 
			CardEntry.COLUMN_NAME_EFFECT, 
			CardEntry.COLUMN_NAME_CLASS, 
			CardEntry.COLUMN_NAME_ATTACK, 
			CardEntry.COLUMN_NAME_HEALTH, 
			CardEntry.COLUMN_NAME_DURABILITY, 
			CardEntry.COLUMN_NAME_RACE, 
			CardEntry.COLUMN_NAME_PATH};
	
	public SearchManager(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public List<Card> search(SearchCriterion searchCriterion) {
		List<Card> cards = new ArrayList<Card>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query(CardEntry.TABLE_NAME,
				cardProjection, null, null, null, null, null);

		if(cursor.moveToFirst()) {
			do {
				Card card = cursorToCard(cursor);
				cards.add(card);
			}while(cursor.moveToNext());
		}

		cursor.close();
		return cards;
	}
	
	private Card cursorToCard(Cursor cursor) {
	    Card card = new Card();
	    card.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_ENTRY_ID))));
	    card.setName(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_NAME)));
//	    card.setType(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_TYPE)));
//	    card.setCost(Integer.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_COST)))));
//	    card.setRarity(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_RARITY)));
//	    card.setEffect(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_EFFECT)));
//	    card.setClassName(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_CLASS)));
//	    card.setAttack(Integer.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_ATTACK)))));
//	    card.setHealth(Integer.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_HEALTH)))));
//	    card.setDurability(Integer.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_DURABILITY)))));
//	    card.setRace(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_RACE)));
	    card.setPath(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_PATH)));
	    return card;
	}
	
}
