package com.hsa.adapter;

import java.util.List;

import com.hsa.R;
import com.hsa.aggregation.GraphicalAggregation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GraphicalAggregationAdapter extends BaseAdapter{

	private final List<GraphicalAggregation> grapichalsAggregations;
	private LayoutInflater inflater;
	//private Context context;
 
	public GraphicalAggregationAdapter(Context context, List<GraphicalAggregation> ga) {
		this.inflater = LayoutInflater.from(context);
		this.grapichalsAggregations = ga;
		//this.context = context;
	}
	
	@Override
	public int getCount() {
		return grapichalsAggregations.size();
	}

	@Override
	public Object getItem(int i) {

		return grapichalsAggregations.get(i);
	}

	@Override
	public long getItemId(int i) {
		return grapichalsAggregations.get(i).getImage();
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

         GraphicalAggregation graphicalAggregation = (GraphicalAggregation)getItem(i);
         //GraphicalAggregation graphicalAggregation = grapichalsAggregations.get(i);
         
         picture.setImageResource(graphicalAggregation.getImage());
         if(graphicalAggregation.getOccurence()==0) {
        	 name.setBackgroundColor(Color.parseColor("#07000000"));
         } else {
        	 name.setText(Integer.toString(graphicalAggregation.getOccurence()));
         }
         
         return v;
	}

//	@Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//		int size = (int) context.getResources().getDimension(R.dimen.image_size);
//        ImageView imageView = new ImageView(context);
//        imageView.setImageResource(grapichalsAggregations.get(position).getImage());
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setLayoutParams(new GridView.LayoutParams(size, (303/200) * size));
//        //imageView.setLayoutParams(new GridView.LayoutParams((int)context.getResources().getDimension(R.dimen.width),(int)context.getResources().getDimension(R.dimen.width)));
//        return imageView;
//    }

//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView = new ImageView(context);
//        imageView.setImageResource(grapichalsAggregations.get(position).getImage());
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setLayoutParams(new GridView.LayoutParams(200, 150));
//        return imageView;
//    }

}
