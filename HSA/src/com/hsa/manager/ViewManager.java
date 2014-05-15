package com.hsa.manager;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.GridView;

import com.hsa.MainActivity;
import com.hsa.R;
import com.hsa.adapter.GraphicalAggregationAdapter;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.Card;
import com.hsa.bean.SearchCriterion;
import com.hsa.database.HSADatabaseHelper;

public class ViewManager {
	
	private HSADatabaseHelper dbHelper;
	private List<GraphicalAggregation> graphicalsAggregations;
	
	public ViewManager(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
		graphicalsAggregations = new ArrayList<GraphicalAggregation>();
	}
	
	public List<GraphicalAggregation> searchRequest(SearchCriterion criterion, FragmentActivity fragment) {
		SearchManager searchManager = new SearchManager(this.dbHelper);	
		List<Card> cards = searchManager.search(criterion);
        List<GraphicalAggregation> graphicalsAggregations = generateGraphicalsAggregations(cards, fragment);
        return graphicalsAggregations;
	}
	public List<GraphicalAggregation> generateGraphicalsAggregations(List<Card> cards, Context context) {
		for(Card card : cards) {
			graphicalsAggregations.add(createGraphicalAggregation(card, context));
		}
		
		return graphicalsAggregations;
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
	

}
