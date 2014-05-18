package com.hsa.handler;

import com.hsa.database.HSADatabaseHelper;

public class DeckHandler {
	
	private static DeckHandler deckHandler;
	
	private HSADatabaseHelper dbHelper;
	
	public static DeckHandler getInstance(HSADatabaseHelper dbHelper) {
		if(deckHandler == null)
			deckHandler = new DeckHandler(dbHelper);
		return deckHandler;
	}
	
	private DeckHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

}
