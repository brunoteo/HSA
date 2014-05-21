package com.hsa.handler;

import java.util.ArrayList;
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
	private List<Formation> tmpFormations;
	private List<PartialTextualAggregation> pile;
	private List<PartialTextualAggregation> partials;
	
	public static TrackHandler getInstance(HSADatabaseHelper dbHelper) {
		if(trackHandler == null)
			trackHandler = new TrackHandler(dbHelper);
		return trackHandler;
	}
	
	private TrackHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public void createCardsPile(){
		this.pile = new ArrayList<PartialTextualAggregation>();
	}
	
	public List<Card> trackDeck(Deck deck, List<Formation> tmpFormations) {
		this.deck = deck;
		this.tmpFormations = tmpFormations;
		createCardsPile();
		
		List<Card> cards = SearchHandler.getInstance(dbHelper).deckCardsSearch(tmpFormations);
		return cards;
	}
	
	public List<PartialTextualAggregation> partialTextualAggregationsRequest(List<Card> cards) {
		partials = ViewHandler.getInstance(dbHelper).generatePartialTextualAggregations(cards, tmpFormations);
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
	      }
	     
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

	public List<PartialTextualAggregation> trackCard(PartialTextualAggregation partial) {
		int occ = checkCardOccurrence(partial);
		if(occ > 1){
			decreaseOccurrence(partial);
		}else{
			deleteCard(partial);
		}
		addCardToPile(partial);
		calculateProbabilities();
		return partials;
	}

	private void calculateProbabilities() {
		int total = 0;
		for (PartialTextualAggregation partial : partials){
			total += partial.getOccurrences();
		}
		int i = 0;
		for(PartialTextualAggregation partial : partials){
			Integer prob = (int) Math.round(((partials.get(i).getOccurrences() * 100.00) / total ));
			partials.get(i).setProbability(prob);
			i++;
		}
	}

	private void addCardToPile(PartialTextualAggregation partial) {
		pile.add(partial);
	}

	private void deleteCard(PartialTextualAggregation partial) {
		int i = 0;
		for(PartialTextualAggregation pta : partials){
			if(pta.getName().equals(partial.getName())){
				partials.remove(i);
				break;
			}
			i++;
		}
	}

	private void decreaseOccurrence(PartialTextualAggregation partial) {
		int i = 0;
		for(PartialTextualAggregation pta : partials){
			if(pta.getName().equals(partial.getName())){
				partials.get(i).setOccurrences(partials.get(i).getOccurrences()-1);
				break;
			}
			i++;
		}
		
	}

	private int checkCardOccurrence(PartialTextualAggregation partial) {
		int occ = 0;
		for(PartialTextualAggregation pta : partials){
			if(pta.getName().equals(partial.getName())){
				occ = pta.getOccurrences();
				break;
			}
		}
		return occ;
	}

}
