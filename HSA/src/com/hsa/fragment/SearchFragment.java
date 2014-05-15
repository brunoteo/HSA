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
import com.hsa.bean.Card;
import com.hsa.bean.SearchCriterion;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.manager.SaveManager;
import com.hsa.manager.SearchManager;
import com.hsa.manager.ViewManager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class SearchFragment extends Fragment{
	
	GridView gridView;
	private ViewManager viewManager;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
               
        return rootView;
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        viewManager = new ViewManager(((MainActivity) getActivity()).getDbHelper());
        List<GraphicalAggregation> graphicalsAggregations = viewManager.searchRequest(null, this.getActivity());
        viewGraphicsAggregations(graphicalsAggregations);

	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.search_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
		
	}
	
	public void viewGraphicsAggregations(final List<GraphicalAggregation> graphicalsAggregations) {
		gridView = (GridView) getView().findViewById(R.id.gridview1);
        gridView.setAdapter(new GraphicalAggregationAdapter(this.getActivity(), graphicalsAggregations));
        gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				CompleteTextualAggregation completeTextualAggregation = viewManager.completeInfoRequest(graphicalsAggregations.get(position));
				return true;
			}
        	
		});
	}
}
