package com.demo.main;

import java.util.List;

import com.demo.app.adapter.AppListAdapter;
import com.demo.app.bean.AppInfo;
import com.demo.app.broadcast.UserPresentReceiver;
import com.demo.app.manager.AppGetter;
import com.demo.app.viewHolder.AppItemHolder;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AppManagerActivity extends Activity {
	private static final String TAG = "AppManagerActivity";
	private AppGetter appGetter;
	private List<AppInfo> appList;
	private AppListAdapter adapter;
	
	private BroadcastReceiver receiver;
	
	TextView tv = null;  
    ListView lv = null; 
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_app_manager);
        
        tv=(TextView)findViewById(R.id.tv_no_data);
        
		lv=(ListView)findViewById(R.id.appList);
		lv.setEmptyView(tv);
		lv.setOnItemClickListener(new appItemClickListener());
		
        initAppList();
		registerUserPresentReceiver();
	}
	
	public void initAppList() {  
		appGetter = AppGetter.getInstance(this);
		appList = appGetter.getAppList();
        if (adapter == null) {  
            adapter = new AppListAdapter(this, appList);  
            lv.setAdapter(adapter);  
        } else {  
            adapter.notifyDataSetChanged();  
        }   
    }
	
	@Override
	public void onDestroy(){
		super.onDestroy(); 
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
	
	private class appItemClickListener implements OnItemClickListener
    {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			AppItemHolder itemView=(AppItemHolder)arg1.getTag();
			itemView.cb.toggle();
			if(itemView.cb.isChecked()) {
				appList.get(arg2).setIsSelected(true);
			} else {
				appList.get(arg2).setIsSelected(false);
			}
		}
    	
    }
}
