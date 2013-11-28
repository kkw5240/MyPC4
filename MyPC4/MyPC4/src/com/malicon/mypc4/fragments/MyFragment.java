package com.malicon.mypc4.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.malicon.mypc4.Part;
import com.malicon.mypc4.R;
import com.malicon.mypc4.ListAdapter;

public class MyFragment extends Fragment {
	private ListAdapter listAdapter =  null;
	private ArrayList<Part> parts = null;
	
	public MyFragment(ArrayList<Part> parts) {
		this.parts = parts;  
		//setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		//if (savedInstanceState != null)
		//	listAdapter = savedInstanceState.getParcelable("listAdapter");
		View v  = inflater.inflate(R.layout.list,container,false);
		ListView lv = (ListView)(v.findViewById(android.R.id.list));
		listAdapter = new ListAdapter(getActivity(), parts);
		lv.setAdapter(listAdapter);
		
		return v;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//outState.putParcelable("listAdapter", listAdapter);
	}	
	public void notifyList() {
		listAdapter.notifyDataSetChanged();
	}
}