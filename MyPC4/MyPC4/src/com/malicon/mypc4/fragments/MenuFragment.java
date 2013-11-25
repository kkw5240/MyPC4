package com.malicon.mypc4.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.malicon.mypc4.R;

public class MenuFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout./*list_page*/list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] pNames = getResources().getStringArray(R.array.part_names);
		ArrayAdapter<String> ArrAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout./*itemlayout*/simple_list_item_1, android.R.id.text1, pNames);
		setListAdapter(ArrAdapter);
	}
}
