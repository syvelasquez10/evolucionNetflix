// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.apm.model.UIModalViewChangedEvent;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.service.logging.apm.model.UIModelessViewSessionStartedEvent;
import com.netflix.mediaclient.javabridge.ui.Log$AppIdSetListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.android.preapp.model.PreAppWidgetInstallEvent;
import com.netflix.mediaclient.service.logging.android.preapp.model.PreAppWidgetInstallEvent$WidgetInstallAction;
import android.content.Context;
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
import java.util.Collections;
import java.util.HashMap;
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
import com.netflix.mediaclient.service.logging.apm.model.UserSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.apm.UserSession;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import com.netflix.mediaclient.javabridge.ui.Log$ResetSessionIdCallback;

class ApmLoggingImpl$2 implements Log$ResetSessionIdCallback
{
    final /* synthetic */ ApmLoggingImpl this$0;
    final /* synthetic */ UserSessionStartedEvent val$ev;
    final /* synthetic */ UserSession val$us;
    
    ApmLoggingImpl$2(final ApmLoggingImpl this$0, final UserSession val$us, final UserSessionStartedEvent val$ev) {
        this.this$0 = this$0;
        this.val$us = val$us;
        this.val$ev = val$ev;
    }
    
    @Override
    public void sessionCreated(final String s) {
        this.this$0.mEventHandler.removeSession(this.this$0.mUserSession);
        this.this$0.mUserSession = this.val$us;
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "Session created: " + s);
        }
        this.val$us.setId(Long.valueOf(s));
        this.this$0.mNrdpLogSessionId = s;
        this.this$0.mEventHandler.addSession(this.this$0.mUserSession);
        final DeviceUniqueId id = this.val$us.getId();
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "User session id: " + id);
        }
        this.val$ev.setSessionId(id);
        this.this$0.sendUserSessionEvent(this.val$ev, this.this$0.mDataContext, this.this$0.mCurrentUiView);
        final UserSessionEndedEvent pendingEndEvent = this.val$us.getPendingEndEvent();
        if (pendingEndEvent != null) {
            pendingEndEvent.setSessionId(id);
            this.this$0.mEventHandler.post(pendingEndEvent);
            Log.d("nf_log_apm", "Pending user session end event posted.");
        }
    }
}
