package com.malicon.mypc4;

import java.util.ArrayList;
import java.io.InputStreamReader;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.malicon.mypc4.fragments.MyFragment;

public class PagerAdapter extends FragmentPagerAdapter {
	
	private ArrayList<Fragment> mFragments;
	private String tabName[] = new String[]{
			"MyPage",
			"CPU", 
			"���κ���", 
			"HDD", 
			"SSD", 
			"RAM", 
			"VGA", 
			"POWER", 
			"ODD", 
			"CASE"
	};
	
	public PagerAdapter(FragmentManager fm) {
		super(fm);
		mFragments = new ArrayList<Fragment>();
		for (int i=0 ; i<tabName.length ; i++){
			mFragments.add(new MyFragment());
		}
	}

	@Override
	public int getCount() {	//	Number of Page
		return mFragments.size();
	}

	@Override
	public Fragment getItem(int position) {	//	Get Tab's Fragment
		return mFragments.get(position);
	}
	
	@Override
	public CharSequence getPageTitle(int position) {	// Set Tab Title
		if(position < tabName.length)
			return tabName[position];
		return null;
	}
}