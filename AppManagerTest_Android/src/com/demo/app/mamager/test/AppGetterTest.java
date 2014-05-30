package com.demo.app.mamager.test;

import java.util.List;

import junit.framework.Assert;

import com.demo.app.bean.AppInfo;
import com.demo.app.manager.AppGetter;

import android.test.AndroidTestCase;

public class AppGetterTest extends AndroidTestCase{
	public static final String TAG = "AppGetterTest";
	AppGetter AppGetter;
	
	protected void setUp() throws Exception {
		super.setUp();
		AppGetter = AppGetter.getInstance(mContext);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetAppList() throws Exception {
		List<AppInfo> appList = AppGetter.getAppList();
		Assert.assertNotNull(appList);
	}
}
