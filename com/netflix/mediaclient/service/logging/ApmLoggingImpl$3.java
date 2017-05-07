// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$EndReason;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.apm.model.SharedContextSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.FalkorPathResult;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import java.util.Collections;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.apm.UIStartupSession;
import com.netflix.mediaclient.service.logging.apm.UIBrowseStartupSession;
import com.netflix.mediaclient.service.logging.apm.SharedContextSession;
import com.netflix.mediaclient.service.logging.apm.NetworkConnectionSession;
import com.netflix.mediaclient.service.logging.apm.UIModelessViewSession;
import com.netflix.mediaclient.service.logging.apm.UIDataRequestSession;
import com.netflix.mediaclient.service.logging.apm.UIAssetRequestSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.apm.ApplicationSession;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.javabridge.ui.Log$ResetSessionIdCallback;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.apm.UserSession;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.client.model.DataContext;

class ApmLoggingImpl$3 implements Runnable
{
    final /* synthetic */ ApmLoggingImpl this$0;
    final /* synthetic */ DataContext val$dataContext;
    final /* synthetic */ long val$idleTime;
    final /* synthetic */ ApplicationPerformanceMetricsLogging$Trigger val$trigger;
    final /* synthetic */ IClientLogging$ModalView val$ui;
    
    ApmLoggingImpl$3(final ApmLoggingImpl this$0, final ApplicationPerformanceMetricsLogging$Trigger val$trigger, final long val$idleTime, final DataContext val$dataContext, final IClientLogging$ModalView val$ui) {
        this.this$0 = this$0;
        this.val$trigger = val$trigger;
        this.val$idleTime = val$idleTime;
        this.val$dataContext = val$dataContext;
        this.val$ui = val$ui;
    }
    
    @Override
    public void run() {
        if (this.this$0.mUserSession != null) {
            Log.d("nf_log_apm", "User session already exist");
            return;
        }
        Log.d("nf_log_apm", "User session created");
        final UserSession userSession = new UserSession();
        Log.d("nf_log_apm", "User session start event posting...");
        final UserSessionStartedEvent startEvent = userSession.createStartEvent(this.val$trigger, this.val$idleTime);
        final String userSessionId = this.this$0.mEventHandler.getUserSessionId();
        if (Log.isLoggable("nf_log_apm", 3)) {
            Log.d("nf_log_apm", "startUserSession: Current nrdp.log.sessionid " + userSessionId);
            Log.d("nf_log_apm", "startUserSession: Last used nrdp.log.sessionid " + this.this$0.mNrdpLogSessionId);
        }
        final ApmLoggingImpl$3$1 apmLoggingImpl$3$1 = new ApmLoggingImpl$3$1(this, userSession, startEvent);
        if (this.this$0.mNrdpLogSessionId != null) {
            if (StringUtils.isEmpty(userSessionId)) {
                Log.e("nf_log_apm", "User session is not yet created. This should NOT happen!");
            }
            else if (this.this$0.mNrdpLogSessionId.equals(userSessionId)) {
                Log.e("nf_log_apm", "User session is not yet created. This should NOT happen!");
            }
            else {
                Log.w("nf_log_apm", "Known used and current user session ids are NOT same, this should not happen");
            }
            this.this$0.mEventHandler.createUserSession(apmLoggingImpl$3$1);
            return;
        }
        Log.d("nf_log_apm", "We never used nrdp.log.sessionid, use it now if user session exist in nrdp");
        if (StringUtils.isEmpty(userSessionId)) {
            Log.e("nf_log_apm", "User session is not yet created. This should NOT happen!");
            this.this$0.mEventHandler.createUserSession(apmLoggingImpl$3$1);
            return;
        }
        Log.d("nf_log_apm", "User session is created. Set it to event and post event");
        this.this$0.mEventHandler.removeSession(this.this$0.mUserSession);
        this.this$0.mUserSession = userSession;
        userSession.setId(Long.valueOf(userSessionId));
        this.this$0.mNrdpLogSessionId = userSessionId;
        this.this$0.mEventHandler.addSession(this.this$0.mUserSession);
        startEvent.setSessionId(userSession.getId());
        this.this$0.sendUserSessionEvent(startEvent, this.val$dataContext, this.val$ui);
    }
}
