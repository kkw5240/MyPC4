package com.malicon.mypc4;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

public class Loading extends Activity {
	static Handler uptext = null;
	static Handler changemain = null; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loading);
		
		/*
		uptext = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				//TextView i = (TextView) findViewById(R.id.LoadingText);
				//i.setText((String)msg.obj);
			}
		};
		*/
		changemain = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				//Intent i = new Intent(Loading.this, MainActivity.class);
				//startActivity(i);
				/*
				if(MainActivity.tabs[0].la != null)
					MainActivity.tabs[0].la.notifyDataSetChanged();
				if(MainActivity.tabs[1].la != null)
					MainActivity.tabs[1].la.notifyDataSetChanged();
				*/
				finish();
			}
		};
		
		/*
		MyPageTab.mypageInfo.clear();
		for(Object tmp : ListAdapter.partIndex){
			((ArrayList<Part>)tmp).clear();
		}*/
		Network ifo = new Network();
		ifo.start();
	}
	
}
