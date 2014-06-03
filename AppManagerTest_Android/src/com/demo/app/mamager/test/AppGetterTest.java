package com.demo.app.mamager.test;

import java.util.List;

import junit.framework.Assert;

import com.demo.app.bean.AppInfo;
import com.demo.app.manager.AppGetter;

import android.test.AndroidTestCase;

public class AppGetterTest extends AndroidTestCase{
	public static final String TAG = "AppGetterTest";
	AppGetter appGetter;
	
	protected void setUp() throws Exception {
		super.setUp();
		appGetter = AppGetter.getInstance(mContext);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetAppList() throws Exception {
		List<AppInfo> appList = appGetter.getAppList();
		Assert.assertNotNull(appList);
	}
	
	public void testFindAppInfoByPackageName() throws Exception {
		List<AppInfo> appList = appGetter.getAppList();
		Assert.assertNotNull(appList);
		AppInfo appManagerTest = appGetter.findAppInfoByPackageName(appList, "com.demo.app.manager.test");
		Assert.assertNotNull(appManagerTest);
	}
}
