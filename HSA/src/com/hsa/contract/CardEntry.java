package com.hsa.contract;

import android.provider.BaseColumns;

public abstract class CardEntry implements BaseColumns{
	public static final String TABLE_NAME = "CARD";
//	public static final String COLUMN_NAME_ENTRY_ID = "_id";
	public static final String COLUMN_NAME_NAME = "name";
	public static final String COLUMN_NAME_TYPE = "type";
	public static final String COLUMN_NAME_COST = "cost";
	public static final String COLUMN_NAME_RARITY = "rarity";
	public static final String COLUMN_NAME_EFFECT = "effect";
	public static final String COLUMN_NAME_CLASS = "class";
	public static final String COLUMN_NAME_ATTACK = "attack";
	public static final String COLUMN_NAME_HEALTH = "health";
	public static final String COLUMN_NAME_DURABILITY = "durability";
	public static final String COLUMN_NAME_RACE = "race";
	public static final String COLUMN_NAME_PATH = "path";
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	public static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + CardEntry.TABLE_NAME + "(" +
//	    		CardEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
	    		CardEntry.COLUMN_NAME_NAME + TEXT_TYPE + " PRIMARY KEY" + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_COST + INT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_RARITY + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_EFFECT + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_CLASS + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_ATTACK + INT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_HEALTH + INT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_DURABILITY + INT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_RACE + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_PATH + TEXT_TYPE + 
	    		")";
	
}

