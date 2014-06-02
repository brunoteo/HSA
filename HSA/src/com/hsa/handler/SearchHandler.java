package com.hsa.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hsa.aggregation.CompleteTextualAggregation;
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

public class SearchHandler{

	/**
	 * 
	 */	
	private static SearchHandler searchInstance;
	
	private HSADatabaseHelper dbHelper;
	
	public static SearchHandler getInstance(HSADatabaseHelper dbHelper) {
		if(searchInstance == null)
			searchInstance = new SearchHandler(dbHelper);
		return searchInstance;
	}
	
	private SearchHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public Card cardSearch(String name) {
		SearchCriterion criterion = new SearchCriterion(name, null);
		Card card = cardsSearch(criterion).get(0);
		return card;
	}
	
	public Deck deckSearch(String name) {
		String sql = "SELECT * FROM " + DeckEntry.TABLE_NAME + " WHERE name = ?";
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, new String[]{name});
		Deck deck = null;
		if(cursor.moveToFirst()) {
			deck = cursorToDeck(cursor);
		}

		cursor.close();
		return deck;
	}
	
	public List<Deck> decksSearch() {
		String sql = "SELECT * FROM " + DeckEntry.TABLE_NAME + " ORDER BY " + DeckEntry.COLUMN_NAME_NAME;
		List<Deck> decks = new ArrayList<Deck>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
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
	
	public List<Formation> formationsSearch(String deckName) {
		String sql = "SELECT * FROM " + FormationEntry.TABLE_NAME + " WHERE deck = ?";
		List<Formation> formations = new ArrayList<Formation>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
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
	
	public List<Card> deckCardsSearch(List<Formation> formations) {
		List<Card> cards = new ArrayList<Card>();
		if(formations.size() != 0){
			String sql = "SELECT * from " + CardEntry.TABLE_NAME + " WHERE";
			List<String> sqlArgs = new ArrayList<String>();
			boolean first = true;
			for (Formation formation : formations){
				if(first){
					sql = sql + " " + CardEntry.COLUMN_NAME_NAME + " = ?";
					first = false;
				}else{
					sql = sql + " OR " + CardEntry.COLUMN_NAME_NAME + " = ?";
				}
				sqlArgs.add(formation.getCard());
			}
			sql = sql + " ORDER BY " + CardEntry.COLUMN_NAME_COST + ", " + CardEntry.COLUMN_NAME_NAME;
			String [] selectionArgs = new String[sqlArgs.size()];
			sqlArgs.toArray(selectionArgs);
			SQLiteDatabase db = dbHelper.getReadableDatabase();
	
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
		return cards;
	}
	
	public List<Card> cardsSearch(SearchCriterion searchCriterion) {
		String sql = "SELECT * FROM " + CardEntry.TABLE_NAME;
		List<String> sqlArgs = new ArrayList<String>();
//=====================================================VERSIONE2=======================================================
		boolean first = true;
		if(searchCriterion!=null){
			if(searchCriterion.getFilters()!=null){
				for (Entry<String, ArrayList<String>> entry : searchCriterion.getFilters().entrySet()){
					if(entry.getValue().size()!= 0){
						if (first){
							sql = sql + " WHERE";
							first = false;
							if(entry.getValue().size()>1){
								sql = sql + " (";
								boolean first1 = true;
								for (String entry2 : entry.getValue()){
									if (first1){
										first1 = false;
										if(entry.getKey() == "className"){
											sql = sql + "class = ?";
										}else{
											sql = sql + entry.getKey() + " = ?";
										}
										sqlArgs.add(entry2);
									}else{
										if(entry.getKey() == "className"){
											sql = sql + " OR " + "class = ?";
										}else{
											sql = sql + " OR " + entry.getKey() + " = ?";
										}
										sqlArgs.add(entry2);
									}
								}
								sql = sql + ")";
							}else{
								if(entry.getKey() == "className"){
									sql = sql + " class = ?";
								}else{
									sql = sql + " " + entry.getKey() + " = ?";
								}
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
										if(entry.getKey() == "className"){
											sql = sql + "class = ?";
										}else{
											sql = sql + entry.getKey() + " = ?";
										}
										sqlArgs.add(entry2);
									}else{
										if(entry.getKey() == "className"){
											sql = sql + " OR class = ?";
										}else{
											sql = sql + " OR " + entry.getKey() + " = ?";
										}
										sqlArgs.add(entry2);						 
									}
								}
								sql = sql + ")";
							}else{
								if(entry.getKey() == "className"){
									sql = sql + " class = ?";
								}else{
									sql = sql + " " + entry.getKey() + " = ?";
								}
								sqlArgs.add(entry.getValue().get(0));					
							}
						}
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
		
		String [] selectionArgs = new String[sqlArgs.size()];
		sqlArgs.toArray(selectionArgs);
		List<Card> cards = new ArrayList<Card>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();

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
	
	private Deck cursorToDeck(Cursor cursor) {
	    Deck deck = new Deck();
	    deck.setName(cursor.getString(cursor.getColumnIndex(DeckEntry.COLUMN_NAME_NAME)));
	    deck.setClassName(cursor.getString(cursor.getColumnIndex(DeckEntry.COLUMN_NAME_CLASS)));
	    deck.setNote(cursor.getString(cursor.getColumnIndex(DeckEntry.COLUMN_NAME_NOTE)));
	    deck.setDate(cursor.getString(cursor.getColumnIndex(DeckEntry.COLUMN_NAME_DATE)));
	    
	    return deck;
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
		
	private boolean controlNull(String s){
		if(s == null){
			return true;
		}else{
			return false;
		}
	}

	public boolean nameCheck(String name) {
		if (name != null && !name.isEmpty() && !name.trim().isEmpty()){
			List<Deck> decks = decksSearch();
			return nameCheck(name, decks);
		}
		return false;
	}
	
	private boolean nameCheck(String name, List<Deck> decks) {
		for (Deck deck : decks){
			if(deck.getName().equals(name)) return false;
		}
		return true;
	}
	
	public SearchCriterion deckCriterionRequest(String deckName) {
		Deck deck = SearchHandler.getInstance(dbHelper).deckSearch(deckName);
		List<Formation> copyFormations = SearchHandler.getInstance(dbHelper).formationsSearch(deckName);
		
		DeckHandler.getInstance(dbHelper).setDeck(deck);
		DeckHandler.getInstance(dbHelper).setCopyFormations(copyFormations);
		DeckHandler.getInstance(dbHelper).setTmpFormations(copyFormations);
		//		copyFormations = SearchHandler.getInstance(dbHelper).formationsSearch(deckName);
		return createSearchCriterion(deck);
	}
	
	private SearchCriterion createSearchCriterion(Deck deck) {
		Map<String, ArrayList<String>> filters = new HashMap<String, ArrayList<String>>();
		ArrayList<String> classFilter = new ArrayList<String>();
		classFilter.add(deck.getClassName());
		classFilter.add("Neutral");
		filters.put("className", classFilter);
		return new SearchCriterion(null, filters);
	}
}
