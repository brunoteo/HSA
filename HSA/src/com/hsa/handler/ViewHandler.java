package com.hsa.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.hsa.aggregation.CompleteTextualAggregation;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.Card;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
import com.hsa.bean.SearchCriterion;
import com.hsa.aggregation.DeckDataAggregation;

public class ViewHandler implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SearchHandler searchHandler;
	private SaveHandler saveHandler;
	
	public SaveHandler getSaveHandler() {
		return saveHandler;
	}

	public void setSaveHandler(SaveHandler saveHandler) {
		this.saveHandler = saveHandler;
	}
	
	public SearchHandler getSearchHandler() {
		return searchHandler;
	}

	public void setSearchHandler(SearchHandler searchHandler) {
		this.searchHandler = searchHandler;
	}

	//FIXME si potrebbe togliere dbHelper
	public ViewHandler() { }
	
	public List<GraphicalAggregation> searchRequest(SearchCriterion criterion, FragmentActivity fragment) {
//		searchHandler = new SearchHandler(this.dbHelper);
		List<GraphicalAggregation> graphicalsAggregations = new ArrayList<GraphicalAggregation>();
		List<Card> cards = searchHandler.search(criterion);
		for(Card card : cards) {
			graphicalsAggregations.add(createGraphicalAggregation(card, fragment));
		}
		
		return graphicalsAggregations;
	}
	
	public List<DeckDataAggregation> decksRequest(FragmentActivity fragment) {
//		searchHandler = new SearchHandler(this.dbHelper);
		List<Deck> decks = searchHandler.decksRequest();
		List<DeckDataAggregation> deckDataAggregations = new ArrayList<DeckDataAggregation>();
		for(Deck deck : decks){
			deckDataAggregations.add(createDeckDataAggregation(deck, fragment));
		}
		return deckDataAggregations;
	}

	public CompleteTextualAggregation completeInfoRequest(GraphicalAggregation graphicalAggregation) {
		Card card = searchHandler.cardRetrievalRequest(graphicalAggregation.getName());
		CompleteTextualAggregation completeTextualAggregation;
		completeTextualAggregation = createCompleteTextualAggregation(card);
		return completeTextualAggregation;
	}
	
	private CompleteTextualAggregation createCompleteTextualAggregation(Card card) {
		return new CompleteTextualAggregation(card);
	}
	
	private GraphicalAggregation createGraphicalAggregation(Card card, Context context) {
		String uri = "@drawable/" + card.getPath();
		int image = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
		GraphicalAggregation ga = new GraphicalAggregation();
		ga.setName(card.getName());
		ga.setOccurence(0);
		ga.setImage(image);
		return ga;
	}
	
	
	private DeckDataAggregation createDeckDataAggregation(Deck deck, FragmentActivity fragment) {
		DeckDataAggregation dda = new DeckDataAggregation();
		dda.setName(deck.getName());
		dda.setClassName(deck.getClassName());
		dda.setDate(deck.getDate());
		List<Formation> formations = searchHandler.formationsRequest(deck.getName());
		int n = 0;
		for(int i = 0; i<formations.size();i++){
			n = n + formations.get(i).getOccurrence();
		}
		dda.setCardNumber(n);
		return dda;
	}
	
	public DeckDataAggregation deckCreationRequest(String name, String className){
		Deck newDeck = searchHandler.dataCheck(name, className);
		if(newDeck == null) return null;
		
        DeckDataAggregation dda = new DeckDataAggregation();
        dda.setName(newDeck.getName());
        dda.setClassName(newDeck.getClassName());
        dda.setCardNumber(0);
        dda.setDate(newDeck.getDate());
        return dda;
	}
}
