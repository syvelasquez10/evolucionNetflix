// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.uiview.model.ImpressionEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.uiview.ImpressionSession;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.service.logging.uiview.model.ImpressionSessionEndedEvent;
import com.netflix.mediaclient.service.logging.uiview.CommandSession;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.DataContext;

class UIViewLoggingImpl$2 implements Runnable
{
    final /* synthetic */ UIViewLoggingImpl this$0;
    final /* synthetic */ DataContext val$dataContext;
    final /* synthetic */ Error val$error;
    final /* synthetic */ boolean val$success;
    
    UIViewLoggingImpl$2(final UIViewLoggingImpl this$0, final boolean val$success, final Error val$error, final DataContext val$dataContext) {
        this.this$0 = this$0;
        this.val$success = val$success;
        this.val$error = val$error;
        this.val$dataContext = val$dataContext;
    }
    
    @Override
    public void run() {
        Log.d("nf_log", "uiView impression session ended");
        if (this.this$0.mImpressionSession == null) {
            Log.w("nf_log", "uiView impression session does NOT exist!");
            return;
        }
        final ImpressionSessionEndedEvent endedEvent = this.this$0.mImpressionSession.createEndedEvent(this.val$success, this.val$error);
        this.this$0.populateEvent(endedEvent, this.val$dataContext, this.this$0.mImpressionSession.getView());
        this.this$0.mEventHandler.removeSession(this.this$0.mImpressionSession);
        Log.d("nf_log", "uiView impression session end event posting...");
        this.this$0.mEventHandler.post(endedEvent);
        this.this$0.mCommandSession = null;
        Log.d("nf_log", "uiView impression session end event posted.");
    }
}
