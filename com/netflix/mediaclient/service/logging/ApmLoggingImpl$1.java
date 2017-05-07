// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.apm.model.UIModalViewChangedEvent;
import com.netflix.mediaclient.javabridge.ui.Log$ResetSessionIdCallback;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.service.logging.apm.model.UIModelessViewSessionStartedEvent;
import android.content.Context;
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
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.Log$AppIdSetListener;

class ApmLoggingImpl$1 implements Log$AppIdSetListener
{
    final /* synthetic */ ApmLoggingImpl this$0;
    final /* synthetic */ boolean val$lastShutdownGraceful;
    final /* synthetic */ long val$now;
    
    ApmLoggingImpl$1(final ApmLoggingImpl this$0, final boolean val$lastShutdownGraceful, final long val$now) {
        this.this$0 = this$0;
        this.val$lastShutdownGraceful = val$lastShutdownGraceful;
        this.val$now = val$now;
    }
    
    @Override
    public void onSet(final String s) {
        if (Log.isLoggable("nf_log_apm", 3)) {
            Log.d("nf_log_apm", "startApplicationSession::onSet:: Application ID received " + s);
        }
        if (StringUtils.isNotEmpty(s)) {
            Log.d("nf_log_apm", "Application ID is ready, start application session...");
            this.this$0.doStartApplicationSession(this.val$lastShutdownGraceful, s, this.val$now);
            return;
        }
        Log.e("nf_log_apm", "Application ID is null in callback, this should NOT happen...");
        this.this$0.mEventHandler.setAppIdSetListener(this);
    }
}
