package com.hsa.fragment;

import java.util.List;

import com.hsa.R;
import com.hsa.activity.DeckActivity;
import com.hsa.adapter.GraphicalAggregationAdapter;
import com.hsa.aggregation.CompleteTextualAggregation;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.SearchCriterion;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;
import com.hsa.handler.ViewHandler;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;

public class DeckFragment extends Fragment{
	
	private HSADatabaseHelper dbHelper;
	
	private ViewHandler viewHandler;
	private DeckHandler deckHandler;
	
	private GridView gridView;
	
	private List<GraphicalAggregation> deckCardsGA;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);
         
        return rootView;
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        dbHelper = HSADatabaseHelper.getInstance(getActivity());
        deckHandler = DeckHandler.getInstance(dbHelper);
        viewHandler = ViewHandler.getInstance(dbHelper);
        
        SearchCriterion criterion = deckHandler.deckCriterionRequest(((DeckActivity) getActivity()).getDeckDataAggregation().getName());
        List<GraphicalAggregation> graphicalsAggregations = viewHandler.cardsSearchRequest(criterion, this.getActivity());
        viewGraphicsAggregations(graphicalsAggregations);
        
        deckCardsGA = deckHandler.deckCardsRequest();
        if(deckCardsGA!=null)
        	viewDeckCardsGraphicsAggregations(deckCardsGA);
        
        //TODO visualizza numero carte
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.deck_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
		
	}
	
	public void viewGraphicsAggregations(final List<GraphicalAggregation> graphicalsAggregations) {
		gridView = (GridView) getView().findViewById(R.id.gridview2);
        gridView.setAdapter(new GraphicalAggregationAdapter(this.getActivity(), graphicalsAggregations));
//        gridView.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//				CompleteTextualAggregation completeTextualAggregation = viewHandler.completeInfoRequest(graphicalsAggregations.get(position));			
//				onSearchListener.onCardSelected(completeTextualAggregation);
//				return true;
//			}
//        	
//		});
	}
	
	public void viewDeckCardsGraphicsAggregations(final List<GraphicalAggregation> graphicalsAggregations) {
		LinearLayout view = (LinearLayout) getActivity().findViewById(R.id.deckCards);
		int i = 1;
		for(GraphicalAggregation ga : graphicalsAggregations) {
			ImageView image = new ImageView(getActivity());
			image.setVisibility(View.VISIBLE);
			if(i%2==0)
				image.setBackgroundColor(Color.BLUE);
			else
				image.setBackgroundColor(Color.RED);
			i++;
			image.setImageResource(ga.getImage());
			image.setLayoutParams(new LayoutParams(160, LayoutParams.WRAP_CONTENT));
			image.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
			view.addView(image);
		}
		//Non riesco a refreshare la pagina
		
//		hListView = (HorizontalListView) getActivity().findViewById(R.id.cardDeck);
//		hListView.setAdapter(new DeckCardsAdapter(this.getActivity(), graphicalsAggregations));
	}

}
