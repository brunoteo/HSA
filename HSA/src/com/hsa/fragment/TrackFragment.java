package com.hsa.fragment;

import java.util.List;

import com.hsa.R;
import com.hsa.adapter.PartialTextualAggregationAdapter;
import com.hsa.aggregation.PartialTextualAggregation;
import com.hsa.bean.Card;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;
import com.hsa.handler.TrackHandler;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class TrackFragment extends Fragment{
	
	private HSADatabaseHelper dbHelper;
	private TrackHandler trackHandler;
	
	private ListView listView;
	private TextView textView;
	
	private OnTracksListener onTracksListener;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_track, container, false);
         
        return rootView;
    }
	
	public interface OnTracksListener{
		public void onTrackSelected(PartialTextualAggregation partial);
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try{
			onTracksListener = (OnTracksListener) activity;
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString());
		}
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        dbHelper = HSADatabaseHelper.getInstance(getActivity());
        DeckHandler.getInstance(dbHelper);
        trackHandler = TrackHandler.getInstance(dbHelper);
//        List<Card> cards = deckHandler.trackDeckRequest();
        List<PartialTextualAggregation> partials = trackHandler.trackDeck();
//        List<PartialTextualAggregation> partials = trackHandler.partialTextualAggregationsRequest(cards);
        viewPartialTextualAggregation(partials);
        viewDeckCardsNumber(partials);
        viewLastCardTracked(null);
	}

	public void viewDeckCardsNumber(List<PartialTextualAggregation> partials) {
		int n = 0;
		for (PartialTextualAggregation partial : partials){
			n += partial.getOccurrences();
		}
		textView = (TextView) getView().findViewById(R.id.remainCards);
		textView.setText("Remaining cards: " + Integer.toString(n) + "/30");
		textView.setTypeface(null, Typeface.BOLD);
	}

	public void viewPartialTextualAggregation(final List<PartialTextualAggregation> partials) {
		listView = (ListView) getView().findViewById(R.id.trackListView);
		listView.setAdapter(new PartialTextualAggregationAdapter(this.getActivity(), partials));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onTracksListener.onTrackSelected(partials.get(position));				
			}
		});
	}

	public void viewLastCardTracked(String nameLast) {
		if(nameLast != null){
			textView = (TextView) getView().findViewById(R.id.lastCard);
			textView.setText("Last card: " + nameLast);
			textView.setTypeface(null, Typeface.BOLD);
		}else{
			textView = (TextView) getView().findViewById(R.id.lastCard);
			textView.setText("Last card: ");
			textView.setTypeface(null, Typeface.BOLD);
		}
	}
}
