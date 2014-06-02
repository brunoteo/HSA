package com.hsa.handler;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.hsa.aggregation.CompleteTextualAggregation;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.aggregation.PartialTextualAggregation;
import com.hsa.bean.Card;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
import com.hsa.bean.SearchCriterion;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.aggregation.DeckDataAggregation;

public class ViewHandler{
	
	private static ViewHandler viewInstance;
	
	private HSADatabaseHelper dbHelper;
	
	private Context context;
	
	public static ViewHandler getInstance(HSADatabaseHelper dbHelper) {
		if(viewInstance == null)
			viewInstance = new ViewHandler(dbHelper);
		return viewInstance;
	}

	private ViewHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public List<GraphicalAggregation> generateGraphicalsAggregation(List<Card> cards, FragmentActivity fragment) {
		List<GraphicalAggregation> graphicalsAggregations = new ArrayList<GraphicalAggregation>();
		this.context = fragment;
		for(Card card : cards) {
			graphicalsAggregations.add(createGraphicalAggregation(card, null));
		}
		
		return graphicalsAggregations;
	}
	
	public List<DeckDataAggregation> decksRequest() {
		List<Deck> decks = SearchHandler.getInstance(dbHelper).decksSearch();
		List<DeckDataAggregation> deckDataAggregations = new ArrayList<DeckDataAggregation>();
		for(Deck deck : decks){
			deckDataAggregations.add(createDeckDataAggregation(deck));
		}
		return deckDataAggregations;
	}
	
//	public DeckDataAggregation deckCreationRequest(String name, String className){
//		Deck newDeck = SaveHandler.getInstance(dbHelper).createDeck(name, className);
//        DeckDataAggregation dda = new DeckDataAggregation();
//        dda.setName(newDeck.getName());
//        dda.setClassName(newDeck.getClassName());
//        dda.setCardNumber(0);
//        dda.setDate(newDeck.getDate());
//		DeckDataAggregation dda = createDeckDataAggregation(newDeck);
//        return dda;
//	}
	
	public CompleteTextualAggregation completeInfoRequest(String cardName) {
		Card card = SearchHandler.getInstance(dbHelper).cardSearch(cardName);
		CompleteTextualAggregation completeTextualAggregation = createCompleteTextualAggregation(card);
		return completeTextualAggregation;
	}
	
	private CompleteTextualAggregation createCompleteTextualAggregation(Card card) {
		return new CompleteTextualAggregation(card);
	}
	
	public GraphicalAggregation createGraphicalAggregation(Card card, Formation formation) {
		String uri = "@drawable/" + card.getPath();
		int image = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
		GraphicalAggregation ga = new GraphicalAggregation();
		ga.setName(card.getName());
		if(formation==null)
			ga.setOccurence(1);
		else
			ga.setOccurence(formation.getOccurrence());
		ga.setImage(image);
		return ga;
	}
	
	
	public DeckDataAggregation createDeckDataAggregation(Deck deck) {
		DeckDataAggregation dda = new DeckDataAggregation();
		dda.setName(deck.getName());
		dda.setClassName(deck.getClassName());
		dda.setDate(deck.getDate());
		List<Formation> formations = SearchHandler.getInstance(dbHelper).formationsSearch(deck.getName());
		int n = 0;
		for(int i = 0; i<formations.size();i++){
			n = n + formations.get(i).getOccurrence();
		}
		dda.setCardNumber(n);
		return dda;
	}

	public List<PartialTextualAggregation> generatePartialTextualAggregations(List<Card> cards, List<Formation> trackFormations) {
		
		int total = 0;
		for(Formation formation : trackFormations){
			//if(formation.getCard() == card.getName()) this.occurrences = formation.getOccurrence();
			total = total + formation.getOccurrence();
		}
		
		List<PartialTextualAggregation> partials = new ArrayList<PartialTextualAggregation>();
		
		for (Card card : cards){
			PartialTextualAggregation partial = new PartialTextualAggregation(card.getName(), card.getCost(), card.getRarity(), 0.0, 0);
			for(Formation formation : trackFormations){
				if(formation.getCard().equals(card.getName())){
					partial.setOccurrences(formation.getOccurrence());
					Double probability = (double) Math.round(((formation.getOccurrence() * 10000) / total ))/100;
					partial.setProbability(probability);
				}
			}
			partials.add(partial);
		}
		
		return partials;
	}
	
	public List<GraphicalAggregation> generateDeckCardsAggregations(List<Card> cards, List<Formation> formations) {
		List<GraphicalAggregation> graphicalsAggregations = new ArrayList<GraphicalAggregation>();
		Formation cardFormation = null;
		for(Card card : cards) {
			for(Formation formation : formations) {
				if(formation.getCard().equals(card.getName())) {
					cardFormation = formation;
					break;
				}			
			}
			graphicalsAggregations.add(createGraphicalAggregation(card, cardFormation));
		}
		return graphicalsAggregations;
	}
	
}
