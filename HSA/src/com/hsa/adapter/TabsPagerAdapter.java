package com.hsa.adapter;

import com.hsa.fragment.DecksFragment;
import com.hsa.fragment.NewsFragment;
import com.hsa.fragment.SearchFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		
		switch (index) {
        case 0:
            // Top Rated fragment activity
            return new NewsFragment();
        case 1:
            // Games fragment activity
            return new SearchFragment();
        case 2:
            // Movies fragment activity
            return new DecksFragment();
        }
 
        return null;
	}

	@Override
	public int getCount() {

		return 3;
	}

}
