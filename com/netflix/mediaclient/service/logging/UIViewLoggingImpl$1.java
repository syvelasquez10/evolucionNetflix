// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.uiview.model.ImpressionEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.uiview.ImpressionSession;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent;
import com.netflix.mediaclient.service.logging.uiview.CommandSession;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.DataContext;

class UIViewLoggingImpl$1 implements Runnable
{
    final /* synthetic */ UIViewLoggingImpl this$0;
    final /* synthetic */ DataContext val$dataContext;
    
    UIViewLoggingImpl$1(final UIViewLoggingImpl this$0, final DataContext val$dataContext) {
        this.this$0 = this$0;
        this.val$dataContext = val$dataContext;
    }
    
    @Override
    public void run() {
        Log.d("nf_log", "uiView command session ended");
        if (this.this$0.mCommandSession == null) {
            Log.w("nf_log", "uiView command session does NOT exist!");
            return;
        }
        final CommandEndedEvent endedEvent = this.this$0.mCommandSession.createEndedEvent();
        if (endedEvent == null) {
            Log.d("nf_log", "uiView command session still waits on session id, do not post at this time.");
            return;
        }
        this.this$0.populateEvent(endedEvent, this.val$dataContext, this.this$0.mCommandSession.getView());
        this.this$0.mEventHandler.removeSession(this.this$0.mCommandSession);
        Log.d("nf_log", "uiView command session end event posting...");
        this.this$0.mEventHandler.post(endedEvent);
        this.this$0.mCommandSession = null;
        Log.d("nf_log", "uiView command session end event posted.");
    }
}
