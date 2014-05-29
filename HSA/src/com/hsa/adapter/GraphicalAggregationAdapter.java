package com.hsa.adapter;

import java.util.List;

import com.hsa.aggregation.GraphicalAggregation;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GraphicalAggregationAdapter extends BaseAdapter{

	private final List<GraphicalAggregation> grapichalsAggregations;
	private Context context;
 
	public GraphicalAggregationAdapter(Context context, List<GraphicalAggregation> ga) {
		LayoutInflater.from(context);
		this.grapichalsAggregations = ga;
		this.context = context;
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
//	public View getView(int i, View view, ViewGroup viewGroup) {
//		
//		 View v = view;
//         ImageView picture;
//         TextView name;
//
//         if(v == null)
//         {
//            v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
//            v.setTag(R.id.picture, v.findViewById(R.id.picture));
//            v.setTag(R.id.text, v.findViewById(R.id.text));
//         }
//
//         WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//         Display display = wm.getDefaultDisplay();
//         Point size = new Point();
//         size.set(display.getWidth(), display.getHeight());
//         int width = size.x;
//         int width_effective = (int) width/3;
//         int height = (int)(width/(3*0.66006600));
//         picture = (ImageView)v.getTag(R.id.picture);
//         name = (TextView)v.getTag(R.id.text);
//
//         GraphicalAggregation graphicalAggregation = (GraphicalAggregation)getItem(i);
//         
//         picture.setImageResource(graphicalAggregation.getImage());
//         picture.setLayoutParams(new GridView.LayoutParams(width_effective, height));
//         if(graphicalAggregation.getOccurence()!=0) {
//        	 name.setText(Integer.toString(graphicalAggregation.getOccurence()));
//             name.setBackgroundColor(Color.parseColor("#55000000"));
//         }
//         
//         return v;
//	}

    public View getView(int position, View convertView, ViewGroup parent) {
    	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        size.set(display.getWidth(), display.getHeight());
        int width = size.x;
        int width_effective = (int) width/3;
        int height = (int)(width/(3*0.66006600));
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(grapichalsAggregations.get(position).getImage());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(width_effective, height));
        return imageView;
    }

}
