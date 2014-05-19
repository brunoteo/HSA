package com.hsa.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsa.bean.Card;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
import com.hsa.bean.SearchCriterion;
import com.hsa.database.HSADatabaseHelper;

public class DeckHandler {
	
	private static DeckHandler deckHandler;
	
	private HSADatabaseHelper dbHelper;
	
	private Deck deck;
	private List<Formation> tmpFormations;
	
	public static DeckHandler getInstance(HSADatabaseHelper dbHelper) {
		if(deckHandler == null)
			deckHandler = new DeckHandler(dbHelper);
		return deckHandler;
	}
	
	private DeckHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public void viewDeck(String name) {
		deck = SearchHandler.getInstance(dbHelper).deckRetrievalRequest(name);
		tmpFormations = SearchHandler.getInstance(dbHelper).formationsRequest(name);
		Map<String, ArrayList<String>> filters = new HashMap<String, ArrayList<String>>();
		ArrayList<String> classFilter = new ArrayList<String>();
		classFilter.add(deck.getClassName());
		classFilter.add("Neutrol");
		filters.put("className", classFilter);
		SearchCriterion criterion = new SearchCriterion(null, filters);
	}
	// TODO perché hai messo ovunuqe il dbhelper come variabile se si può ottenere anche di esso l instance?

	public void trackDeck() {
		TrackHandler.getInstance(dbHelper).trackDeck(deck, tmpFormations);
		
	}

}
