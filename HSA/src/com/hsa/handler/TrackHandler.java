package com.hsa.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.hsa.aggregation.PartialTextualAggregation;
import com.hsa.bean.Card;
import com.hsa.bean.Formation;
import com.hsa.database.HSADatabaseHelper;

public class TrackHandler {

	private static TrackHandler trackHandler;
	
	private HSADatabaseHelper dbHelper;
	
	private List<Formation> tmpFormations;
	private List<Card> cards;
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
	
	public List<Card> trackDeck(List<Formation> tmpFormations) {
		this.tmpFormations = tmpFormations;
		createCardsPile();
		
		List<Card> cards = SearchHandler.getInstance(dbHelper).deckCardsSearch(tmpFormations);
		return cards;
	}

	public List<PartialTextualAggregation> partialTextualAggregationsRequest(List<Card> cards) {
		if(cards!=null)
			this.cards = cards;
		else
			createCardsPile();
		partials = ViewHandler.getInstance(dbHelper).generatePartialTextualAggregations(this.cards, tmpFormations);
		partials = costSort(partials, 0, partials.size()-1);
		partials = nameSort(partials);
		
		return partials;
	}
	
	private List<PartialTextualAggregation> nameSort(List<PartialTextualAggregation> ptas){
		int i = 0;
		while(i<ptas.size()-1){
			int startCost = i;
			boolean search = true;
			int endCost = i;
			while (search && i<ptas.size()-1){
				i++;
				if(ptas.get(i).getCost() == ptas.get(startCost).getCost()){
					endCost = i;
				}else{
					search=false;
				}
			}
			if(startCost != endCost) qsort(ptas, startCost, endCost);
		}
		return ptas;
	}

	public List<PartialTextualAggregation> qsort(List<PartialTextualAggregation> ptas, int low, int high) {
        int i = low, j = high;

        // Get the pivot element
        Random r = new Random();
        int pivot = r.nextInt(high-low+1)+low;

        // Divide into two lists
        while (i <= j) {

          while (ptas.get(i).getName().compareTo(ptas.get(pivot).getName()) < 0) i++;

          while (ptas.get(j).getName().compareTo(ptas.get(pivot).getName()) > 0) j--;

          if (i <= j) {
            exchange(ptas, i, j);
            i++;
            j--;
          }
        }

        // Recursion
        if (low < j) qsort(ptas, low, j);
        if (i < high) qsort(ptas, i, high);
        return ptas;
      }

    static void exchange(List<PartialTextualAggregation> ptas, int i, int j) {
    	PartialTextualAggregation temp = ptas.get(i);
    	ptas.set(i, ptas.get(j));
    	ptas.set(j, temp);
    }
	
	private int partition(List<PartialTextualAggregation> ptas, int left, int right){
	      int i = left, j = right;
	      PartialTextualAggregation tmp;
	      PartialTextualAggregation pivot = ptas.get((left + right) / 2);
	     
	      while (i <= j) {
	            while (ptas.get(i).getCost() < pivot.getCost())
	                  i++;
	            while (ptas.get(j).getCost() > pivot.getCost())
	                  j--;
	            if (i <= j) {
	                  tmp = ptas.get(i);
	                  ptas.set(i, ptas.get(j));
	                  ptas.set(j, tmp);
	                  i++;
	                  j--;
	            }
	      }
	     
	      return i;
	}
	
	private List<PartialTextualAggregation> costSort(List<PartialTextualAggregation> ptas, int left, int right) {
	      int index = partition(ptas, left, right);
	      if (left < index - 1)
	            costSort(ptas, left, index - 1);
	      if (index < right)
	            costSort(ptas, index, right);
	      return ptas;
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

	public int pileNumberRequest() {
		int total = 0;
		for(PartialTextualAggregation partial : pile){
			total += partial.getOccurrences();
		}
		return total;
	}

	public String lastTrackRequest() {
		if(pile.size() != 0){
			return pile.get(pile.size()-1).getName();
		}else{
			return null;
		}
	}
}
