package com.hsa.adapter;

import java.util.List;

import com.hsa.R;
import com.hsa.aggregation.GraphicalAggregation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GraphicalAggregationAdapter extends BaseAdapter{

	private Context context;
	private final List<GraphicalAggregation> ga;
 
	public GraphicalAggregationAdapter(Context context, List<GraphicalAggregation> ga) {
		this.context = context;
		this.ga = ga;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ga.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View gridView;
	 
		if (convertView == null) { 
			gridView = new View(context);
				// get layout from mobile.xml
				gridView = inflater.inflate(R.layout.graphical_aggregation, null);
				
				// set value into textview
				ImageView imageView = (ImageView) gridView
						.findViewById(R.id.imageView1);
				imageView.setImageResource(ga.get(position).getImage());
	 
			} else {
				gridView = (View) convertView;
			}
	 
			return gridView;
		}

}
