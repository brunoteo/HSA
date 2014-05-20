package com.hsa.activity;

import java.util.List;

import com.hsa.R;
import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.aggregation.PartialTextualAggregation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PartialTextualAggregationAdapter extends BaseAdapter{

	private final List<PartialTextualAggregation> partialTextualAggregations;
	private LayoutInflater inflater;
	
	public PartialTextualAggregationAdapter(Context context, List<PartialTextualAggregation> partials){
		this.inflater = LayoutInflater.from(context);
		this.partialTextualAggregations = partials;
	}
	
	@Override
	public int getCount() {
		return partialTextualAggregations.size();
	}

	@Override
	public Object getItem(int position) {
		return partialTextualAggregations.get(position);
	}

	@Override
	public long getItemId(int position) {
		return -1;
	}

	@Override
	public View getView(int i, View convertView, ViewGroup parent) {
		View v = convertView;
        TextView trackCost;
        TextView trackName;
        TextView trackProb;
        TextView trackNumb;

        if(v == null)
        {
           v = inflater.inflate(R.layout.list_view_track, parent, false);
           v.setTag(R.id.trackCost, v.findViewById(R.id.trackCost));
           v.setTag(R.id.trackName, v.findViewById(R.id.trackName));
           v.setTag(R.id.trackProb, v.findViewById(R.id.trackProb));
           v.setTag(R.id.trackNumb, v.findViewById(R.id.trackNumb));
        }
        
        trackCost = (TextView)v.getTag(R.id.trackCost);
        trackName = (TextView)v.getTag(R.id.trackName);
        trackProb = (TextView)v.getTag(R.id.trackProb);
        trackNumb = (TextView)v.getTag(R.id.trackNumb);

        PartialTextualAggregation partialTextualAggregation = (PartialTextualAggregation)getItem(i);
        
        trackCost.setText(partialTextualAggregation.getCost());
        trackName.setText(partialTextualAggregation.getName() + "\n");
        trackProb.setText(Integer.toString(partialTextualAggregation.getProbability()) + "%");
        trackNumb.setText("#" + Integer.toString(partialTextualAggregation.getOccurrences()));
        
        return v;
	}

	
	
}
