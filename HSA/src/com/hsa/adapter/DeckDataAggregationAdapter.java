package com.hsa.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hsa.R;
import com.hsa.aggregation.DeckDataAggregation;

public class DeckDataAggregationAdapter extends BaseAdapter{

	private final List<DeckDataAggregation> deckDataAggregations;
	private LayoutInflater inflater;
	
	public DeckDataAggregationAdapter(Context context, List<DeckDataAggregation> dda){
		this.inflater = LayoutInflater.from(context);
		this.deckDataAggregations = dda;
	}
	
	@Override
	public int getCount() {
		return deckDataAggregations.size();
	}

	@Override
	public Object getItem(int i) {
		return deckDataAggregations.get(i);
	}

	@Override
	public long getItemId(int position) {
		return -1;
	}

	@Override
	public View getView(int i, View view, ViewGroup parent) {
		View v = view;
        TextView deckname;
        TextView deckNumber;
        TextView deckDate;
        TextView deckClass;

        if(v == null)
        {
           v = inflater.inflate(R.layout.list_view_item, parent, false);
           v.setTag(R.id.deckName, v.findViewById(R.id.deckName));
           v.setTag(R.id.deckNumber, v.findViewById(R.id.deckNumber));
           v.setTag(R.id.deckDate, v.findViewById(R.id.deckDate));
           v.setTag(R.id.deckClass, v.findViewById(R.id.deckClass));
        }
        
        deckname = (TextView)v.getTag(R.id.deckName);
        deckNumber = (TextView)v.getTag(R.id.deckNumber);
        deckDate = (TextView)v.getTag(R.id.deckDate);
        deckClass = (TextView)v.getTag(R.id.deckClass);

        DeckDataAggregation deckDataAggregation = (DeckDataAggregation)getItem(i);
        
        deckname.setText(deckDataAggregation.getName());
        deckNumber.setText(Integer.toString(deckDataAggregation.getCardNumber()) + "/30\n");
        deckDate.setText(deckDataAggregation.getDate());
        deckClass.setText(deckDataAggregation.getClassName());
        
        return v;
	}
}
