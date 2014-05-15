package com.hsa.contract;

import android.provider.BaseColumns;

public abstract class DeckEntry implements BaseColumns{
	
	public static final String TABLE_NAME = "DECK";
	public static final String COLUMN_NAME_ENTRY_ID = "_id";
	public static final String COLUMN_NAME_NAME = "name";
	public static final String COLUMN_NAME_CLASS = "class";
	public static final String COLUMN_NAME_NOTE = "note";
	public static final String COLUMN_NAME_DATE = "date";
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	
	public static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + DeckEntry.TABLE_NAME + "(" +
	    		DeckEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
	    		DeckEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    		DeckEntry.COLUMN_NAME_CLASS + TEXT_TYPE + COMMA_SEP +
	    		DeckEntry.COLUMN_NAME_NOTE + INT_TYPE + COMMA_SEP +
	    		DeckEntry.COLUMN_NAME_DATE + TEXT_TYPE + 
	    		")";
	
}
