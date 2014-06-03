package com.demo.app.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.demo.app.bean.AppInfo;

public class AppGetter {
	static final String TAG = "AppGetter";
	public static final int FILTER_ALL_APP = 0;
    public static final int FILTER_SYSTEM_APP = 1;
    public static final int FILTER_THIRD_APP = 2;
    public static final int FILTER_SDCARD_APP = 3;
    
	private int filter = FILTER_THIRD_APP; 
	
	private static AppGetter instance;
	private PackageManager pm;
	private Context m_context;
	
	public static AppGetter getInstance(Context context) {
		if (instance == null) {
			synchronized (AppGetter.class) {
				if (instance == null)
					instance = new AppGetter(context);
			}
		}
		return instance;
	}
	
	private AppGetter(Context context) {
		m_context = context;
		pm = context.getPackageManager();
	}
	
	public List<AppInfo> getAppList() {
        List<AppInfo> appList = new ArrayList<AppInfo>();
        
        List<ApplicationInfo> listAppcations = pm  
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);  
        Collections.sort(listAppcations,  
                new ApplicationInfo.DisplayNameComparator(pm));

        switch (filter) {  
        case FILTER_ALL_APP:
            break;  
        case FILTER_SYSTEM_APP:
            break; 
        case FILTER_THIRD_APP:  
            for (ApplicationInfo app : listAppcations) {  
                //not system app 
                if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {  
                	appList.add(getAppInfo(app));  
                }   
                //system app which has been changed to the third party app 
                else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0){  
                	appList.add(getAppInfo(app));  
                }  
            }  
            break;  
        case FILTER_SDCARD_APP:
            for (ApplicationInfo app : listAppcations) {  
                if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {  
                	appList.add(getAppInfo(app));  
                }  
            }  
            break; 
        default:  
        	break;  
        }  
        
        return appList;
    }
	
	private AppInfo getAppInfo(ApplicationInfo app) {  
        AppInfo appInfo = new AppInfo();  
        appInfo.setAppLabel((String) app.loadLabel(pm));  
        appInfo.setAppIcon(app.loadIcon(pm));  
        appInfo.setPkgName(app.packageName);  
        Log.d(TAG, app.packageName);
        return appInfo;  
    }
	
	public AppInfo findAppInfoByPackageName(List<AppInfo> appList, String packageName){
		AppInfo appInfo = null;
		for(AppInfo app : appList){
			if(app.getPkgName().equalsIgnoreCase(packageName)){
				appInfo = app;
			}
		}
		return appInfo;
	}
	
	public int getLaunchCountByPackageName(String packageName){
		int launchCount = 0;
		List<ApplicationInfo> listAppcations = pm  
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		for(ApplicationInfo app : listAppcations){
			if(app.packageName.equalsIgnoreCase(packageName)){
				launchCount = getLaunchCount(app);
			}
		}
		return launchCount;
	}
	
	public int getLaunchCount(ApplicationInfo app){
		int launchCount = 0;

		
		return launchCount;
	}
}
