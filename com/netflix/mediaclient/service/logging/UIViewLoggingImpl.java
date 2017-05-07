// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.uiview.model.ImpressionSessionEndedEvent;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.uiview.model.ImpressionEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.uiview.ImpressionSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.uiview.CommandSession;
import com.netflix.mediaclient.servicemgr.UIViewLogging;

public class UIViewLoggingImpl implements UIViewLogging
{
    private static final String TAG = "nf_log";
    private CommandSession mCommandSession;
    private DataContext mDataContext;
    private EventHandler mEventHandler;
    private ImpressionSession mImpressionSession;
    
    public UIViewLoggingImpl(final EventHandler mEventHandler) {
        this.mEventHandler = mEventHandler;
    }
    
    private void handleUIViewCommandEnded(final Intent intent) {
        this.endCommandSession();
    }
    
    private void handleUIViewCommandStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<UIViewCommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UIViewCommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        final DataContext dataContext = null;
        final String stringExtra3 = intent.getStringExtra("dataContext");
        DataContext instance = dataContext;
        while (true) {
            if (!StringUtils.isNotEmpty(stringExtra3)) {
                break Label_0080;
            }
            try {
                instance = DataContext.createInstance(new JSONObject(stringExtra3));
                this.startCommandSession((UIViewCommandName)value, (IClientLogging.ModalView)value2, instance);
            }
            catch (JSONException ex) {
                Log.w("nf_log", "failed to create dataContext: " + stringExtra3);
                instance = dataContext;
                continue;
            }
            break;
        }
    }
    
    private void handleUIViewImpression(final Intent intent) {
        final int intExtra = intent.getIntExtra("trackId", 0);
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<UIViewCommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UIViewCommandName.valueOf(stringExtra);
        }
        this.createImpressionEvent((UIViewCommandName)value, intExtra);
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
        Enum<IClientLogging.ModalView> value = null;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = IClientLogging.ModalView.valueOf(stringExtra);
        }
        this.startImpressionSession((IClientLogging.ModalView)value, intent.getStringExtra("guid"));
    }
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging.ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
        event.setModalView(modalView);
    }
    
    @Override
    public void createImpressionEvent(final UIViewCommandName uiViewCommandName, final int n) {
        final ImpressionEvent impressionEvent = new ImpressionEvent(uiViewCommandName, n);
        this.populateEvent(impressionEvent, this.mDataContext, null);
        this.mEventHandler.post(impressionEvent);
    }
    
    @Override
    public void endCommandSession() {
        Log.d("nf_log", "uiView command session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UIViewLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "uiView command session ended");
                if (UIViewLoggingImpl.this.mCommandSession == null) {
                    Log.w("nf_log", "uiView command session does NOT exist!");
                    return;
                }
                final CommandEndedEvent endedEvent = UIViewLoggingImpl.this.mCommandSession.createEndedEvent();
                if (endedEvent == null) {
                    Log.d("nf_log", "uiView command session still waits on session id, do not post at this time.");
                    return;
                }
                UIViewLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UIViewLoggingImpl.this.mCommandSession.getView());
                UIViewLoggingImpl.this.mEventHandler.removeSession(UIViewLoggingImpl.this.mCommandSession);
                Log.d("nf_log", "uiView command session end event posting...");
                UIViewLoggingImpl.this.mEventHandler.post(endedEvent);
                UIViewLoggingImpl.this.mCommandSession = null;
                Log.d("nf_log", "uiView command session end event posted.");
            }
        });
        Log.d("nf_log", "uiView command session end done.");
    }
    
    @Override
    public void endImpressionSession(final boolean b, final Error error) {
        Log.d("nf_log", "uiView impression session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UIViewLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "uiView impression session ended");
                if (UIViewLoggingImpl.this.mImpressionSession == null) {
                    Log.w("nf_log", "uiView impression session does NOT exist!");
                    return;
                }
                final ImpressionSessionEndedEvent endedEvent = UIViewLoggingImpl.this.mImpressionSession.createEndedEvent(b, error);
                UIViewLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UIViewLoggingImpl.this.mImpressionSession.getView());
                UIViewLoggingImpl.this.mEventHandler.removeSession(UIViewLoggingImpl.this.mImpressionSession);
                Log.d("nf_log", "uiView impression session end event posting...");
                UIViewLoggingImpl.this.mEventHandler.post(endedEvent);
                UIViewLoggingImpl.this.mCommandSession = null;
                Log.d("nf_log", "uiView impression session end event posted.");
            }
        });
        Log.d("nf_log", "uiView impression session end done.");
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
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void startCommandSession(final UIViewCommandName uiViewCommandName, final IClientLogging.ModalView modalView, final DataContext mDataContext) {
        if (this.mCommandSession != null) {
            Log.e("nf_log", "uiView command session already started!");
            return;
        }
        Log.d("nf_log", "uiView command session starting...");
        final CommandSession mCommandSession = new CommandSession(uiViewCommandName, modalView);
        this.mEventHandler.addSession(mCommandSession);
        this.mCommandSession = mCommandSession;
        this.mDataContext = mDataContext;
        Log.d("nf_log", "uiView command session start done.");
    }
    
    @Override
    public void startImpressionSession(final IClientLogging.ModalView modalView, final String s) {
        if (this.mImpressionSession != null) {
            Log.e("nf_log", "uiView impression session already started!");
            return;
        }
        Log.d("nf_log", "uiView impression session starting...");
        final ImpressionSession mImpressionSession = new ImpressionSession(modalView, s);
        this.mEventHandler.addSession(mImpressionSession);
        this.mImpressionSession = mImpressionSession;
        Log.d("nf_log", "uiView impression session start done.");
    }
}
