package com.hsa.fragment;

import java.util.List;

import com.hsa.R;
import com.hsa.bean.Card;
import com.hsa.manager.SearchManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SearchFragment extends Fragment{

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        
        //PROVA DATABASE
        SearchManager searchManager = new SearchManager(getActivity().getApplicationContext());
        List<Card> cards = searchManager.search(null);
        EditText editText = (EditText) getActivity().findViewById(R.id.editText1);
        editText.setText(cards.get(0).getName());
        
        return rootView;
    }
}
