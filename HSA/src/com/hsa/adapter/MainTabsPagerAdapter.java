package com.hsa.adapter;

import com.hsa.fragment.DecksFragment;
import com.hsa.fragment.NewsFragment;
import com.hsa.fragment.SearchFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainTabsPagerAdapter extends FragmentPagerAdapter{

	public MainTabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		
		switch (index) {
        case 0:
            return new NewsFragment();
        case 1:
            return new SearchFragment();
        case 2:
            return new DecksFragment();
        }
 
        return null;
	}

	@Override
	public int getCount() {

		return 3;
	}

}
