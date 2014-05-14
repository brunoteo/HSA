package com.hsa.manager;

import java.util.ArrayList;
import java.util.List;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;

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
	
	public void generateGraphicalsAggregations(List<Card> cards, Context context) {
		for(Card card : cards) {
			graphicalsAggregations.add(createGraphicalAggregation(card, context));
		}
		//TODO visualizzaAggregazioniGrafiche
	}
	
	private GraphicalAggregation createGraphicalAggregation(Card card, Context context) {
		String uri = "@drawable/ancestral_spirit.png";
		Drawable image = context.getResources().getDrawable(context.getResources()
                .getIdentifier("ancestral_spirit.png", "drawable", context.getPackageName()));
		GraphicalAggregation ga = new GraphicalAggregation();
		ga.setName(card.getName());
		ga.setOccurence(0);
		ga.setImage(image);
		return new GraphicalAggregation();
	}

}
