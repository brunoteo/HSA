package com.hsa.handler;

import java.util.List;
import java.util.Vector;

import com.hsa.aggregation.PartialTextualAggregation;
import com.hsa.bean.Card;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
import com.hsa.database.HSADatabaseHelper;

public class TrackHandler {

	private static TrackHandler trackHandler;
	
	private HSADatabaseHelper dbHelper;
	
	private Deck deck;
	private List<Formation> trackFormations;
	private List<Formation> tmpFormations;
	private Vector<String> pile;
	private List<PartialTextualAggregation> partials;
	
	public static TrackHandler getInstance(HSADatabaseHelper dbHelper) {
		if(trackHandler == null)
			trackHandler = new TrackHandler(dbHelper);
		return trackHandler;
	}
	
	private TrackHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public List<Formation> createFormationCopy(List<Formation> tmpFormations){
		this.trackFormations = tmpFormations;
		return this.trackFormations;
	}
	
	public void createCardsPile(){
		this.pile = new Vector<String>();
	}
	
	public List<Card> trackDeck(Deck deck, List<Formation> tmpFormations) {
		this.deck = deck;
		this.tmpFormations = tmpFormations;
		createFormationCopy(tmpFormations);
		this.pile = new Vector<String>();
		
		List<Card> cards = SearchHandler.getInstance(dbHelper).deckCardsSearch(trackFormations);
		return cards;
	}
	
	public List<PartialTextualAggregation> partialTextualAggregationRequest(List<Card> cards) {
		partials = ViewHandler.getInstance(dbHelper).generatePartialTextualAggregation(cards, trackFormations);
		return partials;
	}

}
