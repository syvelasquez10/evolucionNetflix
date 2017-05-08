// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.PreferenceUtils;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.content.Context;
import com.netflix.mediaclient.util.PermissionUtils;
import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class DebugMenuItems
{
    private final NetflixActivity activity;
    private final String logTag;
    
    public DebugMenuItems(final String logTag, final NetflixActivity activity) {
        this.logTag = logTag;
        this.activity = activity;
    }
    
    private void addBarkerBars(final Menu menu) {
        menu.add((CharSequence)"Show Barker Bars").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$22(this));
    }
    
    private void addCrashItem(final Menu menu) {
        menu.add((CharSequence)"Crash").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$14(this));
    }
    
    private void addCreateAutoLoginToken(final Menu menu) {
        menu.add((CharSequence)"Create AutoLogin Token").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$4(this));
    }
    
    private void addDumpCacheToDiskItem(final Menu menu) {
        menu.add((CharSequence)"Dump Cache to Disk").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$5(this));
    }
    
    private void addDumpHomeLolomoToHtmlItem(final Menu menu) {
        if (!(this.activity instanceof HomeActivity)) {
            return;
        }
        menu.add((CharSequence)"Dump Lolomo to Html").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$6(this, (HomeActivity)this.activity));
    }
    
    private void addDumpPerfData(final Menu menu) {
        menu.add((CharSequence)"Dump Perf Data").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$18(this));
    }
    
    private void addFlushCLEvents(final Menu menu) {
        menu.add((CharSequence)"Push logging events").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$20(this));
    }
    
    private void addFlushDataCacheItem(final Menu menu) {
        menu.add((CharSequence)"Flush Data Cache").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$17(this));
    }
    
    private void addHprofDumpItem(final Menu menu) {
        menu.add((CharSequence)"Dump hprof profile").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$12(this));
    }
    
    private void addKitchensink(final Menu menu) {
        menu.add((CharSequence)"Kitchensink").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$1(this));
    }
    
    private void addLaunchNotificationsActivity(final Menu menu) {
        menu.add((CharSequence)"Launch Notifications Activity").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$3(this));
    }
    
    private void addMakeRefreshCwRemoteCall(final Menu menu) {
        menu.add((CharSequence)"Make refreshCw Remote Call").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$9(this));
    }
    
    private void addMakeRefreshIqRemoteCall(final Menu menu) {
        menu.add((CharSequence)"Make refreshIq Remote Call").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$10(this));
    }
    
    private void addMakeRefreshLolomoRemoteCall(final Menu menu) {
        menu.add((CharSequence)"Make refreshLolomo Remote Call").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$11(this));
    }
    
    private void addResetABConfigData(final Menu menu) {
        menu.add((CharSequence)"Request config data").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$21(this));
    }
    
    private void addRunPrefetchLolomoSchedulerJob(final Menu menu) {
        menu.add((CharSequence)"Run Prefetch Lolomo JobScheduler").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$19(this));
    }
    
    private void addSendCwRefreshBroadcast(final Menu menu) {
        menu.add((CharSequence)"Send Cw Refresh Broadcast").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$8(this));
    }
    
    private void addSendHomeRefreshBroadcast(final Menu menu) {
        menu.add((CharSequence)"Send Home Refresh").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$7(this));
    }
    
    private void addToaster(final Menu menu) {
        menu.add((CharSequence)"Show Playback Advisory").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$23(this));
    }
    
    private void addToggleFetchErrorsItem(final Menu menu) {
        menu.add((CharSequence)"Toggle Fetch Errors").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$16(this));
    }
    
    private void addTraceviewItem(final Menu menu) {
        menu.add((CharSequence)"5s Traceview").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$13(this));
    }
    
    private void beginTraceview() {
        Log.i(this.logTag, "**********************************************************************");
        Log.i(this.logTag, "Starting method trace...");
        Log.i(this.logTag, "**********************************************************************");
        Debug.startMethodTracing("nflx");
        new Handler().postDelayed((Runnable)new DebugMenuItems$15(this), 5000L);
    }
    
    private void launchOnramp(final Menu menu) {
        menu.add((CharSequence)"Launch OnRamp").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$24(this));
    }
    
    private boolean requestExternalFileWritePermission() {
        if (PermissionUtils.shouldRequestPermission((Context)this.activity, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions(this.activity, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 145);
            return false;
        }
        return true;
    }
    
    public void addAPIEnvironmentPicker(final Menu menu) {
        menu.add((CharSequence)"Pick API environment").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$2(this, new String[] { "INT", "TEST" }, PreferenceUtils.getStringPref((Context)this.activity, "api_environment_preference", ""), new String[] { "PROD", "STAGING" }));
    }
    
    public void addItems(final Menu menu) {
        this.addToaster(menu);
        this.addResetABConfigData(menu);
        this.addFlushCLEvents(menu);
        this.addRunPrefetchLolomoSchedulerJob(menu);
        this.launchOnramp(menu);
        this.addBarkerBars(menu);
        this.addDumpPerfData(menu);
        this.addLaunchNotificationsActivity(menu);
        this.addDumpCacheToDiskItem(menu);
        this.addDumpHomeLolomoToHtmlItem(menu);
        this.addHprofDumpItem(menu);
        this.addTraceviewItem(menu);
        this.addToggleFetchErrorsItem(menu);
        this.addFlushDataCacheItem(menu);
        this.addSendHomeRefreshBroadcast(menu);
        this.addSendCwRefreshBroadcast(menu);
        this.addMakeRefreshCwRemoteCall(menu);
        this.addMakeRefreshIqRemoteCall(menu);
        this.addMakeRefreshLolomoRemoteCall(menu);
        this.addCreateAutoLoginToken(menu);
        this.addCrashItem(menu);
        this.addAPIEnvironmentPicker(menu);
        this.addKitchensink(menu);
    }
}
