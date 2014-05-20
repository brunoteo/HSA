package com.hsa.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.aggregation.GraphicalAggregation;
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
	
	public SearchCriterion deckCriterionRequest(String deckName) {
		deck = SearchHandler.getInstance(dbHelper).deckSearch(deckName);
		tmpFormations = SearchHandler.getInstance(dbHelper).formationsSearch(deckName);
		
		Map<String, ArrayList<String>> filters = new HashMap<String, ArrayList<String>>();
		ArrayList<String> classFilter = new ArrayList<String>();
		classFilter.add(deck.getClassName());
		classFilter.add("Neutral");
		filters.put("className", classFilter);
		return new SearchCriterion(null, filters);
	}
	
	public List<GraphicalAggregation> deckCardsRequest() {
		List<Card> deckCards = SearchHandler.getInstance(dbHelper).deckCardsSearch(tmpFormations);
		if(deckCards.size()!=0)
			return ViewHandler.getInstance(dbHelper).generateDeckCardsAggregations(deckCards, tmpFormations);
		else
			return null;
	}

	public void trackDeck() {
		TrackHandler.getInstance(dbHelper).trackDeck(deck, tmpFormations);
		
	}

}
