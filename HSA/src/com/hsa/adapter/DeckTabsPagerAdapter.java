package com.hsa.adapter;

import com.hsa.fragment.DeckFragment;
import com.hsa.fragment.DeckInformationFragment;
import com.hsa.fragment.NoteFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DeckTabsPagerAdapter extends FragmentPagerAdapter{

	public DeckTabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
        case 0:
            return new NoteFragment();
        case 1:
            return new DeckFragment();
        case 2:
            return new DeckInformationFragment();
        }
 
        return null;
	}

	@Override
	public int getCount() {
		return 3;
	}
	
	

}
