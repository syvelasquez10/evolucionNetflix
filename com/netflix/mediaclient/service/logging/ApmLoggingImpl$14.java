// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$EndReason;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.apm.model.SharedContextSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.FalkorPathResult;
import java.util.List;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import java.util.Collections;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.apm.UserSession;
import com.netflix.mediaclient.service.logging.apm.UIStartupSession;
import com.netflix.mediaclient.service.logging.apm.UIBrowseStartupSession;
import com.netflix.mediaclient.service.logging.apm.SharedContextSession;
import com.netflix.mediaclient.service.logging.apm.NetworkConnectionSession;
import com.netflix.mediaclient.service.logging.apm.UIModelessViewSession;
import com.netflix.mediaclient.service.logging.apm.UIDataRequestSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.apm.ApplicationSession;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.logging.apm.model.UIAssetRequestSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.apm.UIAssetRequestSession;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;

class ApmLoggingImpl$14 implements Runnable
{
    final /* synthetic */ ApmLoggingImpl this$0;
    final /* synthetic */ IClientLogging$CompletionReason val$completionReason;
    final /* synthetic */ DataContext val$dataContext;
    final /* synthetic */ Error val$error;
    final /* synthetic */ HttpResponse val$httpResponse;
    final /* synthetic */ IClientLogging$ModalView val$ui;
    final /* synthetic */ String val$url;
    
    ApmLoggingImpl$14(final ApmLoggingImpl this$0, final String val$url, final IClientLogging$CompletionReason val$completionReason, final HttpResponse val$httpResponse, final Error val$error, final DataContext val$dataContext, final IClientLogging$ModalView val$ui) {
        this.this$0 = this$0;
        this.val$url = val$url;
        this.val$completionReason = val$completionReason;
        this.val$httpResponse = val$httpResponse;
        this.val$error = val$error;
        this.val$dataContext = val$dataContext;
        this.val$ui = val$ui;
    }
    
    @Override
    public void run() {
        final UIAssetRequestSession uiAssetRequestSession = this.this$0.mAssetRequests.remove(this.val$url);
        if (uiAssetRequestSession == null) {
            if (Log.isLoggable("nf_log_apm", 3)) {
                Log.d("nf_log_apm", "UI Asset Request session does not exist (probably due to sampling) - url: " + this.val$url);
            }
            return;
        }
        this.this$0.mEventHandler.removeSession(uiAssetRequestSession);
        Log.d("nf_log_apm", "Asset request session end event posting...");
        final UIAssetRequestSessionEndedEvent endedEvent = uiAssetRequestSession.createEndedEvent(this.val$completionReason, this.val$httpResponse, this.val$error);
        this.this$0.populateEvent(endedEvent, this.val$dataContext, this.val$ui);
        this.this$0.mEventHandler.post(endedEvent);
        Log.d("nf_log_apm", "Asset request session end event posted.");
    }
}
