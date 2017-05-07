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
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
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
import com.netflix.mediaclient.service.logging.apm.UIAssetRequestSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.apm.ApplicationSession;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.apm.model.UIModalViewChangedEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DataContext;

class ApmLoggingImpl$11 implements Runnable
{
    final /* synthetic */ ApmLoggingImpl this$0;
    final /* synthetic */ DataContext val$dataContext;
    final /* synthetic */ boolean val$portrait;
    final /* synthetic */ IClientLogging$ModalView val$ui;
    final /* synthetic */ IClientLogging$ModalView val$view;
    
    ApmLoggingImpl$11(final ApmLoggingImpl this$0, final IClientLogging$ModalView val$view, final boolean val$portrait, final DataContext val$dataContext, final IClientLogging$ModalView val$ui) {
        this.this$0 = this$0;
        this.val$view = val$view;
        this.val$portrait = val$portrait;
        this.val$dataContext = val$dataContext;
        this.val$ui = val$ui;
    }
    
    @Override
    public void run() {
        if (Log.isLoggable("nf_log_apm", 3)) {
            Log.d("nf_log_apm", "UI view changed " + this.val$view);
        }
        final UIModalViewChangedEvent uiModalViewChangedEvent = new UIModalViewChangedEvent(this.val$portrait, this.val$view);
        this.this$0.populateEvent(uiModalViewChangedEvent, this.val$dataContext, this.val$ui);
        this.this$0.mEventHandler.post(uiModalViewChangedEvent);
        Log.d("nf_log_apm", "UI view changed event posted.");
    }
}
