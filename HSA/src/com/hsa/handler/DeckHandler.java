package com.hsa.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
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
		copyFormations = SearchHandler.getInstance(dbHelper).formationsSearch(deckName);
		return createSearchCriterion();
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
	
	public List<GraphicalAggregation> modifyDeckRequest(String cardName, boolean insert, Context context) {
		if(insert){
			if(checkNumCards()) {
				insertCard(cardName);
			} else {
				AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
				
	            dlgAlert.setMessage("Deck contains 30 cards.");
	            dlgAlert.setTitle("WARNING");
	            dlgAlert.setPositiveButton("OK", null);
	            dlgAlert.create().show();
			}
		} else {
			deleteCard(cardName);		
		}
		return tmpGraphicalsAggregations;
	}
	
	public boolean controlModifyRequest() {
		if(tmpFormations.equals(copyFormations))
			return false;
		return true;	
	}
	
	public void saveRequest() {
		SaveHandler.getInstance(dbHelper).updateDeck(tmpFormations, deck.getName());
	}
	
	public int cardNumberRequest() {
		int cards = 0;
		for(GraphicalAggregation ga : tmpGraphicalsAggregations){
			cards = cards + ga.getOccurence();
		}
		return cards;
	}
	
	public void deckDeletionRequest() {
		SaveHandler.getInstance(dbHelper).deleteDeck(deck.getName());
		
	}

	public List<Card> trackDeckRequest() {
		List<Card> cards = TrackHandler.getInstance(dbHelper).trackDeck(tmpFormations);
		return cards;
	}
	
	private boolean checkNumCards() {
		int numCards = 0;
		for(Formation f : tmpFormations) {
			numCards += f.getOccurrence();
		}
		if(numCards < 30) {
			return true;
		} else {
			return false;
		}
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
	
	private void insertCard(String cardName) {
		int index = checkExistenceCard(cardName);
		if(index >= 0) {
			increaseOccurrence(cardName, index);
		} else {
			addCard(cardName);
		}
	}
	
	private void increaseOccurrence(String cardName, int index) {
		int occurrence = tmpGraphicalsAggregations.get(index).getOccurence() + 1;
		tmpGraphicalsAggregations.get(index).setOccurence(occurrence);
		int indexF = getDeckIndex(cardName);
		tmpFormations.get(indexF).setOccurrence(occurrence);
	}
	
	private void addCard(String cardName) {
		Card card = SearchHandler.getInstance(dbHelper).cardSearch(cardName);
		GraphicalAggregation newDeckCard = ViewHandler.getInstance(dbHelper).createGraphicalAggregation(card, null);
		tmpGraphicalsAggregations.add(newDeckCard);
		tmpFormations.add(new Formation(newDeckCard.getName(), deck.getName() ,newDeckCard.getOccurence()));
	}
	
	private void deleteCard(String cardName) {
		int index = checkExistenceCard(cardName);
		int occurrence = tmpGraphicalsAggregations.get(index).getOccurence();
		if(occurrence > 1) {
			decreaseOccurrence(cardName, index, occurrence);
		} else {
			cancelCard(cardName, index);
		}
	}
	
	private void decreaseOccurrence(String cardName, int index, int occurrence) {
		tmpGraphicalsAggregations.get(index).setOccurence(occurrence-1);
		int indexF = getDeckIndex(cardName);
		tmpFormations.get(indexF).setOccurrence(occurrence-1);
	}
	
	private void cancelCard(String cardName, int index) {
		tmpGraphicalsAggregations.remove(index);
		int indexF = getDeckIndex(cardName);
		tmpFormations.remove(indexF);
	}
	
	private int getDeckIndex(String cardName) {
		for(int i = 0; i < tmpFormations.size(); i++) {
			if(tmpFormations.get(i).getCard().equals(cardName)) {
				return i;
			}
		}
		return -1;
	}
	
	private SearchCriterion createSearchCriterion() {
		Map<String, ArrayList<String>> filters = new HashMap<String, ArrayList<String>>();
		ArrayList<String> classFilter = new ArrayList<String>();
		classFilter.add(deck.getClassName());
		classFilter.add("Neutral");
		filters.put("className", classFilter);
		return new SearchCriterion(null, filters);
	}


}
