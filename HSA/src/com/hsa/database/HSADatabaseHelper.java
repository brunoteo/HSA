package com.hsa.database;

import java.io.Serializable;

import com.hsa.contract.CardEntry;
import com.hsa.contract.DeckEntry;
import com.hsa.contract.FormationEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HSADatabaseHelper extends SQLiteOpenHelper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "hsa.db";

	public HSADatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
        db.execSQL(CardEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DeckEntry.SQL_CREATE_ENTRIES);
        db.execSQL(FormationEntry.SQL_CREATE_ENTRIES);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		
	}
	
}
