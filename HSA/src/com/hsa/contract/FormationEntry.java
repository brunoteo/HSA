package com.hsa.contract;

import android.provider.BaseColumns;

public abstract class FormationEntry implements BaseColumns{
	
	public static final String TABLE_NAME = "FORMATION";
	public static final String COLUMN_NAME_CARD = "card";
	public static final String COLUMN_NAME_DECK = "deck";
	public static final String COLUMN_NAME_OCCURRENCE = "occurrence";

	
	private static final String TEXT_TYPE = " TEXT";
	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	
	public static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + FormationEntry.TABLE_NAME + "(" +
	    		FormationEntry.COLUMN_NAME_CARD + TEXT_TYPE + " REFERENCES " + CardEntry.TABLE_NAME + "(" + CardEntry.COLUMN_NAME_NAME  + ")" + COMMA_SEP +
	    		FormationEntry.COLUMN_NAME_DECK + TEXT_TYPE + " REFERENCES " + DeckEntry.TABLE_NAME + "(" + DeckEntry.COLUMN_NAME_NAME + ")" + " ON UPDATE CASCADE" + COMMA_SEP +
	    		FormationEntry.COLUMN_NAME_OCCURRENCE + TEXT_TYPE + COMMA_SEP +
	    		"PRIMARY KEY (" + FormationEntry.COLUMN_NAME_CARD + COMMA_SEP + " " + FormationEntry.COLUMN_NAME_DECK + ")" + 
	    		")";
	
}