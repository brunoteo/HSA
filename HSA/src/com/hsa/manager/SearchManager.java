package com.hsa.manager;

import java.util.ArrayList;
import java.util.List;

import com.hsa.bean.Card;
import com.hsa.database.HSADatabaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SearchManager {

	private SQLiteDatabase database;
	private HSADatabaseHelper dbHelper;
	private String[] allColumns = {	HSADatabaseHelper.COLUMN_NAME, 
									HSADatabaseHelper.COLUMN_TYPE, 
									HSADatabaseHelper.COLUMN_COST, 
									HSADatabaseHelper.COLUMN_RARITY, 
									HSADatabaseHelper.COLUMN_EFFECT, 
									HSADatabaseHelper.COLUMN_CLASSNAME, 
									HSADatabaseHelper.COLUMN_ATTACK, 
									HSADatabaseHelper.COLUMN_HEALTH, 
									HSADatabaseHelper.COLUMN_DURABILITY, 
									HSADatabaseHelper.COLUMN_RACE, 
									HSADatabaseHelper.COLUMN_PATH};
	
	public SearchManager(Context context) {
		dbHelper = new HSADatabaseHelper(context);
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
	    dbHelper.close();
	}
	
	public void ricerca() {
		List<Card> cards = new ArrayList<Card>();
		
		Cursor cursor = database.query(HSADatabaseHelper.TABLE_CARD,
		        allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Card card = cursorToCard(cursor);
			cards.add(card);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
	}
	
	private Card cursorToCard(Cursor cursor) {
	    Card card = new Card();
	    card.setName(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_NAME)));
	    card.setType(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_TYPE)));
	    card.setCost(Integer.parseInt(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_COST))));
	    card.setRarity(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_RARITY)));
	    card.setEffect(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_EFFECT)));
	    card.setClassName(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_CLASSNAME)));
	    card.setAttack(Integer.parseInt(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_ATTACK))));
	    card.setHealth(Integer.parseInt(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_HEALTH))));
	    card.setDurability(Integer.parseInt(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_DURABILITY))));
	    card.setRace(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_RACE)));
	    card.setPath(cursor.getString(cursor.getColumnIndex(HSADatabaseHelper.COLUMN_PATH)));
	    return card;
	}
	
}
