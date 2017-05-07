// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.util.StringUtils;
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
import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import java.util.Collections;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.apm.UserSession;
import com.netflix.mediaclient.service.logging.apm.UIBrowseStartupSession;
import com.netflix.mediaclient.service.logging.apm.SharedContextSession;
import com.netflix.mediaclient.service.logging.apm.NetworkConnectionSession;
import com.netflix.mediaclient.service.logging.apm.UIModelessViewSession;
import com.netflix.mediaclient.service.logging.apm.UIDataRequestSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.apm.UIAssetRequestSession;
import com.netflix.mediaclient.service.logging.apm.ApplicationSession;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.apm.UIStartupSession;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import java.util.Map;

class ApmLoggingImpl$6 implements Runnable
{
    final /* synthetic */ ApmLoggingImpl this$0;
    final /* synthetic */ Map val$activeABTests;
    final /* synthetic */ IClientLogging$ModalView val$destination;
    final /* synthetic */ Display val$display;
    final /* synthetic */ String val$searchTerm;
    final /* synthetic */ Long val$started;
    final /* synthetic */ int val$trackId;
    final /* synthetic */ ApplicationPerformanceMetricsLogging$UiStartupTrigger val$trigger;
    
    ApmLoggingImpl$6(final ApmLoggingImpl this$0, final ApplicationPerformanceMetricsLogging$UiStartupTrigger val$trigger, final IClientLogging$ModalView val$destination, final int val$trackId, final String val$searchTerm, final Map val$activeABTests, final Display val$display, final Long val$started) {
        this.this$0 = this$0;
        this.val$trigger = val$trigger;
        this.val$destination = val$destination;
        this.val$trackId = val$trackId;
        this.val$searchTerm = val$searchTerm;
        this.val$activeABTests = val$activeABTests;
        this.val$display = val$display;
        this.val$started = val$started;
    }
    
    @Override
    public void run() {
        Log.d("nf_log_apm", "UI startup session created");
        final UIStartupSession uiStartupSession = new UIStartupSession(this.val$trigger, this.val$destination, this.val$trackId, this.val$searchTerm, this.val$activeABTests, this.val$display);
        if (this.val$started != null) {
            uiStartupSession.setStarted(this.val$started);
        }
        this.this$0.mEventHandler.removeSession(this.this$0.mUIStartupSession);
        this.this$0.mUIStartupSession = uiStartupSession;
        this.this$0.mEventHandler.addSession(uiStartupSession);
    }
}
