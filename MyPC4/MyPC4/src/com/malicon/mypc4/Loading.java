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

				finish();
			}
		};
		
		
		Network ifo = new Network();
		ifo.start();
	}
	
}
