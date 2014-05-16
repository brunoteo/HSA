package com.hsa.fragment;

import java.util.List;

import com.hsa.MainActivity;
import com.hsa.R;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.manager.ViewManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DecksFragment extends Fragment{

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_decks, container, false);
         
        return rootView;
    }
	
}
