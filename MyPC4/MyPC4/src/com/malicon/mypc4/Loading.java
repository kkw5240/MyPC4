package com.malicon.mypc4;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

public class Loading extends Activity {
	static Handler uptext = null;
	static Handler changemain = null; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loading);


		changemain = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				//if(AllParts.getInstance() != null)/*
				AllParts allparts = AllParts.getInstance();
				
				for(int i=0;i<9;i++)
					if(allparts.getListAdapter(i) != null)
						allparts.getListAdapter(i).notifyDataSetChanged();
						//MainActivity.tabs[0].la.notifyDataSetChanged();
				//if(MainActivity.tabs[1].la != null)
				//	MainActivity.tabs[1].la.notifyDataSetChanged();*/
				finish();
			}
		};
		
		
		Network ifo = new Network();
		ifo.start();
	}
	
}
