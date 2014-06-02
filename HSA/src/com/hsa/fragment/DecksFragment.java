package com.hsa.fragment;

import java.util.List;

import com.hsa.R;
import com.hsa.adapter.DeckDataAggregationAdapter;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.bean.Deck;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.SearchHandler;
import com.hsa.handler.ViewHandler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DecksFragment extends Fragment{

	private HSADatabaseHelper dbHelper;
	
	private ViewHandler viewHandler;
	
	private OnDecksListener onDecksListener;
	
	private ListView listView;
	
	public interface OnDecksListener{
		public void onDeckSelected(DeckDataAggregation deckDataAggregation);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_decks, container, false);
        return rootView;
    }
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try{
			onDecksListener = (OnDecksListener) activity;
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString());
		}
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        dbHelper = HSADatabaseHelper.getInstance(getActivity());
        viewHandler = ViewHandler.getInstance(dbHelper);
        
        List<Deck> decks = SearchHandler.getInstance(dbHelper).decksSearch();
        List<DeckDataAggregation> deckDataAggregations = viewHandler.generateDeckData(decks);
        viewDeckDataAggregations(deckDataAggregations);
        
	}
	
	public void refreshList() {
		List<Deck> decks = SearchHandler.getInstance(dbHelper).decksSearch();
        List<DeckDataAggregation> deckDataAggregations = viewHandler.generateDeckData(decks);
        viewDeckDataAggregations(deckDataAggregations);
	}
	
	public void viewDeckDataAggregations(final List<DeckDataAggregation> deckDataAggregations) {
		listView = (ListView) getView().findViewById(R.id.deckListView);
		listView.setAdapter(new DeckDataAggregationAdapter(this.getActivity(), deckDataAggregations));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onDecksListener.onDeckSelected(deckDataAggregations.get(position));				
			}
		});
		
	}
	
}
