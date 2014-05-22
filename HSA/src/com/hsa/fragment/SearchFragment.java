package com.hsa.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsa.MainActivity;
import com.hsa.R;
import com.hsa.adapter.GraphicalAggregationAdapter;
import com.hsa.aggregation.CompleteTextualAggregation;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.SearchCriterion;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.ViewHandler;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.location.Criteria;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{
	
	private HSADatabaseHelper dbHelper;
	
	private ViewHandler viewHandler;
	
	private OnSearchListener onSearchListener;
	
	private GridView gridView;
	
	public interface OnSearchListener{
		public void onCardSelected(CompleteTextualAggregation completeAggregation);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
               
        return rootView;
    }
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try{
			onSearchListener = (OnSearchListener) activity;
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
        
        List<GraphicalAggregation> graphicalsAggregations = viewHandler.cardsSearchRequest(null, this.getActivity());
        viewGraphicsAggregations(graphicalsAggregations);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.search_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchView searchViewAction = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchViewAction.setOnQueryTextListener(this);
		
	}

	
	public void viewGraphicsAggregations(final List<GraphicalAggregation> graphicalsAggregations) {
		gridView = (GridView) getView().findViewById(R.id.gridview1);
        gridView.setAdapter(new GraphicalAggregationAdapter(this.getActivity(), graphicalsAggregations));
        gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				CompleteTextualAggregation completeTextualAggregation = viewHandler.completeInfoRequest(graphicalsAggregations.get(position).getName());			
				onSearchListener.onCardSelected(completeTextualAggregation);
				return true;
			}
        	
		});
	}

	@Override
	public boolean onQueryTextChange(String query) {

		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		((MainActivity) getActivity()).setNameFilter(query);
	    Map<String, ArrayList<String>> filters = new HashMap<String, ArrayList<String>>();
	    if(((MainActivity) getActivity()).getClassFilters() != null) {
	    	if(((MainActivity) getActivity()).getClassFilters().size() != 0) filters.put("className", new ArrayList<String>(((MainActivity) getActivity()).getClassFilters()));
	    }
	    if(((MainActivity) getActivity()).getCostFilters() != null){
	    	if(((MainActivity) getActivity()).getCostFilters().size() != 0) filters.put("cost", new ArrayList<String>(((MainActivity) getActivity()).getCostFilters()));
	    }
	    if(((MainActivity) getActivity()).getRarityFilters() != null){
	      	if(((MainActivity) getActivity()).getRarityFilters().size() != 0) filters.put("rarity", new ArrayList<String>(((MainActivity) getActivity()).getRarityFilters()));
	    }
	    if(((MainActivity) getActivity()).getTypeFilters() != null){
	      	if(((MainActivity) getActivity()).getTypeFilters().size() != 0) filters.put("type", new ArrayList<String>(((MainActivity) getActivity()).getTypeFilters()));
	    }
		SearchCriterion criterion = new SearchCriterion(query, filters);
		List<GraphicalAggregation> graphicalsAggregations = ViewHandler.getInstance(dbHelper).cardsSearchRequest(criterion, getActivity());
		viewGraphicsAggregations(graphicalsAggregations);
		return true;
	}

}
