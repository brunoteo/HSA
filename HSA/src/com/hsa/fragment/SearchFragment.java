package com.hsa.fragment;

import java.util.List;

import com.hsa.R;
import com.hsa.adapter.GraphicalAggregationAdapter;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.Card;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.manager.SaveManager;
import com.hsa.manager.SearchManager;
import com.hsa.manager.ViewManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchFragment extends Fragment{
	
	GridView gridView;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
               
        return rootView;
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        HSADatabaseHelper dbHelper = new HSADatabaseHelper(this.getActivity());
//        SearchManager searchManager = new SearchManager(dbHelper);
//        int emptyDB = searchManager.search(null).size();
//        if(emptyDB==0) {
//            SaveManager saveManager = new SaveManager(dbHelper);
//            saveManager.fillDB(); // TODO spostare il fill nel MainActivity
//        }
        List<Card> cards = searchManager.search(null);
        ViewManager viewManager = new ViewManager(dbHelper);
        List<GraphicalAggregation> graphicalsAggregations = viewManager.generateGraphicalsAggregations(cards, this.getActivity());
        gridView = (GridView) getView().findViewById(R.id.gridView1);
        gridView.setAdapter(new GraphicalAggregationAdapter(this.getActivity(), graphicalsAggregations));
        
//        
//        TextView txt=(TextView) getView().findViewById(R.id.pippo);  
//        txt.setText(Integer.toString(cards.size())); 
	}
}
