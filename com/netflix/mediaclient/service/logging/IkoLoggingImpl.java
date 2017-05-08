// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.iko.model.IkoVideoPlaybackEndedEvent;
import com.netflix.mediaclient.service.logging.iko.model.IkoVideoLoadEndedEvent;
import com.netflix.mediaclient.service.logging.iko.model.IkoMomentEndedEvent;
import com.netflix.mediaclient.service.logging.iko.model.IkoModeEndedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.client.model.Event;
import java.io.Serializable;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.iko.IkoVideoPlaybackSession;
import com.netflix.mediaclient.service.logging.iko.IkoVideoLoadSession;
import com.netflix.mediaclient.service.logging.iko.IkoMomentSession;
import com.netflix.mediaclient.service.logging.iko.IkoModeSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IkoLogging;

public class IkoLoggingImpl implements IkoLogging
{
    private static final String TAG = "nf_log_iko";
    private DataContext mDataContext;
    private EventHandler mEventHandler;
    private IkoModeSession mIkoModeSession;
    private IkoMomentSession mIkoMomentSession;
    private IkoVideoLoadSession mIkoVideoLoadSession;
    private IkoVideoPlaybackSession mIkoVideoPlaybackSession;
    
    IkoLoggingImpl(final EventHandler mEventHandler) {
        this.mEventHandler = mEventHandler;
    }
    
    private void handleIkoModeEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endIkoModeSession(value, instance);
            }
            catch (JSONException ex) {
                Log.e("nf_log_iko", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleIkoModeStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startIkoModeSession(value, value2);
    }
    
    private void handleIkoMomentEnded(final Intent intent) {
    Label_0031_Outer:
        while (true) {
            Serializable s = intent.getStringExtra("reason");
            final String stringExtra = intent.getStringExtra("error");
            while (true) {
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra);
                        if (StringUtils.isNotEmpty((String)s)) {
                            s = IClientLogging$CompletionReason.valueOf((String)s);
                            this.endIkoMomentSession((IClientLogging$CompletionReason)s, instance, intent.getStringExtra("momentId"), intent.getStringExtra("momentType"), intent.getIntExtra("expectedVideoOffset", 0), intent.getStringExtra("ikoMomentState"));
                            return;
                        }
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log_iko", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue Label_0031_Outer;
                    }
                    break;
                }
                s = null;
                continue;
            }
        }
    }
    
    private void handleIkoMomentStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startIkoMomentSession(value, value2);
    }
    
    private void handleIkoVideoLoadEnd(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endIkoVideoLoadSession(value, instance);
            }
            catch (JSONException ex) {
                Log.e("nf_log_iko", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleIkoVideoLoadStart(final Intent intent) {
        this.startIkoVideoLoadSession(intent.getStringExtra("url"));
    }
    
    private void handleIkoVideoPlaybackEnd(final Intent intent) {
        final String stringExtra = intent.getStringExtra("reason");
        final int intExtra = intent.getIntExtra("errorCode", -1);
        final int intExtra2 = intent.getIntExtra("errorSubCode", -1);
        IClientLogging$CompletionReason value = null;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = IClientLogging$CompletionReason.valueOf(stringExtra);
        }
        this.endIkoVideoPlaybackSession(value, intExtra, intExtra2);
    }
    
    private void handleIkoVideoPlaybackStart(final Intent intent) {
        this.startIkoVideoPlaybackSession(intent.getStringExtra("url"));
    }
    
    private void populateEvent(final Event event, final DataContext dataContext) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
    }
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging$ModalView modalView) {
        if (event == null) {
            return;
        }
        this.populateEvent(event, dataContext);
        event.setModalView(modalView);
    }
    
    @Override
    public void endAllActiveSessions() {
        synchronized (this) {
            this.endIkoVideoLoadSession(IClientLogging$CompletionReason.canceled, null);
            this.endIkoVideoPlaybackSession(IClientLogging$CompletionReason.canceled, -1, -1);
            this.endIkoModeSession(IClientLogging$CompletionReason.canceled, null);
            this.endIkoMomentSession(IClientLogging$CompletionReason.canceled, null, null, null, 0, null);
        }
    }
    
    @Override
    public void endIkoModeSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (this.mIkoModeSession == null) {
            return;
        }
        Log.d("nf_log_iko", "IkoMode session ended");
        final IkoModeEndedEvent endedEvent = this.mIkoModeSession.createEndedEvent(clientLogging$CompletionReason, uiError);
        if (endedEvent == null) {
            Log.d("nf_log_iko", "IkoMode session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mIkoModeSession.getView());
        this.mEventHandler.removeSession(this.mIkoModeSession);
        Log.d("nf_log_iko", "IkoMode session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mIkoModeSession = null;
        Log.d("nf_log_iko", "IkoMode session end event posted.");
    }
    
    @Override
    public void endIkoMomentSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String s, final String s2, final int n, final String s3) {
        if (this.mIkoMomentSession == null) {
            return;
        }
        Log.d("nf_log_iko", "IkoMoment session ended");
        final IkoMomentEndedEvent endedEvent = this.mIkoMomentSession.createEndedEvent(clientLogging$CompletionReason, uiError, s, s2, n, s3);
        if (endedEvent == null) {
            Log.d("nf_log_iko", "IkoMoment session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mIkoMomentSession.getView());
        this.mEventHandler.removeSession(this.mIkoMomentSession);
        Log.d("nf_log_iko", "IkoMoment session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mIkoMomentSession = null;
        Log.d("nf_log_iko", "IkoMoment session end event posted.");
    }
    
    @Override
    public void endIkoVideoLoadSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (this.mIkoVideoLoadSession == null) {
            return;
        }
        Log.d("nf_log_iko", "IkoVideoLoad session ended");
        final IkoVideoLoadEndedEvent endedEvent = this.mIkoVideoLoadSession.createEndedEvent(clientLogging$CompletionReason, uiError);
        if (endedEvent == null) {
            Log.d("nf_log_iko", "IkoVideoLoad session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext);
        this.mEventHandler.removeSession(this.mIkoVideoLoadSession);
        Log.d("nf_log_iko", "IkoVideoLoad session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mIkoVideoLoadSession = null;
        Log.d("nf_log_iko", "IkoVideoLoad session end event posted.");
    }
    
    @Override
    public void endIkoVideoPlaybackSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final int n, final int n2) {
        if (this.mIkoVideoPlaybackSession == null) {
            return;
        }
        Log.d("nf_log_iko", "IkoVideoPlaybackSession session ended");
        final IkoVideoPlaybackEndedEvent endedEvent = this.mIkoVideoPlaybackSession.createEndedEvent(clientLogging$CompletionReason, n, n2);
        if (endedEvent == null) {
            Log.d("nf_log_iko", "IkoVideoPlaybackSession session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext);
        this.mEventHandler.removeSession(this.mIkoVideoPlaybackSession);
        Log.d("nf_log_iko", "IkoVideoPlaybackSession session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mIkoVideoPlaybackSession = null;
        Log.d("nf_log_iko", "IkoVideoPlaybackSession session end event posted.");
    }
    
    public boolean handleIntent(final Intent intent) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_LOAD_START".equals(action)) {
            Log.d("nf_log_iko", "VIDEO_LOAD_START");
            this.handleIkoVideoLoadStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_LOAD_END".equals(action)) {
            Log.d("nf_log_iko", "VIDEO_LOAD_END");
            this.handleIkoVideoLoadEnd(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_PLAYBACK_START".equals(action)) {
            Log.d("nf_log_iko", "VIDEO_PLAYBACK_START");
            this.handleIkoVideoPlaybackStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_PLAYBACK_END".equals(action)) {
            Log.d("nf_log_iko", "VIDEO_PLAYBACK_END");
            this.handleIkoVideoPlaybackEnd(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_IKO_MODE_START".equals(action)) {
            Log.d("nf_log_iko", "IKO_MODE_START");
            this.handleIkoModeStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_IKO_MODE_ENDED".equals(action)) {
            Log.d("nf_log_iko", "IKO_MODE_ENDED");
            this.handleIkoModeEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_IKO_MOMENT_START".equals(action)) {
            Log.d("nf_log_iko", "IKO_MOMENT_START");
            this.handleIkoMomentStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_IKO_MOMENT_ENDED".equals(action)) {
            Log.d("nf_log_iko", "IKO_MOMENT_ENDED");
            this.handleIkoMomentEnded(intent);
            return true;
        }
        if (Log.isLoggable()) {
            Log.d("nf_log_iko", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void setDataContext(final DataContext mDataContext) {
        this.mDataContext = mDataContext;
    }
    
    @Override
    public void startIkoModeSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mIkoModeSession != null) {
            Log.e("nf_log_iko", "IkoMode session already started!");
            return;
        }
        Log.d("nf_log_iko", "IkoMode session starting...");
        final IkoModeSession mIkoModeSession = new IkoModeSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mIkoModeSession);
        this.mIkoModeSession = mIkoModeSession;
        Log.d("nf_log_iko", "IkoMode session start done.");
    }
    
    @Override
    public void startIkoMomentSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mIkoMomentSession != null) {
            Log.e("nf_log_iko", "IkoMoment session already started!");
            return;
        }
        Log.d("nf_log_iko", "IkoMoment session starting...");
        final IkoMomentSession mIkoMomentSession = new IkoMomentSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mIkoMomentSession);
        this.mIkoMomentSession = mIkoMomentSession;
        Log.d("nf_log_iko", "IkoMoment session start done.");
    }
    
    @Override
    public void startIkoVideoLoadSession(final String s) {
        if (this.mIkoVideoLoadSession != null) {
            Log.e("nf_log_iko", "IkoVideoLoad session already started!");
            return;
        }
        Log.d("nf_log_iko", "IkoVideoLoad session starting...");
        final IkoVideoLoadSession mIkoVideoLoadSession = new IkoVideoLoadSession(s);
        this.mEventHandler.addSession(mIkoVideoLoadSession);
        this.mIkoVideoLoadSession = mIkoVideoLoadSession;
        Log.d("nf_log_iko", "IkoVideoLoad session start done.");
    }
    
    @Override
    public void startIkoVideoPlaybackSession(final String s) {
        if (this.mIkoVideoPlaybackSession != null) {
            Log.e("nf_log_iko", "IkoVideoPlayback session already started!");
            return;
        }
        Log.d("nf_log_iko", "IkoVideoPlayback session starting...");
        final IkoVideoPlaybackSession mIkoVideoPlaybackSession = new IkoVideoPlaybackSession(s);
        this.mEventHandler.addSession(mIkoVideoPlaybackSession);
        this.mIkoVideoPlaybackSession = mIkoVideoPlaybackSession;
        Log.d("nf_log_iko", "IkoVideoPlayback session start done.");
    }
}
