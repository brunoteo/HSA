package com.hsa.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsa.MainActivity;
import com.hsa.R;
import com.hsa.adapter.GraphicalAggregationAdapter;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.Card;
import com.hsa.bean.SearchCriterion;
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
//        List<Card> cards = searchManager.search(null);
//        ViewManager viewManager = new ViewManager(dbHelper);
//        List<GraphicalAggregation> graphicalsAggregations = viewManager.generateGraphicalsAggregations(cards, this.getActivity());
//        gridView = (GridView) getView().findViewById(R.id.gridView1);
//        gridView.setAdapter(new GraphicalAggregationAdapter(this.getActivity(), graphicalsAggregations));
          ViewManager viewManager = new ViewManager(((MainActivity) getActivity()).getDbHelper());
          
//	    Map<String, ArrayList<String>> filters = new HashMap<String, ArrayList<String>>();
//	    ArrayList<String> typeValues = new ArrayList<String>();
//	    typeValues.add("Spell");
//	    filters.put("type", typeValues);
//	    SearchCriterion sc = new SearchCriterion(null, filters);
	    
//      classValues.add("Paladin");
//      ArrayList<String> rarityValues = new ArrayList<String>();
//      rarityValues.add("Epic");
//      filters.put("class", classValues);
//      filters.put("rarity", rarityValues);z
        List<GraphicalAggregation> graphicalsAggregations = viewManager.searchRequest(null, this.getActivity());
        gridView = (GridView) getView().findViewById(R.id.gridview1);
        gridView.setAdapter(new GraphicalAggregationAdapter(this.getActivity(), graphicalsAggregations));
//        Map<String, ArrayList<String>> filters = new HashMap<String, ArrayList<String>>();
//        ArrayList<String> classValues = new ArrayList<String>();
//        classValues.add("Hunter");
//        classValues.add("Paladin");
//        ArrayList<String> rarityValues = new ArrayList<String>();
//        rarityValues.add("Epic");
//        filters.put("class", classValues);
//        filters.put("rarity", rarityValues);
//        String name = "imp";
//        SearchCriterion criterion = new SearchCriterion(name, null);
//        List<GraphicalAggregation> graphicalsAggregations2 = viewManager.searchRequest(criterion, this.getActivity());
//        gridView = (GridView) getView().findViewById(R.id.gridView1);
//        gridView.setAdapter(new GraphicalAggregationAdapter(this.getActivity(), graphicalsAggregations));
        
//        ImageView imageView = (ImageView) getView().findViewById(R.id.theImage);
//		imageView.setImageResource(graphicalsAggregations.get(0).getImage());
//		
//		ImageView imageView2 = (ImageView) getView().findViewById(R.id.theImage2);
//		imageView2.setImageResource(graphicalsAggregations.get(1).getImage());
//		
//		ImageView imageView3 = (ImageView) getView().findViewById(R.id.theImage3);
//		imageView3.setImageResource(graphicalsAggregations.get(2).getImage());

//        
//        TextView txt=(TextView) getView().findViewById(R.id.pippo);  
//        txt.setText(Integer.toString(cards.size())); 
	}
}
