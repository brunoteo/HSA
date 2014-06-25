package com.hsa.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.aggregation.PartialTextualAggregation;
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
		if(tmpFormations.size()>1){
			tmpFormations = costSort(tmpFormations, 0, tmpFormations.size()-1);
			tmpFormations = nameSort(tmpFormations);
		}
		if(tmpFormations.equals(copyFormations))
			return false;
		return true;	
	}
	
	public int cardNumberRequest() {
		int cards = 0;
		for(GraphicalAggregation ga : tmpGraphicalsAggregations){
			cards = cards + ga.getOccurrence();
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
		int occurrence = tmpGraphicalsAggregations.get(index).getOccurrence() + 1;
		tmpGraphicalsAggregations.get(index).setOccurrence(occurrence);
		int indexF = getDeckIndex(cardName);
		tmpFormations.get(indexF).setOccurrence(occurrence);
	}
	
	private void addCard(String cardName) {
		Card card = SearchHandler.getInstance(dbHelper).cardSearch(cardName);
		GraphicalAggregation newDeckCard = ViewHandler.getInstance(dbHelper).createGraphicalAggregation(card, null);
		tmpGraphicalsAggregations.add(newDeckCard);
		tmpFormations.add(new Formation(newDeckCard.getName(), deck.getName() ,newDeckCard.getOccurrence()));
	}
	
	private void deleteCard(String cardName) {
		int index = checkExistenceCard(cardName);
		int occurrence = tmpGraphicalsAggregations.get(index).getOccurrence();
		if(occurrence > 1) {
			decreaseOccurrence(cardName, index, occurrence);
		} else {
			cancelCard(cardName, index);
		}
	}
	
	private void decreaseOccurrence(String cardName, int index, int occurrence) {
		tmpGraphicalsAggregations.get(index).setOccurrence(occurrence-1);
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
	
	private List<Formation> nameSort(List<Formation> ptas){
		int i = 0;
		while(i<ptas.size()-1){
			int startCost = i;
			boolean search = true;
			int endCost = i;
			while (search && i<ptas.size()-1){
				i++;
				if(SearchHandler.getInstance(dbHelper).cardSearch(ptas.get(i).getCard()).getCost() == SearchHandler.getInstance(dbHelper).cardSearch(ptas.get(startCost).getCard()).getCost()){
					endCost = i;
				}else{
					search=false;
				}
			}
			if(startCost != endCost) qsort(ptas, startCost, endCost);
		}
		return ptas;
	}

	public List<Formation> qsort(List<Formation> ptas, int low, int high) {
        int i = low, j = high;

        // Get the pivot element
        Random r = new Random();
        int pivot = r.nextInt(high-low+1)+low;

        // Divide into two lists
        while (i <= j) {

          while (ptas.get(i).getCard().compareTo(ptas.get(pivot).getCard()) < 0) i++;

          while (ptas.get(j).getCard().compareTo(ptas.get(pivot).getCard()) > 0) j--;

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

    static void exchange(List<Formation> ptas, int i, int j) {
    	Formation temp = ptas.get(i);
    	ptas.set(i, ptas.get(j));
    	ptas.set(j, temp);
    }
	
	private int partition(List<Formation> ptas, int left, int right){
	      int i = left, j = right;
	      Formation tmp;
	      Formation pivot = ptas.get((left + right) / 2);
	     
	      while (i <= j) {
	            while (SearchHandler.getInstance(dbHelper).cardSearch(ptas.get(i).getCard()).getCost() < SearchHandler.getInstance(dbHelper).cardSearch(pivot.getCard()).getCost())
	                  i++;
	            while (SearchHandler.getInstance(dbHelper).cardSearch(ptas.get(j).getCard()).getCost() > SearchHandler.getInstance(dbHelper).cardSearch(pivot.getCard()).getCost())
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
	
	private List<Formation> costSort(List<Formation> ptas, int left, int right) {
	      int index = partition(ptas, left, right);
	      if (left < index - 1)
	            costSort(ptas, left, index - 1);
	      if (index < right)
	            costSort(ptas, index, right);
	      return ptas;
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
