package com.demo.app.adapter;

import java.util.List;

import com.demo.app.bean.AppInfo;
import com.demo.app.viewHolder.AppItemHolder;
import com.demo.main.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class AppListAdapter extends BaseAdapter {
	private Context mContext;
	private List<AppInfo> mList;    
	
	public AppListAdapter(Context context, List<AppInfo> appList) {    
		mContext = context;
        mList = appList;  
    }
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public AppInfo getItem(int arg0) {
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) { 
		AppItemHolder holder;  
        if (view == null || (holder = (AppItemHolder) view.getTag()) == null) {  
            view = View.inflate(mContext, R.layout.app_item,  
                    null);  
            holder = new AppItemHolder();  
            holder.tv = (TextView) view.findViewById(R.id.item_tv); 
            holder.tv.setSingleLine(true);
            holder.cb = (CheckBox) view.findViewById(R.id.item_cb);  
            view.setTag(holder);  
        } else {
        	holder=(AppItemHolder)view.getTag();
		} 
        AppInfo item = getItem(position);  
        holder.tv.setText(item.getAppLabel());  
        holder.cb.setChecked(item.getIsSelected());  
        return view; 
	}

}
