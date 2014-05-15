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

	private final List<GraphicalAggregation> grapichalsAggregations;
	private LayoutInflater inflater;
 
	public GraphicalAggregationAdapter(Context context, List<GraphicalAggregation> ga) {
		this.inflater = LayoutInflater.from(context);
		this.grapichalsAggregations = ga;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return grapichalsAggregations.size();
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

//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		
//		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View gridView;
//	 
//		if (convertView == null) { 
//			gridView = new View(context);
//			// get layout from mobile.xml
//			gridView = inflater.inflate(R.layout.graphical_aggregation, null);
//				
//			// set value into textview
//			ImageView imageView = (ImageView) gridView
//						.findViewById(R.id.imageView1);
//			imageView.setImageResource(grapichalsAggregations.get(position).getImage());
//	 
//		} else {
//			gridView = (View) convertView;
//		}
//	 
//		return gridView;
//	}
	
	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		
		 View v = view;
         ImageView picture;
         TextView name;

         if(v == null)
         {
            v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
         }

         picture = (ImageView)v.getTag(R.id.picture);
         name = (TextView)v.getTag(R.id.text);

         GraphicalAggregation graphicalAggregation = grapichalsAggregations.get(i);

         picture.setImageResource(graphicalAggregation.getImage());
         name.setText("3");

         return v;
	}

}
