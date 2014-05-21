package com.hsa.fragment;

import java.util.List;

import com.hsa.R;
import com.hsa.activity.DeckActivity;
import com.hsa.adapter.GraphicalAggregationAdapter;
import com.hsa.aggregation.CompleteTextualAggregation;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.SearchCriterion;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;
import com.hsa.handler.ViewHandler;

import android.app.Activity;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeckFragment extends Fragment{
	
	private HSADatabaseHelper dbHelper;
	
	private ViewHandler viewHandler;
	private DeckHandler deckHandler;
	
	private GridView gridView;
	
	private OnDeckListener onDeckListener;
	
	public interface OnDeckListener{
		public void onCardSelected(CompleteTextualAggregation completeAggregation);
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);
         
        return rootView;
    }
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try{
			onDeckListener = (OnDeckListener) activity;
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString());
		}
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
        
        List<GraphicalAggregation> deckCardsGA = deckHandler.deckCardsRequest();
        if(deckCardsGA!=null)
        	viewDeckCardsGraphicsAggregations(deckCardsGA);
        
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.deck_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
		//FIXME non funziona la ricerca
//		SearchManager SManager =  (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        MenuItem searchMenuItem = menu.findItem(R.id.searchDeck);
//        SearchView searchViewAction = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
//        ComponentName cn = getActivity().getComponentName();
//        searchViewAction.setSearchableInfo(SManager.getSearchableInfo(getActivity().getComponentName()));
		
	}
	
	public void viewGraphicsAggregations(final List<GraphicalAggregation> graphicalsAggregations) {
		gridView = (GridView) getView().findViewById(R.id.gridview2);
        gridView.setAdapter(new GraphicalAggregationAdapter(this.getActivity(), graphicalsAggregations));
        gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				List<GraphicalAggregation> deckCardsGA = deckHandler.modifyDeckRequest(graphicalsAggregations.get(position).getName(), 0, getActivity());
				deleteItemList();
				((DeckActivity) getActivity()).viewNumCards(deckCardsGA);
//				viewNumCards(deckCardsGA);
				viewDeckCardsGraphicsAggregations(deckCardsGA);				
			}
		});

        gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				CompleteTextualAggregation completeTextualAggregation = viewHandler.completeInfoRequest(graphicalsAggregations.get(position).getName());			
				onDeckListener.onCardSelected(completeTextualAggregation);
				return true;
			}
        	
		});
	}
	
	public void viewDeckCardsGraphicsAggregations(final List<GraphicalAggregation> graphicalsAggregations) {
		LinearLayout view = (LinearLayout) getActivity().findViewById(R.id.deckCards);
		int density = getDensityName();	
		for(GraphicalAggregation ga : graphicalsAggregations) {
			LinearLayout ll = new LinearLayout(getActivity());
			ll.setOrientation(LinearLayout.VERTICAL);
			LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			llp.setMargins(2, 0, 2, 0);
			ll.setLayoutParams(llp);
			
			FrameLayout fl = new FrameLayout(getActivity());
			fl.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			
			ImageView image = new ImageView(getActivity());
			image.setVisibility(View.VISIBLE);
			image.setImageResource(ga.getImage());
			image.setTag(ga.getName());
			image.setLayoutParams(new LayoutParams(160, LayoutParams.WRAP_CONTENT));
			image.setLayoutParams(new LayoutParams(density, LayoutParams.WRAP_CONTENT));
			image.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					List<GraphicalAggregation> deckCardsGA = deckHandler.modifyDeckRequest(v.getTag().toString(), 1, getActivity());
					deleteItemList();
					viewDeckCardsGraphicsAggregations(deckCardsGA);
					((DeckActivity) getActivity()).viewNumCards(deckCardsGA);
//					viewNumCards(graphicalsAggregations);
				}
			});
			if(ga.getOccurence()>1) {
				TextView tx = new TextView(getActivity());	
			    tx.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			    tx.setGravity(Gravity.BOTTOM | Gravity.CENTER);
			    tx.setBackgroundColor(Color.parseColor("#770000FF"));
			    tx.setTextColor(Color.RED);
			    tx.setText(Integer.toString(ga.getOccurence()));
			    fl.addView(image);	
			    fl.addView(tx);
			} else {
				fl.addView(image);
			}
			
		    ll.addView(fl);
		    
			view.addView(ll);
		}
	}
	
//	private void viewNumCards(List<GraphicalAggregation> graphicalsAggregations) {
//		int numCards = 0;
//		for(GraphicalAggregation ga : graphicalsAggregations) {
//			numCards += ga.getOccurence();
//		}
//		getActivity().setTitle(Integer.toString(numCards) + "/30");
//	}
	
	private int getDensityName() {
	    float density = getResources().getDisplayMetrics().density;
	    if (density >= 4.0) {
	        return 240;
	    } 
	    if (density >= 3.0) {
	        return 200;
	    }
	    if (density >= 2.0) {
	        return 160;
	    }
	    if (density >= 1.5) {
	        return 120;
	    }
	    if (density >= 1.0) {
	        return 90;
	    }
	    return 50;
	}
	
	private void deleteItemList() {
		LinearLayout view = (LinearLayout) getActivity().findViewById(R.id.deckCards);
		if(view.getChildCount()>0)
			view.removeAllViews();
	}

}
