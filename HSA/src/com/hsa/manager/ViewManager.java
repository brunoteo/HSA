package com.hsa.manager;

import java.util.ArrayList;
import java.util.List;

import android.R;

import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.Card;
import com.hsa.database.HSADatabaseHelper;

public class ViewManager {
	
//	private HSADatabaseHelper dbHelper;
	private List<GraphicalAggregation> graphicalsAggregations;
	
	public ViewManager(HSADatabaseHelper dbHelper) {
//		this.dbHelper = dbHelper;
		graphicalsAggregations = new ArrayList<GraphicalAggregation>();
	}
	
	public void generateGraphicalsAggregations(List<Card> cards) {
		for(Card card : cards) {
			graphicalsAggregations.add(createGraphicalAggregation(card));
		}
		//TODO visualizzaAggregazioniGrafiche
	}
	
	public GraphicalAggregation createGraphicalAggregation(Card card) {
		GraphicalAggregation ga = new GraphicalAggregation();
		ga.setName(card.getName());
		ga.setOccurence(0);
		
		return new GraphicalAggregation();
	}

}
