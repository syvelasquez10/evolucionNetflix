// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.SubMenu;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.util.PermissionUtils;
import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.view.MenuItem;
import com.netflix.mediaclient.util.net.CronetHttpURLConnectionFactory;
import android.view.MenuItem$OnMenuItemClickListener;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class DebugMenuItems
{
    private final NetflixActivity activity;
    private final String logTag;
    private final String menuLabel;
    
    public DebugMenuItems(final String logTag, final NetflixActivity activity) {
        this.menuLabel = "Ignore Player Interaction";
        this.logTag = logTag;
        this.activity = activity;
    }
    
    private void addAPIEnvironmentPicker(final Menu menu) {
        menu.add((CharSequence)"Pick API environment").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$7(this, new String[] { "INT", "TEST" }, PreferenceUtils.getStringPref((Context)this.activity, "api_environment_preference", ""), new String[] { "PROD", "STAGING" }));
    }
    
    private void addBarkerBars(final Menu menu) {
        menu.add((CharSequence)"Show Barker Bars").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$28(this));
    }
    
    private void addClearTooltip(final Menu menu) {
        menu.add((CharSequence)"Reset Tooltips state").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$3(this));
    }
    
    private void addCrashItem(final Menu menu) {
        menu.add((CharSequence)"Crash").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$19(this));
    }
    
    private void addCreateAutoLoginToken(final Menu menu) {
        menu.add((CharSequence)"Create AutoLogin Token").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$9(this));
    }
    
    private void addCronetLoggingItem(final Menu menu) {
        final MenuItem setOnMenuItemClickListener = menu.add((CharSequence)"Cronet Logging Enabled").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$6(this));
        setOnMenuItemClickListener.setCheckable(true);
        setOnMenuItemClickListener.setChecked(CronetHttpURLConnectionFactory.getInstance((Context)this.activity).isNetLogInProgress());
    }
    
    private void addDumpCacheToDiskItem(final Menu menu) {
        menu.add((CharSequence)"Dump Cache to Disk").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$10(this));
    }
    
    private void addDumpHomeLolomoToHtmlItem(final Menu menu) {
        if (!(this.activity instanceof HomeActivity)) {
            return;
        }
        menu.add((CharSequence)"Dump Lolomo to Html").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$11(this, (HomeActivity)this.activity));
    }
    
    private void addDumpPerfData(final Menu menu) {
        menu.add((CharSequence)"Dump Perf Data").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$24(this));
    }
    
    private void addFlushCLEvents(final Menu menu) {
        menu.add((CharSequence)"Push logging events").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$26(this));
    }
    
    private void addFlushDataCacheItem(final Menu menu) {
        menu.add((CharSequence)"Flush Data Cache").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$23(this));
    }
    
    private void addHprofDumpItem(final Menu menu) {
        menu.add((CharSequence)"Dump hprof profile").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$17(this));
    }
    
    private void addLaunchNotificationsActivity(final Menu menu) {
        menu.add((CharSequence)"Launch Notifications Activity").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$8(this));
    }
    
    private void addLaunchOnramp(final Menu menu) {
        menu.add((CharSequence)"Launch OnRamp").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$30(this));
    }
    
    private void addLaunchUiShowcase(final Menu menu) {
        menu.add((CharSequence)"UI Showcase Activity").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$5(this));
    }
    
    private void addMakeRefreshCwRemoteCall(final Menu menu) {
        menu.add((CharSequence)"Make refreshCw Remote Call").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$14(this));
    }
    
    private void addMakeRefreshIqRemoteCall(final Menu menu) {
        menu.add((CharSequence)"Make refreshIq Remote Call").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$15(this));
    }
    
    private void addMakeRefreshLolomoRemoteCall(final Menu menu) {
        menu.add((CharSequence)"Make refreshLolomo Remote Call").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$16(this));
    }
    
    private void addNetworkRequestOverlay(final Menu menu) {
        final MenuItem setOnMenuItemClickListener = menu.add((CharSequence)"Network Request Overlay").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$31(this));
        setOnMenuItemClickListener.setCheckable(true);
        setOnMenuItemClickListener.setChecked(DebugOverlay.isEnabled((Context)this.activity));
    }
    
    private void addNetworkSpeedController(final Menu menu) {
        menu.add(2131297071).setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$2(this));
    }
    
    private void addReportBug(final Menu menu) {
        menu.add((CharSequence)"Report a bug").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$4(this));
    }
    
    private void addResetABConfigData(final Menu menu) {
        menu.add((CharSequence)"Request config data").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$27(this));
    }
    
    private void addRunPrefetchLolomoSchedulerJob(final Menu menu) {
        menu.add((CharSequence)"Run Prefetch Lolomo JobScheduler").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$25(this));
    }
    
    private void addSendCwRefreshBroadcast(final Menu menu) {
        menu.add((CharSequence)"Send Cw Refresh Broadcast").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$13(this));
    }
    
    private void addSendHomeRefreshBroadcast(final Menu menu) {
        menu.add((CharSequence)"Send Home Refresh").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$12(this));
    }
    
    private void addShowControlledNetworkSpeedChart(final Menu menu) {
        menu.add(2131297070).setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$1(this));
    }
    
    private void addShowPlaybackAdvisory(final Menu menu) {
        menu.add((CharSequence)"Show Playback Advisory").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$29(this));
    }
    
    private void addToggleFetchErrorsItem(final Menu menu) {
        menu.add((CharSequence)"Toggle Fetch Errors").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$21(this));
    }
    
    private void addToggleIgnorePlayerInteraction(final Menu menu) {
        menu.add((CharSequence)"Toggle Ignore Player Interaction").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$22(this));
    }
    
    private void addTraceviewItem(final Menu menu) {
        menu.add((CharSequence)"5s Traceview").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$18(this));
    }
    
    private void beginTraceview() {
        Log.i(this.logTag, "**********************************************************************");
        Log.i(this.logTag, "Starting method trace...");
        Log.i(this.logTag, "**********************************************************************");
        Debug.startMethodTracing("nflx");
        new Handler().postDelayed((Runnable)new DebugMenuItems$20(this), 5000L);
    }
    
    private boolean requestExternalFileWritePermission() {
        if (PermissionUtils.shouldRequestPermission((Context)this.activity, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions(this.activity, new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" }, 145);
            return false;
        }
        return true;
    }
    
    private void startCronetLogging() {
        if (!this.requestExternalFileWritePermission()) {
            Log.e(this.logTag, "Error: Don't have External write permissions yet... ");
            return;
        }
        CronetHttpURLConnectionFactory.getInstance((Context)this.activity).startNetLog();
    }
    
    private void stopCronetLogging() {
        CronetHttpURLConnectionFactory.getInstance((Context)this.activity).stopNetLog();
    }
    
    public void addItems(final Menu menu) {
        final SubMenu addSubMenu = menu.addSubMenu((CharSequence)"Cache");
        this.addFlushDataCacheItem((Menu)addSubMenu);
        this.addSendHomeRefreshBroadcast((Menu)addSubMenu);
        this.addSendCwRefreshBroadcast((Menu)addSubMenu);
        this.addMakeRefreshCwRemoteCall((Menu)addSubMenu);
        this.addMakeRefreshIqRemoteCall((Menu)addSubMenu);
        this.addMakeRefreshLolomoRemoteCall((Menu)addSubMenu);
        final SubMenu addSubMenu2 = menu.addSubMenu((CharSequence)"Data Dumps");
        this.addDumpPerfData((Menu)addSubMenu2);
        this.addDumpCacheToDiskItem((Menu)addSubMenu2);
        this.addDumpHomeLolomoToHtmlItem((Menu)addSubMenu2);
        this.addHprofDumpItem((Menu)addSubMenu2);
        final SubMenu addSubMenu3 = menu.addSubMenu((CharSequence)"Misc");
        this.addResetABConfigData((Menu)addSubMenu3);
        this.addFlushCLEvents((Menu)addSubMenu3);
        this.addRunPrefetchLolomoSchedulerJob((Menu)addSubMenu3);
        this.addTraceviewItem((Menu)addSubMenu3);
        this.addCreateAutoLoginToken((Menu)addSubMenu3);
        this.addCrashItem((Menu)addSubMenu3);
        final SubMenu addSubMenu4 = menu.addSubMenu((CharSequence)"Network");
        this.addNetworkSpeedController((Menu)addSubMenu4);
        this.addShowControlledNetworkSpeedChart((Menu)addSubMenu4);
        this.addToggleFetchErrorsItem((Menu)addSubMenu4);
        this.addNetworkRequestOverlay((Menu)addSubMenu4);
        this.addCronetLoggingItem((Menu)addSubMenu4);
        final SubMenu addSubMenu5 = menu.addSubMenu((CharSequence)"UI");
        this.addLaunchOnramp((Menu)addSubMenu5);
        this.addLaunchNotificationsActivity((Menu)addSubMenu5);
        this.addLaunchUiShowcase((Menu)addSubMenu5);
        this.addShowPlaybackAdvisory((Menu)addSubMenu5);
        this.addBarkerBars((Menu)addSubMenu5);
        this.addToggleIgnorePlayerInteraction((Menu)addSubMenu5);
        this.addClearTooltip((Menu)addSubMenu5);
        this.addAPIEnvironmentPicker(menu);
        this.addReportBug(menu);
    }
}
