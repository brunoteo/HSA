package com.hsa.database;

import com.hsa.contract.CardEntry;
import com.hsa.manager.SaveManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HSADatabaseHelper extends SQLiteOpenHelper {
	
	public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "hsa.db";
    private Context context;

	public HSADatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	public void onCreate(SQLiteDatabase db) {
        db.execSQL(CardEntry.SQL_CREATE_ENTRIES);    
        SaveManager saveManager = new SaveManager(context);
        saveManager.fillDB();
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
