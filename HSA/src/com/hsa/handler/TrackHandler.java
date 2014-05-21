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
		partials = quickSort(partials, 0, partials.size()-1);
		
		return partials;
	}

	private int partition(List<PartialTextualAggregation> partials, int left, int right){
	      int i = left, j = right;
	      PartialTextualAggregation tmp;
	      PartialTextualAggregation pivot = partials.get((left + right) / 2);
	     
	      while (i <= j) {
	            while (partials.get(i).getCost() < pivot.getCost())
	                  i++;
	            while (partials.get(j).getCost() > pivot.getCost())
	                  j--;
	            if (i <= j) {
	                  tmp = partials.get(i);
	                  partials.set(i, partials.get(j));
	                  partials.set(j, tmp);
	                  i++;
	                  j--;
	            }
	      };
	     
	      return i;
	}
	
	private List<PartialTextualAggregation> quickSort(List<PartialTextualAggregation> partials, int left, int right) {
	      int index = partition(partials, left, right);
	      if (left < index - 1)
	            quickSort(partials, left, index - 1);
	      if (index < right)
	            quickSort(partials, index, right);
	      return partials;
	}

}
