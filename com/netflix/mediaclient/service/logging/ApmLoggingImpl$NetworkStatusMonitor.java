// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.apm.model.UIModalViewChangedEvent;
import com.netflix.mediaclient.javabridge.ui.Log$ResetSessionIdCallback;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.apm.model.UIBrowseStartupSessionCustomData;
import com.netflix.mediaclient.service.logging.apm.model.DeepLink;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.service.logging.apm.model.UIModelessViewSessionStartedEvent;
import com.netflix.mediaclient.javabridge.ui.Log$AppIdSetListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.android.preapp.model.PreAppWidgetInstallEvent;
import com.netflix.mediaclient.service.logging.android.preapp.model.PreAppWidgetInstallEvent$WidgetInstallAction;
import com.netflix.mediaclient.service.logging.android.settings.model.LocalSettingsChangeEvent;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIStartupSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIModelessViewSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIBrowseStartupSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.SharedContextSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIDataRequestSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIAssetRequestSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.AppSessionEndedEvent;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$EndReason;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.service.logging.client.model.FalkorPathResult;
import java.util.List;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import java.util.Collection;
import java.util.HashSet;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.apm.WiredNetworkConnectionSession;
import com.netflix.mediaclient.service.logging.apm.WifiNetworkConnectionSession;
import com.netflix.mediaclient.service.logging.apm.MobileNetworkConnectionSession;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.apm.model.AppSessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import java.util.Collections;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.apm.UserSession;
import com.netflix.mediaclient.service.logging.apm.UIStartupSession;
import com.netflix.mediaclient.service.logging.apm.UIBrowseStartupSession;
import com.netflix.mediaclient.service.logging.apm.SharedContextSession;
import com.netflix.mediaclient.service.logging.apm.NetworkConnectionSession;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.logging.apm.UIModelessViewSession;
import com.netflix.mediaclient.service.logging.apm.UIDataRequestSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.apm.UIAssetRequestSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.apm.ApplicationSession;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import com.netflix.mediaclient.Log;
import android.content.Context;

class ApmLoggingImpl$NetworkStatusMonitor
{
    private boolean mMobileConnected;
    private boolean mWiFiConnected;
    private boolean mWiredConnected;
    final /* synthetic */ ApmLoggingImpl this$0;
    
    private ApmLoggingImpl$NetworkStatusMonitor(final ApmLoggingImpl this$0) {
        this.this$0 = this$0;
    }
    
    private void lostConnectivity() {
        this.reset();
        this.this$0.endWiredNetworkConnectivitySession();
        this.this$0.endWifiNetworkConnectivitySession();
        this.this$0.endMobileNetworkConnectivitySession();
    }
    
    private void reset() {
        this.mWiFiConnected = false;
        this.mMobileConnected = false;
        this.mWiredConnected = false;
    }
    
    public void handleConnectivityChange(final Context context) {
        Log.d("nf_log_apm", "handleConnectivityChange started");
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null) {
            Log.w("nf_log_apm", "Connectivity manager is gone! Connectivity is lost!");
            this.lostConnectivity();
            return;
        }
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            Log.w("nf_log_apm", "Connectivity manager exist, but no active connection! Connectivity is lost!");
            this.lostConnectivity();
            return;
        }
        final boolean connectedOrConnecting = activeNetworkInfo.isConnectedOrConnecting();
        final int type = activeNetworkInfo.getType();
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "handleConnectivityChange:: connected " + connectedOrConnecting + ", type: " + type);
        }
        switch (type) {
            default: {
                Log.d("nf_log_apm", "Not supported network type " + type);
            }
            case 9: {
                this.mWiFiConnected = false;
                this.mMobileConnected = false;
                this.mWiredConnected = connectedOrConnecting;
                if (connectedOrConnecting) {
                    this.this$0.startWiredNetworkConnectivitySession();
                }
                else {
                    this.this$0.endWiredNetworkConnectivitySession();
                }
                this.this$0.endWifiNetworkConnectivitySession();
                this.this$0.endMobileNetworkConnectivitySession();
            }
            case 1: {
                this.mWiFiConnected = connectedOrConnecting;
                this.mMobileConnected = false;
                this.mWiredConnected = false;
                if (connectedOrConnecting) {
                    this.this$0.startWifiNetworkConnectivitySession();
                }
                else {
                    this.this$0.endWifiNetworkConnectivitySession();
                }
                this.this$0.endWiredNetworkConnectivitySession();
                this.this$0.endMobileNetworkConnectivitySession();
            }
            case 0:
            case 6: {
                this.mWiFiConnected = false;
                this.mMobileConnected = connectedOrConnecting;
                this.mWiredConnected = false;
                if (connectedOrConnecting) {
                    this.this$0.startMobileNetworkConnectivitySession();
                }
                else {
                    this.this$0.endMobileNetworkConnectivitySession();
                }
                this.this$0.endWiredNetworkConnectivitySession();
                this.this$0.endWifiNetworkConnectivitySession();
            }
        }
    }
    
    public boolean isMobileConnected() {
        return this.mMobileConnected;
    }
    
    public boolean isWiFiConnected() {
        return this.mWiFiConnected;
    }
    
    public boolean isWiredConnected() {
        return this.mWiredConnected;
    }
}
