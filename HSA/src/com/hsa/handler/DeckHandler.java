package com.hsa.handler;

import java.util.ArrayList;
import java.util.List;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.Card;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
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
	
	public List<GraphicalAggregation> modifyDeckRequest(String cardName, boolean insert) {
		if(insert){
			if(checkNumCards()) {
				insertCard(cardName);
			} else {
				return null;
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
	
	public int cardNumberRequest() {
		int cards = 0;
		for(GraphicalAggregation ga : tmpGraphicalsAggregations){
			cards = cards + ga.getOccurence();
		}
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

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public List<Formation> getTmpFormations() {
		return tmpFormations;
	}

	public void setTmpFormations(List<Formation> copyFormations) {
		tmpFormations = new ArrayList<Formation>();
		for(Formation f : copyFormations){
			tmpFormations.add((Formation) f.clone());
		}
	}

	public List<Formation> getCopyFormations() {
		return copyFormations;
	}

	public void setCopyFormations(List<Formation> copyFormations) {
		this.copyFormations = copyFormations;
	}

	public List<GraphicalAggregation> getTmpGraphicalsAggregations() {
		return tmpGraphicalsAggregations;
	}

	public void setTmpGraphicalsAggregations(
			List<GraphicalAggregation> tmpGraphicalsAggregations) {
		this.tmpGraphicalsAggregations = tmpGraphicalsAggregations;
	}


}
