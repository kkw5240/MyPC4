package com.malicon.mypc4;

import java.util.ArrayList;

import android.content.Context;
//import android.os.Parcel;
//import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter{// implements Parcelable {
	private ArrayList<Part> parts = null;
	private AllParts allParts = null;
	private LayoutInflater inflater = null;
	private Context c = null;
	
	public ListAdapter(Context c, ArrayList<Part> parts) {
		inflater = LayoutInflater.from(c);
		this.c = c;
		this.parts = parts;
	}
	
//	@Override
//	public int describeContents() {
//		return 0;
//	}
//
//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//		dest.writeValue(parts);
//	}
	
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
		ViewHolder vh = null;
		if(v == null){
			vh = new ViewHolder();
			v = inflater.inflate(R.layout.itemlayout, null);
			vh.name = (TextView)v.findViewById(R.id.itemName);
			vh.attribute = (TextView)v.findViewById(R.id.itemPerformance);
			vh.price = (TextView)v.findViewById(R.id.itemPrice);
			v.setTag(vh);
		}else{
			vh = (ViewHolder)v.getTag();
		}
		
		if(vh.name != null)
			vh.name.setText(parts.get(position).name);
		if(vh.attribute != null)
			vh.attribute.setText(parts.get(position).attribute);
		if(vh.price != null)
			vh.price.setText(parts.get(position).price+"원");
		
		return v;
	}
	class ViewHolder {
		//ImageView icon;
		TextView name;
		TextView attribute;
		TextView price;
		CheckBox cb;
	}

}
