package com.demo.app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class UserPresentReceiver extends BroadcastReceiver {
	private static final String TAG = "UserPresentReceiver";  
	  
    @Override  
    public void onReceive(Context context, Intent intent) {  
        String action = intent.getAction();
        //Toast.makeText(context, action, Toast.LENGTH_LONG).show();
        if(action.equals(Intent.ACTION_USER_PRESENT)){
            Log.d(TAG, "receive unlock");
        }
    } 
}
