package com.malicon.mypc4;

import java.util.ArrayList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class page extends Fragment{
	ListAdapter la = null;
	private static AllParts t = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//la = new ListAdapter(getActivity());
		t = AllParts.getInstance();
		ArrayList<Part> info = t.getParts(0);
		
		return onCreateList(this, inflater.inflate(R.layout.list_page,container,false), info, la);
	}

	public View onCreateList(page p, View v, ArrayList<Part> info, ListAdapter la){
		ListView lv = (ListView)(v.findViewById(R.id.list_page));
		lv.setAdapter(la = new ListAdapter(p.getActivity(), info));
		return v;
	}
}
