package com.hsa.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.widget.EditText;

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
	private List<Formation> copyFormations;
	private List<Formation> tmpFormations;
	private List<GraphicalAggregation> tmpGraphicalsAggregations;

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
		copyFormations = new ArrayList<Formation>(tmpFormations);
		Map<String, ArrayList<String>> filters = new HashMap<String, ArrayList<String>>();
		ArrayList<String> classFilter = new ArrayList<String>();
		classFilter.add(deck.getClassName());
		classFilter.add("Neutral");
		filters.put("className", classFilter);
		return new SearchCriterion(null, filters);
	}
	
	public List<GraphicalAggregation> deckCardsRequest() {
		List<Card> deckCards = SearchHandler.getInstance(dbHelper).deckCardsSearch(tmpFormations);
		if(deckCards.size()!=0) {
			tmpGraphicalsAggregations = ViewHandler.getInstance(dbHelper).generateDeckCardsAggregations(deckCards, tmpFormations);
			return tmpGraphicalsAggregations;
		} else {
			tmpGraphicalsAggregations = new ArrayList<GraphicalAggregation>();
			return null;
		}
	}
	
	public List<GraphicalAggregation> modifyDeckRequest(String cardName, int type) {
		if(type==0){
			insertCard(cardName);
		} else {
			deleteCard(cardName);		
		}
		return tmpGraphicalsAggregations;
	}
	
	public boolean controlModifyRequest() {
		if(checkModify()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void saveRequest() {
		SaveHandler.getInstance(dbHelper).updateDeck(copyFormations, tmpFormations, deck);
	}
	
	public int cardNumberRequest() {
		int cards = 0;
		for(GraphicalAggregation ga : tmpGraphicalsAggregations){
			cards = cards + ga.getOccurence();
		}
		return cards;
	}
	
	public void deckDeletionRequest() {
		SaveHandler.getInstance(dbHelper).deleteDeck(deck);
		
	}

	public List<Card> trackDeckRequest() {
		List<Card> cards = TrackHandler.getInstance(dbHelper).trackDeck(deck, tmpFormations);
		return cards;
	}
	
	private int checkExistenceCard(String cardName) {
		if(tmpGraphicalsAggregations.size()==0){
			return -1;
		} else {
			for(int index = 0; index<tmpGraphicalsAggregations.size(); index++) {
				if(tmpGraphicalsAggregations.get(index).getName().equals(cardName)) {
					return index;
				}
			}
			return -1;
		}	
	}
	
	private boolean checkModify() {
		if(copyFormations.equals(tmpFormations))
			return false;
		else
			return true;
	}
	
	private void insertCard(String cardName) {
		int index = checkExistenceCard(cardName);
		if(index >= 0) {
			int occurrence = tmpGraphicalsAggregations.get(index).getOccurence() + 1;
			tmpGraphicalsAggregations.get(index).setOccurence(occurrence);
			tmpFormations.get(index).setOccurrence(occurrence);
		} else {
			Card card = SearchHandler.getInstance(dbHelper).cardSearch(cardName);
			GraphicalAggregation newDeckCard = ViewHandler.getInstance(dbHelper).createGraphicalAggregation(card, null);
			tmpGraphicalsAggregations.add(newDeckCard);
			tmpFormations.add(new Formation(newDeckCard.getName(), deck.getName() ,newDeckCard.getOccurence()));
		}
	}
	
	private void deleteCard(String cardName) {
		int index = checkExistenceCard(cardName);
		int occurrence = tmpGraphicalsAggregations.get(index).getOccurence();
		if(occurrence > 1) {
			tmpGraphicalsAggregations.get(index).setOccurence(occurrence-1);
			tmpFormations.get(index).setOccurrence(occurrence-1);
		} else {
			tmpGraphicalsAggregations.remove(index);
			tmpFormations.remove(index);
		}
	}


}
