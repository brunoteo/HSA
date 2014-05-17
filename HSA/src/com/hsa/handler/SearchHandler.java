package com.hsa.handler;

import java.io.Serializable;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import com.hsa.MainActivity;
import com.hsa.bean.Card;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
import com.hsa.bean.SearchCriterion;
import com.hsa.contract.CardEntry;
import com.hsa.contract.DeckEntry;
import com.hsa.contract.FormationEntry;
import com.hsa.database.HSADatabaseHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SearchHandler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HSADatabaseHelper dbHelper;
	private SaveHandler saveHandler;
	private ViewHandler viewHandler;
	
	public ViewHandler getViewHandler() {
		return viewHandler;
	}

	public void setViewHandler(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
	}

	public SaveHandler getSaveHandler() {
		return saveHandler;
	}

	public void setSaveHandler(SaveHandler saveHandler) {
		this.saveHandler = saveHandler;
	}

	public SearchHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
		saveHandler = null;
		viewHandler = null;
	}
	
	public Card cardRetrievalRequest(String name) {
		SearchCriterion criterion = new SearchCriterion(name, null);
		Card card = search(criterion).get(0);
		return card;
	}
	
	public List<Card> search(SearchCriterion searchCriterion) {
		String sql = "SELECT * FROM " + CardEntry.TABLE_NAME;
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
									sql = sql + entry.getKey() + " = ?";
									sqlArgs.add(entry2);
								}else{
									sql = sql + " OR " + entry.getKey() + " = ?";
									sqlArgs.add(entry2);
								}
							}
							sql = sql + ")";
						}else{
							sql = sql + " " + entry.getKey() + " = ?";
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
									sql = sql + entry.getKey() + " = ?";
									sqlArgs.add(entry2);
								}else{
									sql = sql + " OR " + entry.getKey() + " = ?";
									sqlArgs.add(entry2);							}
							}
							sql = sql + ")";
						}else{
							sql = sql + " " + entry.getKey() + " = ?";
							sqlArgs.add(entry.getValue().get(0));					}
					}
				}
			}
			if(searchCriterion.getName()!=null){
				if (first){
					sql = sql + " WHERE " + CardEntry.COLUMN_NAME_NAME + " LIKE ?";
				}else{
					sql = sql + " AND " + CardEntry.COLUMN_NAME_NAME + " LIKE ?";
				}
				sqlArgs.add("%"+searchCriterion.getName()+"%");
			}
		}
		sql = sql + " ORDER BY " + CardEntry.COLUMN_NAME_COST + ", " + CardEntry.COLUMN_NAME_NAME;
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

	public List<Deck> decksRequest() {
		String sql = "SELECT * FROM " + DeckEntry.TABLE_NAME;
		List<Deck> decks = new ArrayList<Deck>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		if(cursor.moveToFirst()) {
			do {
				Deck deck = cursorToDeck(cursor);
				decks.add(deck);
			}while(cursor.moveToNext());
		}

		cursor.close();
		return decks;
	}
	
	private Deck cursorToDeck(Cursor cursor) {
	    Deck deck = new Deck();
	    deck.setName(cursor.getString(cursor.getColumnIndex(DeckEntry.COLUMN_NAME_NAME)));
	    deck.setClassName(cursor.getString(cursor.getColumnIndex(DeckEntry.COLUMN_NAME_CLASS)));
	    deck.setNote(cursor.getString(cursor.getColumnIndex(DeckEntry.COLUMN_NAME_NOTE)));
	    deck.setDate(cursor.getString(cursor.getColumnIndex(DeckEntry.COLUMN_NAME_DATE)));
	    
	    return deck;
	}

	public List<Formation> formationsRequest(String deckName) {
		String sql = "SELECT * FROM " + FormationEntry.TABLE_NAME + " WHERE deck = ?";
		List<Formation> formations = new ArrayList<Formation>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String [] selectionArgs = new String[]{deckName};
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		if(cursor.moveToFirst()) {
			do {
				Formation formation = cursorToFormation(cursor);
				formations.add(formation);
			}while(cursor.moveToNext());
		}

		cursor.close();
		return formations;
	}

	private Formation cursorToFormation(Cursor cursor) {
	    Formation formation = new Formation();
	    formation.setCard(cursor.getString(cursor.getColumnIndex(FormationEntry.COLUMN_NAME_CARD)));
	    formation.setDeck(cursor.getString(cursor.getColumnIndex(FormationEntry.COLUMN_NAME_DECK)));
	    if(controlNull(cursor.getString(cursor.getColumnIndex(FormationEntry.COLUMN_NAME_OCCURRENCE)))){
	    	formation.setOccurrence(null);
	    }else{
	    	formation.setOccurrence(Integer.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FormationEntry.COLUMN_NAME_OCCURRENCE)))));
	    }  
	    return formation;
	}

	public Deck dataCheck(String name, String className) {
		
		if(className != null){
			List<Deck> decks = decksRequest();
			for (Deck deck : decks){
				if(deck.getName()==name) return null;
			}
			Deck deck = saveHandler.createNewDeck(name, className);
			return deck;
		}
		return null;
	}
}
