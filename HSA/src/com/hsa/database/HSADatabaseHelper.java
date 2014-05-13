package com.hsa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HSADatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "hsa";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_CARD = "CARD";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_COST = "cost";
	public static final String COLUMN_RARITY = "rarity";
	public static final String COLUMN_EFFECT = "effect";
	public static final String COLUMN_CLASSNAME = "className";
	public static final String COLUMN_ATTACK = "attack";
	public static final String COLUMN_HEALTH = "health";
	public static final String COLUMN_DURABILITY = "durability";
	public static final String COLUMN_RACE = "race";
	public static final String COLUMN_PATH = "path";
	

	public HSADatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
