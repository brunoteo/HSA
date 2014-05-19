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
	
	public static TrackHandler getInstance(HSADatabaseHelper dbHelper) {
		if(trackHandler == null)
			trackHandler = new TrackHandler(dbHelper);
		return trackHandler;
	}
	
	private TrackHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public void trackDeck(Deck deck, List<Formation> tmpFormations) {
		this.deck = deck;
		this.tmpFormations = tmpFormations;
		this.trackFormations = tmpFormations;
		this.pile = new Vector<String>();
		
		List<Card> cards = SearchHandler.getInstance(dbHelper).deckCardsRecoveryRequest(trackFormations);
		
		List<PartialTextualAggregation> partials = ViewHandler.getInstance(dbHelper).createPartialTextualAggregation(cards, trackFormations);
		
	}
}
