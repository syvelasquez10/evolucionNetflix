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
import com.netflix.mediaclient.service.logging.apm.model.SharedContextSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.FalkorPathResult;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import java.util.Collections;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.apm.UserSession;
import com.netflix.mediaclient.service.logging.apm.UIBrowseStartupSession;
import com.netflix.mediaclient.service.logging.apm.SharedContextSession;
import com.netflix.mediaclient.service.logging.apm.NetworkConnectionSession;
import com.netflix.mediaclient.service.logging.apm.UIModelessViewSession;
import com.netflix.mediaclient.service.logging.apm.UIDataRequestSession;
import com.netflix.mediaclient.service.logging.apm.UIAssetRequestSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.apm.ApplicationSession;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.logging.apm.model.UIStartupSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.UIStartupSession;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.DataContext;

class ApmLoggingImpl$7 implements Runnable
{
    final /* synthetic */ ApmLoggingImpl this$0;
    final /* synthetic */ DataContext val$dataContext;
    final /* synthetic */ UIError val$error;
    final /* synthetic */ PlayerType val$playerType;
    final /* synthetic */ boolean val$success;
    final /* synthetic */ IClientLogging$ModalView val$ui;
    
    ApmLoggingImpl$7(final ApmLoggingImpl this$0, final boolean val$success, final UIError val$error, final PlayerType val$playerType, final DataContext val$dataContext, final IClientLogging$ModalView val$ui) {
        this.this$0 = this$0;
        this.val$success = val$success;
        this.val$error = val$error;
        this.val$playerType = val$playerType;
        this.val$dataContext = val$dataContext;
        this.val$ui = val$ui;
    }
    
    @Override
    public void run() {
        Log.d("nf_log_apm", "UI startup session ended");
        if (this.this$0.mUIStartupSession == null) {
            Log.w("nf_log_apm", "UI startup session does NOT exist!");
            return;
        }
        this.this$0.mEventHandler.removeSession(this.this$0.mUIStartupSession);
        Log.d("nf_log_apm", "UI startup session end event posting...");
        final UIStartupSessionEndedEvent endedEvent = this.this$0.mUIStartupSession.createEndedEvent(this.val$success, this.val$error, this.val$playerType);
        this.this$0.populateEvent(endedEvent, this.val$dataContext, this.val$ui);
        this.this$0.mEventHandler.post(endedEvent);
        this.this$0.mUIStartupSession = null;
        Log.d("nf_log_apm", "UI startup session end event posted.");
    }
}
