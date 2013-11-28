package com.malicon.mypc4;

import java.util.ArrayList;

public class AllParts {
	private static AllParts allParts = null;
	private ArrayList<Part> parts[] = null;
	private ListAdapter lv[] = null;
	@SuppressWarnings("unchecked")
	private AllParts() {
		parts = new ArrayList[9];
		lv = new ListAdapter[9];
		for(int i=0; i<9; i++)
			parts[i] = new ArrayList<Part>();
	}
	public static synchronized AllParts getInstance() {
		if( allParts == null ) {
			allParts = new AllParts();
		}
		return allParts;
	}
	public ArrayList<Part> getParts(int index) {
		return parts[index];
	}
	public void setListAdapter(ListAdapter lv,int index){
		this.lv[index] = lv;
	}
	public ListAdapter getListAdapter(int index){
		return lv[index];
	}
}
