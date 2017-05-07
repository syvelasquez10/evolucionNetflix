// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.widget.Toast;
import android.os.Handler;
import android.os.Debug;
import android.content.Context;
import com.netflix.mediaclient.android.activity.FalkorValidationActivity;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.MenuItem;
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
    
    private void addFlushDataCacheItem(final Menu menu) {
        menu.add((CharSequence)"Flush Data Cache").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                final ServiceManager serviceManager = DebugMenuItems.this.activity.getServiceManager();
                if (serviceManager != null) {
                    serviceManager.getBrowse().flushCaches();
                }
                return true;
            }
        });
    }
    
    private void addHprofDumpItem(final Menu menu) {
        menu.add((CharSequence)"Dump hprof profile").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                AndroidUtils.dumpHprofToDisk();
                return true;
            }
        });
    }
    
    private void addSendCwRefreshBroadcast(final Menu menu) {
        menu.add((CharSequence)"Send Cw Refresh").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                Log.d(DebugMenuItems.this.logTag, "Sending CW refresh: com.netflix.mediaclient.intent.action.BA_CW_REFRESH");
                DebugMenuItems.this.activity.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.BA_CW_REFRESH"));
                return true;
            }
        });
    }
    
    private void addSendHomeRefreshBroadcast(final Menu menu) {
        menu.add((CharSequence)"Send Home Refresh").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                Log.d(DebugMenuItems.this.logTag, "Sending home refresh: com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO");
                DebugMenuItems.this.activity.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO"));
                return true;
            }
        });
    }
    
    private void addToggleFetchErrorsItem(final Menu menu) {
        menu.add((CharSequence)"Toggle Fetch Errors").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                NetflixService.toggleFetchErrorsEnabled();
                DebugMenuItems.this.activity.showFetchErrorsToast();
                return true;
            }
        });
    }
    
    private void addTraceviewItem(final Menu menu) {
        menu.add((CharSequence)"5s Traceview").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                DebugMenuItems.this.beginTraceview();
                return true;
            }
        });
    }
    
    private void addValidateFalkorAgentItem(final Menu menu) {
        menu.add((CharSequence)"Launch Falkor Validator Act").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                DebugMenuItems.this.activity.startActivity(FalkorValidationActivity.createStartIntent((Context)DebugMenuItems.this.activity));
                return true;
            }
        });
    }
    
    private void beginTraceview() {
        Log.i(this.logTag, "Starting method trace...");
        Debug.startMethodTracing("nflx");
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                Debug.stopMethodTracing();
                Log.i(DebugMenuItems.this.logTag, "Trace complete.  Get with: adb pull /sdcard/nflx.trace");
                Toast.makeText((Context)DebugMenuItems.this.activity, (CharSequence)"Trace: /sdcard/nflx.trace", 1).show();
            }
        }, 5000L);
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
