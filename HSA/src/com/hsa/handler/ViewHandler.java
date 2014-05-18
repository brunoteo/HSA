package com.hsa.handler;

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
import com.hsa.database.HSADatabaseHelper;
import com.hsa.aggregation.DeckDataAggregation;

public class ViewHandler{
	
	/**
	 * 
	 */
	private static ViewHandler viewInstance;
	
	private HSADatabaseHelper dbHelper;
	
	public static ViewHandler getInstance(HSADatabaseHelper dbHelper) {
		if(viewInstance == null)
			viewInstance = new ViewHandler(dbHelper);
		return viewInstance;
	}

	private ViewHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public List<GraphicalAggregation> searchRequest(SearchCriterion criterion, FragmentActivity fragment) {
		List<GraphicalAggregation> graphicalsAggregations = new ArrayList<GraphicalAggregation>();
		List<Card> cards = SearchHandler.getInstance(dbHelper).search(criterion);
		for(Card card : cards) {
			graphicalsAggregations.add(createGraphicalAggregation(card, fragment));
		}
		
		return graphicalsAggregations;
	}
	
	public List<DeckDataAggregation> decksRequest(FragmentActivity fragment) {
		List<Deck> decks = SearchHandler.getInstance(dbHelper).decksRequest();
		List<DeckDataAggregation> deckDataAggregations = new ArrayList<DeckDataAggregation>();
		for(Deck deck : decks){
			deckDataAggregations.add(createDeckDataAggregation(deck, fragment));
		}
		return deckDataAggregations;
	}
	
	public DeckDataAggregation deckCreationRequest(String name, String className){
		Deck newDeck = SearchHandler.getInstance(dbHelper).dataCheck(name, className);
		if(newDeck == null) return null;
		
        DeckDataAggregation dda = new DeckDataAggregation();
        dda.setName(newDeck.getName());
        dda.setClassName(newDeck.getClassName());
        //FIXME bisogna contare le occorrenze
        dda.setCardNumber(0);
        dda.setDate(newDeck.getDate());
        return dda;
	}

	public CompleteTextualAggregation completeInfoRequest(GraphicalAggregation graphicalAggregation) {
		Card card = SearchHandler.getInstance(dbHelper).cardRetrievalRequest(graphicalAggregation.getName());
		CompleteTextualAggregation completeTextualAggregation;
		completeTextualAggregation = createCompleteTextualAggregation(card);
		return completeTextualAggregation;
	}
	
	public void viewDeckRequest(DeckDataAggregation deckData) {
		
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
		List<Formation> formations = SearchHandler.getInstance(dbHelper).formationsRequest(deck.getName());
		int n = 0;
		for(int i = 0; i<formations.size();i++){
			n = n + formations.get(i).getOccurrence();
		}
		dda.setCardNumber(n);
		return dda;
	}
	
}
