package com.hsa.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
	private List<PartialTextualAggregation> totalPartials;
	
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
		partials = costSort(partials, 0, partials.size()-1);
		partials = nameSort(partials);
		totalPartials = ViewHandler.getInstance(dbHelper).generatePartialTextualAggregations(cards, tmpFormations);
		totalPartials = costSort(totalPartials, 0, totalPartials.size()-1);
		totalPartials = nameSort(totalPartials);
		
		return partials;
	}
	
	private List<PartialTextualAggregation> nameSort(List<PartialTextualAggregation> partials){
		int i = 0;
		while(i<partials.size()-1){
			int startCost = i;
			boolean search = true;
			int endCost = i;
			while (search && i<partials.size()-1){
				i++;
				if(partials.get(i).getCost() == partials.get(startCost).getCost()){
					endCost = i;
				}else{
					search=false;
				}
			}
			if(startCost != endCost) qsort(partials, startCost, endCost);
		}
		return partials;
	}

	public List<PartialTextualAggregation> qsort(List<PartialTextualAggregation> partials, int low, int high) {
        int i = low, j = high;

        // Get the pivot element
        Random r = new Random();
        int pivot = r.nextInt(high-low+1)+low;

        // Divide into two lists
        while (i <= j) {

          while (partials.get(i).getName().compareTo(partials.get(pivot).getName()) < 0) i++;

          while (partials.get(j).getName().compareTo(partials.get(pivot).getName()) > 0) j--;

          if (i <= j) {
            exchange(partials, i, j);
            i++;
            j--;
          }
        }

        // Recursion
        if (low < j) qsort(partials, low, j);
        if (i < high) qsort(partials, i, high);
        return partials;
      }

    static void exchange(List<PartialTextualAggregation> partials, int i, int j) {
    	PartialTextualAggregation temp = partials.get(i);
    	partials.set(i, partials.get(j));
    	partials.set(j, temp);
    }
	
	private int partition(int left, int right){
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
	//TODO aggiungere questi due sort ai diagrammi di tracciamento?
	private List<PartialTextualAggregation> costSort(List<PartialTextualAggregation> partials2, int left, int right) {
	      int index = partition(left, right);
	      if (left < index - 1)
	            costSort(partials2, left, index - 1);
	      if (index < right)
	            costSort(partials2, index, right);
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
		partials = costSort(partials, 0, partials.size()-1);
		partials = nameSort(partials);
		return partials;
	}

	private void calculateProbabilities() {
		int total = 0;
		for (PartialTextualAggregation partial : partials){
			total += partial.getOccurrences();
		}
		int i = 0;
		for(PartialTextualAggregation partial : partials){
			Double prob = (double) Math.round(((partials.get(i).getOccurrences() * 10000) / total )) / 100;
			partial.setProbability(prob);
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

	public List<PartialTextualAggregation> lastCardRestorationRequest() {
		if(pile.size() == 0) return null;
		PartialTextualAggregation pta=popLastTrackedCard();
		int occ = verifyCardOccurrences(pta);
		if(occ > 0){
			increaseOccurrencesNumber(pta);
		}else{
			insertCard(pta);
		}
		updateProbabilities();
		partials = costSort(partials, 0, partials.size()-1);
		partials = nameSort(partials);
		return partials;
	}
	
	public void updateProbabilities(){
		int total = 0;
		for (PartialTextualAggregation partial : partials){
			total += partial.getOccurrences();
		}
		
		for (PartialTextualAggregation partial : partials){
			Double probability = (double) Math.round(((partial.getOccurrences() * 10000) / total ))/100;
			partial.setProbability(probability);
		}
	}
	//TODO quicksort by name

	private void insertCard(PartialTextualAggregation pta) {
		partials.add(pta);
	}

	private void increaseOccurrencesNumber(PartialTextualAggregation pta) {
		int i = 0;
		for(PartialTextualAggregation partial : partials){
			if(pta.getName().equals(partial.getName())) partials.get(i).setOccurrences(partials.get(i).getOccurrences() + 1);
			i++;
		}
	}

	private int verifyCardOccurrences(PartialTextualAggregation pta) {
		for(PartialTextualAggregation partial : partials){
			if(pta.getName().equals(partial.getName())) return partial.getOccurrences();
		}
		return 0;
	}

	private PartialTextualAggregation popLastTrackedCard() {
		return pile.remove(pile.size()-1);
	}

	public int cardsNumberRequest() {
		int total = 0;
		for(PartialTextualAggregation partial : partials){
			total += partial.getOccurrences();
		}
		return total;
	}

	public List<PartialTextualAggregation> getAllPartialTextualAggregation() {
		return totalPartials;
	}
}
