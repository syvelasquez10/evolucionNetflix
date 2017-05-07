// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.uiview.model.ImpressionSessionEndedEvent;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.uiview.model.ImpressionEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.client.model.Error;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.uiview.ImpressionSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.uiview.CommandSession;
import com.netflix.mediaclient.servicemgr.UIViewLogging;

public final class UIViewLoggingImpl implements UIViewLogging
{
    private static final String TAG = "nf_log";
    private CommandSession mCommandSession;
    private DataContext mDataContext;
    private EventHandler mEventHandler;
    private ImpressionSession mImpressionSession;
    private String mUrl;
    
    public UIViewLoggingImpl(final EventHandler mEventHandler) {
        this.mEventHandler = mEventHandler;
    }
    
    private void handleUIViewCommandEnded(final Intent intent) {
        this.endCommandSession();
    }
    
    private void handleUIViewCommandStart(final Intent intent) {
        final DataContext dataContext = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UIViewLogging$UIViewCommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UIViewLogging$UIViewCommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        while (true) {
            Label_0125: {
                if (!StringUtils.isNotEmpty(stringExtra2)) {
                    break Label_0125;
                }
                final IClientLogging$ModalView value2 = IClientLogging$ModalView.valueOf(stringExtra2);
                final String stringExtra3 = intent.getStringExtra("dataContext");
                DataContext instance = dataContext;
                while (true) {
                    if (!StringUtils.isNotEmpty(stringExtra3)) {
                        break Label_0075;
                    }
                    try {
                        instance = DataContext.createInstance(new JSONObject(stringExtra3));
                        this.startCommandSession(value, value2, instance, intent.getStringExtra("url"));
                        return;
                    }
                    catch (JSONException ex) {
                        Log.w("nf_log", "failed to create dataContext: " + stringExtra3);
                        instance = dataContext;
                        continue;
                    }
                    break;
                }
            }
            final IClientLogging$ModalView value2 = null;
            continue;
        }
    }
    
    private void handleUIViewImpression(final Intent intent) {
        final int intExtra = intent.getIntExtra("trackId", 0);
        final String stringExtra = intent.getStringExtra("cmd");
        UIViewLogging$UIViewCommandName value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UIViewLogging$UIViewCommandName.valueOf(stringExtra);
        }
        this.createImpressionEvent(value, intExtra);
    }
    
    private void handleUIViewImpressionEnd(Intent instance) {
        final boolean booleanExtra = instance.getBooleanExtra("success", false);
        final String stringExtra = instance.getStringExtra("error");
        instance = null;
        while (true) {
            try {
                instance = (Intent)Error.createInstance(stringExtra);
                this.endImpressionSession(booleanExtra, (Error)instance);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleUIViewImpressionStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("view");
        IClientLogging$ModalView value = null;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = IClientLogging$ModalView.valueOf(stringExtra);
        }
        this.startImpressionSession(value, intent.getStringExtra("guid"));
    }
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging$ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
        event.setModalView(modalView);
    }
    
    @Override
    public void createImpressionEvent(final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final int n) {
        final ImpressionEvent impressionEvent = new ImpressionEvent(uiViewLogging$UIViewCommandName, n);
        this.populateEvent(impressionEvent, this.mDataContext, null);
        this.mEventHandler.post(impressionEvent);
    }
    
    @Override
    public void endAllActiveSessions() {
        this.endCommandSession();
        this.endImpressionSession(true, null);
    }
    
    @Override
    public void endCommandSession() {
        if (this.mCommandSession == null) {
            return;
        }
        Log.d("nf_log", "uiView command session ended");
        final CommandEndedEvent endedEvent = this.mCommandSession.createEndedEvent();
        if (endedEvent == null) {
            Log.d("nf_log", "uiView command session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mCommandSession.getView());
        this.mEventHandler.removeSession(this.mCommandSession);
        Log.d("nf_log", "uiView command session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mCommandSession = null;
        Log.d("nf_log", "uiView command session end event posted.");
    }
    
    @Override
    public void endImpressionSession(final boolean b, final Error error) {
        synchronized (this) {
            if (this.mImpressionSession != null) {
                Log.d("nf_log", "uiView impression session ended");
                final ImpressionSessionEndedEvent endedEvent = this.mImpressionSession.createEndedEvent(b, error);
                this.populateEvent(endedEvent, this.mDataContext, this.mImpressionSession.getView());
                this.mEventHandler.removeSession(this.mImpressionSession);
                Log.d("nf_log", "uiView impression session end event posting...");
                this.mEventHandler.post(endedEvent);
                this.mCommandSession = null;
                Log.d("nf_log", "uiView impression session end event posted.");
            }
        }
    }
    
    public boolean handleIntent(final Intent intent, final boolean b) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START".equals(action)) {
            Log.d("nf_log", "COMMAND_START");
            this.handleUIViewCommandStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED".equals(action)) {
            Log.d("nf_log", "COMMAND_ENDED");
            this.handleUIViewCommandEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION".equals(action)) {
            Log.d("nf_log", "IMPRESSION");
            this.handleUIViewImpression(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_STARTED".equals(action)) {
            Log.d("nf_log", "IMPRESSION_SESSION_STARTED");
            this.handleUIViewImpressionStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_ENDED".equals(action)) {
            Log.d("nf_log", "IMPRESSION_SESSION_ENDED");
            this.handleUIViewImpressionEnd(intent);
            return true;
        }
        if (Log.isLoggable()) {
            Log.d("nf_log", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void startCommandSession(final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final IClientLogging$ModalView clientLogging$ModalView, final DataContext mDataContext, final String mUrl) {
        synchronized (this) {
            if (this.mCommandSession != null) {
                Log.e("nf_log", "uiView command session already started!");
            }
            else {
                Log.d("nf_log", "uiView command session starting...");
                final CommandSession mCommandSession = new CommandSession(uiViewLogging$UIViewCommandName, clientLogging$ModalView, mUrl);
                this.mEventHandler.addSession(mCommandSession);
                this.mCommandSession = mCommandSession;
                this.mDataContext = mDataContext;
                this.mUrl = mUrl;
                Log.d("nf_log", "uiView command session start done.");
            }
        }
    }
    
    @Override
    public void startImpressionSession(final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        synchronized (this) {
            if (this.mImpressionSession != null) {
                Log.e("nf_log", "uiView impression session already started!");
            }
            else {
                Log.d("nf_log", "uiView impression session starting...");
                final ImpressionSession mImpressionSession = new ImpressionSession(clientLogging$ModalView, s);
                this.mEventHandler.addSession(mImpressionSession);
                this.mImpressionSession = mImpressionSession;
                Log.d("nf_log", "uiView impression session start done.");
            }
        }
    }
}
