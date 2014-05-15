package com.hsa.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.hsa.bean.Card;
import com.hsa.bean.SearchCriterion;
import com.hsa.contract.CardEntry;
import com.hsa.database.HSADatabaseHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SearchManager {

	private HSADatabaseHelper dbHelper;
	
	public SearchManager(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public List<Card> search(SearchCriterion searchCriterion) {
		String sql = "SELECT * FROM CARD";
		List<String> sqlArgs = new ArrayList<String>();
//=====================================================VERSIONE2=======================================================
		boolean first = true;
		if(searchCriterion!=null){
			if(searchCriterion.getFilters()!=null){
				for (Entry<String, ArrayList<String>> entry : searchCriterion.getFilters().entrySet()){
					if (first){
						sql = sql + " WHERE";
						first = false;
						if(entry.getValue().size()>1){
							sql = sql + " (";
							boolean first1 = true;
							for (String entry2 : entry.getValue()){
								if (first1){
									first1 = false;
									sql = sql + entry.getKey() + " = ?";// + entry2;
									sqlArgs.add(entry2);
								}else{
									sql = sql + " OR " + entry.getKey() + " = ?";// + entry2;
									sqlArgs.add(entry2);
								}
							}
							sql = sql + ")";
						}else{
							sql = sql + " " + entry.getKey() + " = ?";// + entry.getValue().get(0);
							sqlArgs.add(entry.getValue().get(0));
						}
					}else{
						sql = sql + " AND";
						if(entry.getValue().size()>1){
							sql = sql + " (";
							boolean first1 = true;
							for (String entry2 : entry.getValue()){
								if (first1){
									first1 = false;
									sql = sql + entry.getKey() + " = ?";// + entry2;
									sqlArgs.add(entry2);
								}else{
									sql = sql + " OR " + entry.getKey() + " = ?";// + entry2;
									sqlArgs.add(entry2);							}
							}
							sql = sql + ")";
						}else{
							sql = sql + " " + entry.getKey() + " = ?";// + entry.getValue().get(0);
							sqlArgs.add(entry.getValue().get(0));					}
					}
				}
			}
		}
// =============================================VERSIONE 1===============================================
//				if (i == 0){
//					sql = sql + " WHERE " +  entry.getKey() + " = ? ";
//					sqlArgs.add(entry.getValue());
//					i++;
//				}else{
//					sql = sql + "AND " + entry.getKey() + " = ? ";
//					sqlArgs.add(entry.getValue());
//				}
//			}
//		if(searchCriterion.getFilters()!=null){
//			for (Map.Entry<String, String> entry : searchCriterion.getFilters().entrySet()){
//				if (i == 0){
//					sql = sql + " WHERE " +  entry.getKey() + " = ? ";
//					sqlArgs.add(entry.getValue());
//					i++;
//				}else{
//					sql = sql + "AND " + entry.getKey() + " = ? ";
//					sqlArgs.add(entry.getValue());
//				}
//			}
//			if (searchCriterion.getName() != null) {
//				sql = sql + "AND name LIKE ? ;";
//				sqlArgs.add("'%"+searchCriterion.getName()+"%'");
//			}
//		}else{
//			if (searchCriterion.getName() != null) {
//				sql = sql + " WHERE name LIKE ?;";
//				sqlArgs.add("'%"+searchCriterion.getName()+"%'");
//			}
//		}
		
		String [] selectionArgs = new String[sqlArgs.size()];
		sqlArgs.toArray(selectionArgs);
		List<Card> cards = new ArrayList<Card>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
//		Cursor cursor = db.query(CardEntry.TABLE_NAME,
//				cardProjection, null, null, null, null, null);
		
//		Cursor cursor = db.query(CardEntry.TABLE_NAME,
//		null, "class = ?", new String[]{"Hunter"}, null, null, null);

		Cursor cursor = db.rawQuery(sql, selectionArgs);
		
		if(cursor.moveToFirst()) {
			do {
				Card card = cursorToCard(cursor);
				cards.add(card);
			}while(cursor.moveToNext());
		}

		cursor.close();
		return cards;
	}
	
	private Card cursorToCard(Cursor cursor) {
	    Card card = new Card();
	    card.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_ENTRY_ID))));
	   	card.setName(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_NAME)));
	   	card.setType(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_TYPE)));
	    if (controlNull(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_COST)))){
	    	card.setCost(null);
	    }else{
	    	card.setCost(Integer.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_COST)))));
	    }
	    card.setRarity(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_RARITY)));
	    card.setEffect(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_EFFECT)));
	    card.setClassName(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_CLASS)));	    
	    if(controlNull(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_ATTACK)))){
	    	card.setAttack(null);
	    }else{
	    	card.setAttack(Integer.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_ATTACK)))));
	    }    
	    if(controlNull(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_HEALTH)))){
	    	card.setHealth(null);
	    }else{
	    	card.setHealth(Integer.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_HEALTH)))));
	    }	    
	    if(controlNull(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_DURABILITY)))){
	    	card.setDurability(null);
	    }else{
	    	card.setDurability(Integer.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_DURABILITY)))));
	    }  
	    card.setRace(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_RACE)));
	    card.setPath(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_NAME_PATH)));
	    return card;
	}
	
	private boolean controlNull(String s){
		if(s == null){
			return true;
		}else{
			return false;
		}
	}
}
