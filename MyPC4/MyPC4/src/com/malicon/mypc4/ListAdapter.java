package com.malicon.mypc4;

import java.util.ArrayList;



import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter implements Parcelable {
	private ArrayList<Part> parts = null;
	AllParts allParts = null;
	//Tab tab = null;
	private Context c = null;
	public ListAdapter(Context c, ArrayList<Part> parts) {
		this.c = c;
		parts = new ArrayList<Part>();
		this.parts = parts;
		//tab = new Tab();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(parts);
		//dest.writeValue(tab.getParts());
	}
	
	@Override
	public int getCount() {
		return parts.size();
		//return tab.getParts().size();
	}
	
	@Override
	public Object getItem(int index) {
		if(index >= 0 && index < parts.size())
			return parts.get(index);
		//if(index >= 0 && index < this.getCount())
			//return tab.getParts().get(index);
		return null;
	}
	
	@Override
	public long getItemId(int index) {
		return index;
	}
	
	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
		View v = convertview;
		TextView t = null;
		if(v == null){
			LayoutInflater inflater = LayoutInflater.from(c);
			v = inflater.inflate(R.layout.itemlayout, null);
			t = (TextView)v.findViewById(R.id.itemName);
		}else{
		}
		
		if(t != null)
			t.setText(parts.get(position).name);
		
		return v;
	}

}
