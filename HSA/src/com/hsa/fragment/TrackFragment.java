package com.hsa.fragment;

import java.util.List;

import com.hsa.R;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.ViewHandler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TrackFragment extends Fragment{

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
        //viewHandler = ViewHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
        //List<GraphicalAggregation> graphicalsAggregations = viewHandler.searchRequest(null, this.getActivity());
        //viewGraphicsAggregations(graphicalsAggregations);
	}
}
