package com.hsa.handler;

import com.hsa.database.HSADatabaseHelper;

public class TrackHandler {

	private static TrackHandler trackHandler;
	
	private HSADatabaseHelper dbHelper;
	
	public static TrackHandler getInstance(HSADatabaseHelper dbHelper) {
		if(trackHandler == null)
			trackHandler = new TrackHandler(dbHelper);
		return trackHandler;
	}
	
	private TrackHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
}
