package com.hsa.fragment;

import java.util.List;

import com.hsa.MainActivity;
import com.hsa.R;
import com.hsa.adapter.DeckDataAggregationAdapter;
import com.hsa.adapter.GraphicalAggregationAdapter;
import com.hsa.aggregation.CompleteTextualAggregation;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.handler.ViewHandler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class DecksFragment extends Fragment{

	private ViewHandler viewHandler;
	
	ListView listView;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_decks, container, false);
         
        return rootView;
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        viewHandler = new ViewHandler(((MainActivity) getActivity()).getDbHelper());
        List<DeckDataAggregation> deckDataAggregations = viewHandler.decksRequest(this.getActivity());
        viewDeckDataggregations(deckDataAggregations);
        
	}

	private void viewDeckDataggregations(List<DeckDataAggregation> deckDataAggregations) {
		listView = (ListView) getView().findViewById(R.id.deckListView);
		listView.setAdapter(new DeckDataAggregationAdapter(this.getActivity(), deckDataAggregations));
//		listView.setOnItemLongClickListener(new OnItemLongClickListener() { 
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
	
}
