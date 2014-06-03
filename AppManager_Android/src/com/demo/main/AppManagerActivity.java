package com.demo.main;

import com.demo.app.broadcast.UserPresentReceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class AppManagerActivity extends Activity {
	private static final String TAG = "AppManagerActivity"; 
	private BroadcastReceiver receiver;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_app_manager);
        
		registerUserPresentReceiver();
	}
	
	@Override
	public void onDestroy(){
		unregisterUserPresentReceiver();
	}
	
	private void registerUserPresentReceiver(){
		receiver = new UserPresentReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Intent.ACTION_USER_PRESENT);
		this.registerReceiver(receiver, intentFilter);
	}
	
	private void unregisterUserPresentReceiver(){
		if(receiver!=null){
			this.unregisterReceiver(receiver);
		}
	}
}
