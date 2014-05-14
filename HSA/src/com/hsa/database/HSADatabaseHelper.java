package com.hsa.database;

import com.hsa.contract.CardEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HSADatabaseHelper extends SQLiteOpenHelper {
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + CardEntry.TABLE_NAME + " (" +
	    		CardEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY," +
	    		CardEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_COST + INT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_RARITY + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_EFFECT + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_CLASS + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_ATTACK + INT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_HEALTH + INT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_DURABILITY + INT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_RACE + TEXT_TYPE + COMMA_SEP +
	    		CardEntry.COLUMN_NAME_PATH + TEXT_TYPE + COMMA_SEP +
	    		" )";
	
	public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "hsa.db";

	public HSADatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
