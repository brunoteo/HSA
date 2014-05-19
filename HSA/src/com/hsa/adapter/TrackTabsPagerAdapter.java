package com.hsa.adapter;

import com.hsa.fragment.DeckFragment;
import com.hsa.fragment.DeckInformationFragment;
import com.hsa.fragment.NoteFragment;
import com.hsa.fragment.TrackFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TrackTabsPagerAdapter extends FragmentPagerAdapter{

	public TrackTabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
        case 0:
            return new NoteFragment();
        case 1:
            return new TrackFragment();
        }
 
        return null;
	}

	@Override
	public int getCount() {
		return 2;
	}

}
