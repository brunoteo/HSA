package com.hsa.fragment;

import java.util.List;

import com.hsa.R;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.aggregation.PartialTextualAggregation;
import com.hsa.bean.Card;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;
import com.hsa.handler.SaveHandler;
import com.hsa.handler.SearchHandler;
import com.hsa.handler.TrackHandler;
import com.hsa.handler.ViewHandler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TrackFragment extends Fragment{
	
	private HSADatabaseHelper dbHelper;
	private SaveHandler saveHandler;
	private SearchHandler searchHandler;
	private ViewHandler viewHandler;
	private DeckHandler deckHandler;
	private TrackHandler trackHandler;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_track, container, false);
         
        return rootView;
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        dbHelper = HSADatabaseHelper.getInstance(getActivity());
        deckHandler = DeckHandler.getInstance(dbHelper);
        trackHandler = TrackHandler.getInstance(dbHelper);
        List<Card> cards = deckHandler.trackDeckRequest();
        List<PartialTextualAggregation> partials = trackHandler.partialTextualAggregationRequest(cards);
        String tre = "tre";
        //List<GraphicalAggregation> graphicalsAggregations = viewHandler.searchRequest(null, this.getActivity());
        //viewGraphicsAggregations(graphicalsAggregations);
	}
}
