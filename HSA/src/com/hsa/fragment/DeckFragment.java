package com.hsa.fragment;

import com.hsa.R;
import com.hsa.activity.DeckActivity;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.ViewHandler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class DeckFragment extends Fragment{
	
	ViewHandler viewHandler;
	
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
        viewHandler = ViewHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
        viewHandler.viewDeckRequest(((DeckActivity) getActivity()).getDeckDataAggregation());
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.deck_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
		
	}

}
