// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.uiview.CommandSession;
import com.netflix.mediaclient.servicemgr.UIViewLogging;

public class UIViewLoggingImpl implements UIViewLogging
{
    public static final String[] ACTIONS;
    public static final String COMMAND_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED";
    public static final String COMMAND_START = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START";
    public static final String EXTRA_CMD = "cmd";
    public static final String EXTRA_DATA_CONTEXT = "dataContext";
    public static final String EXTRA_VIEW = "view";
    private static final String TAG = "nf_log";
    private CommandSession mCommandSession;
    private DataContext mDataContext;
    private EventHandler mEventHandler;
    
    static {
        ACTIONS = new String[] { "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START", "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED" };
    }
    
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
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging.ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
        event.setModalView(modalView);
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
}
