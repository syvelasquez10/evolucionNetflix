// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.Log;
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
    
    private void addDumpCacheToDiskItem(final Menu menu) {
        menu.add((CharSequence)"Dump Falkor Cache to Disk").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$1(this));
    }
    
    private void addFlushDataCacheItem(final Menu menu) {
        menu.add((CharSequence)"Flush Data Cache").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$9(this));
    }
    
    private void addHprofDumpItem(final Menu menu) {
        menu.add((CharSequence)"Dump hprof profile").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$5(this));
    }
    
    private void addSendCwRefreshBroadcast(final Menu menu) {
        menu.add((CharSequence)"Send Cw Refresh").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$4(this));
    }
    
    private void addSendHomeRefreshBroadcast(final Menu menu) {
        menu.add((CharSequence)"Send Home Refresh").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$3(this));
    }
    
    private void addToggleFetchErrorsItem(final Menu menu) {
        menu.add((CharSequence)"Toggle Fetch Errors").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$8(this));
    }
    
    private void addTraceviewItem(final Menu menu) {
        menu.add((CharSequence)"5s Traceview").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$6(this));
    }
    
    private void addValidateFalkorAgentItem(final Menu menu) {
        menu.add((CharSequence)"Launch Falkor Validator Act").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new DebugMenuItems$2(this));
    }
    
    private void beginTraceview() {
        Log.i(this.logTag, "Starting method trace...");
        Debug.startMethodTracing("nflx");
        new Handler().postDelayed((Runnable)new DebugMenuItems$7(this), 5000L);
    }
    
    public void addItems(final Menu menu) {
        this.addHprofDumpItem(menu);
        this.addTraceviewItem(menu);
        this.addToggleFetchErrorsItem(menu);
        this.addFlushDataCacheItem(menu);
        this.addSendHomeRefreshBroadcast(menu);
        this.addSendCwRefreshBroadcast(menu);
        this.addValidateFalkorAgentItem(menu);
    }
}
