// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.service.logging.suspend.model.UnfocusedSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.Log;
import java.util.Map;
import android.net.Uri;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.protocol.netflixcom.NetflixComUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.apm.model.DeepLink;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.suspend.UnfocusedSession;
import com.netflix.mediaclient.service.logging.suspend.SuspendSession;
import com.netflix.mediaclient.service.logging.suspend.BackgroundSession;
import com.netflix.mediaclient.service.logging.suspend.ResumingSession;
import com.netflix.mediaclient.service.logging.suspend.ForegroundSession;
import com.netflix.mediaclient.service.logging.suspend.BackgroundingSession;
import com.netflix.mediaclient.servicemgr.SuspendLogging;

public final class SuspendLoggingImpl implements SuspendLogging
{
    private static final String TAG = "nf_log";
    private BackgroundingSession mBackgroundingSession;
    private EventHandler mEventHandler;
    private ForegroundSession mForegroundSession;
    private ResumingSession mResumingSession;
    private BackgroundSession mSessionBackground;
    private SuspendSession mSuspendSession;
    private UnfocusedSession mUnfocusedSession;
    
    SuspendLoggingImpl(final EventHandler mEventHandler) {
        this.mEventHandler = mEventHandler;
    }
    
    private DeepLink getDeepLink(final Intent intent) {
        if (intent != null) {
            final Uri data = intent.getData();
            final String stringExtra = intent.getStringExtra("source");
            if (!StringUtils.isEmpty(stringExtra)) {
                final Map<String, String> parameters = NetflixComUtils.getParameters(data);
                parameters.put("source", stringExtra);
                return NflxProtocolUtils.createDeepLink(parameters);
            }
        }
        return null;
    }
    
    @Override
    public void endAllActiveSessions() {
        synchronized (this) {
            this.endBackgroundingSession();
            this.endBackgroundSession();
            this.endResumingSession();
            this.endForegroundSession();
            this.endSuspendSession();
            this.endUnfocusedSession();
        }
    }
    
    @Override
    public void endBackgroundSession() {
        if (this.mSessionBackground != null) {
            Log.d("nf_log", "Background session end started");
            this.mEventHandler.removeSession(this.mSessionBackground);
            this.mEventHandler.post(this.mSessionBackground.createEndedEvent());
            this.mSessionBackground = null;
            Log.d("nf_log", "Background session end done.");
        }
    }
    
    @Override
    public void endBackgroundingSession() {
        if (this.mBackgroundingSession != null) {
            Log.d("nf_log", "Backgrounding session end started");
            this.mEventHandler.removeSession(this.mBackgroundingSession);
            this.mEventHandler.post(this.mBackgroundingSession.createEndedEvent());
            this.mBackgroundingSession = null;
            Log.d("nf_log", "Backgrounding session end done.");
        }
    }
    
    @Override
    public void endForegroundSession() {
        if (this.mForegroundSession != null) {
            Log.d("nf_log", "Foreground session end started");
            this.mEventHandler.removeSession(this.mForegroundSession);
            this.mEventHandler.post(this.mForegroundSession.createEndedEvent());
            this.mForegroundSession = null;
            Log.d("nf_log", "Foreground session end done.");
        }
    }
    
    @Override
    public void endResumingSession() {
        if (this.mResumingSession != null) {
            Log.d("nf_log", "Resuming session end started");
            this.mEventHandler.removeSession(this.mResumingSession);
            this.mEventHandler.post(this.mResumingSession.createEndedEvent());
            this.mResumingSession = null;
            Log.d("nf_log", "Resuming session end done.");
        }
    }
    
    @Override
    public void endSuspendSession() {
        if (this.mSuspendSession != null) {
            Log.d("nf_log", "Suspend session end started");
            this.mEventHandler.removeSession(this.mSuspendSession);
            this.mEventHandler.post(this.mSuspendSession.createEndedEvent());
            this.mSuspendSession = null;
            Log.d("nf_log", "Suspend session end done.");
        }
    }
    
    @Override
    public void endUnfocusedSession() {
        if (this.mUnfocusedSession != null) {
            Log.d("nf_log", "Unfocused session end started");
            this.mEventHandler.removeSession(this.mUnfocusedSession);
            final UnfocusedSessionEndedEvent endedEvent = this.mUnfocusedSession.createEndedEvent();
            if (endedEvent != null && this.mUnfocusedSession.getUnfocusedSessionStartedEvent() != null) {
                this.mEventHandler.post(this.mUnfocusedSession.getUnfocusedSessionStartedEvent());
                this.mEventHandler.post(endedEvent);
            }
            this.mUnfocusedSession = null;
            Log.d("nf_log", "Unfocused session end done.");
        }
    }
    
    @Override
    public void startBackgroundSession() {
        Log.d("nf_log", "Background session start started");
        if (this.mSessionBackground != null) {
            Log.w("nf_log", "Background session existed before! It should not happen!");
            return;
        }
        this.mSessionBackground = new BackgroundSession();
        this.mEventHandler.addSession(this.mSessionBackground);
        this.mEventHandler.post(this.mSessionBackground.createStartEvent());
        Log.d("nf_log", "Background session start done.");
    }
    
    @Override
    public void startBackgroundingSession() {
        Log.d("nf_log", "Backgrounding session start started");
        if (this.mBackgroundingSession != null) {
            Log.w("nf_log", "Backgrounding session existed before! It should not happen!");
            return;
        }
        this.mBackgroundingSession = new BackgroundingSession();
        this.mEventHandler.addSession(this.mBackgroundingSession);
        Log.d("nf_log", "Background session start done.");
    }
    
    @Override
    public void startForegroundSession(final Intent intent) {
        Log.d("nf_log", "Foreground session start started");
        final DeepLink deepLink = this.getDeepLink(intent);
        if (this.mForegroundSession != null) {
            Log.d("nf_log", "Foreground session existed before");
            if (deepLink == null) {
                Log.w("nf_log", "Not deeplink, ignore.");
                return;
            }
            Log.d("nf_log", "Deeplink found, end existing foreground session...");
            this.endForegroundSession();
        }
        this.mForegroundSession = new ForegroundSession();
        this.mEventHandler.addSession(this.mForegroundSession);
        this.mEventHandler.post(this.mForegroundSession.createStartEvent(null, deepLink));
        Log.d("nf_log", "Foreground session start done.");
    }
    
    @Override
    public void startResumingSession() {
        Log.d("nf_log", "Resuming session start started");
        if (this.mResumingSession != null) {
            Log.w("nf_log", "Resuming session existed before! It should not happen!");
            return;
        }
        this.mResumingSession = new ResumingSession();
        this.mEventHandler.addSession(this.mResumingSession);
        Log.d("nf_log", "Resuming session start done.");
    }
    
    @Override
    public void startSuspendSession() {
        Log.d("nf_log", "Suspend session start started");
        if (this.mSuspendSession != null) {
            Log.w("nf_log", "Suspend session existed before! It should not happen!");
            return;
        }
        this.mSuspendSession = new SuspendSession();
        this.mEventHandler.addSession(this.mSuspendSession);
        this.mEventHandler.post(this.mSuspendSession.createStartEvent());
        Log.d("nf_log", "Suspend session start done.");
    }
    
    @Override
    public void startUnfocusedSession() {
        Log.d("nf_log", "Unfocused session start started");
        if (this.mUnfocusedSession != null) {
            Log.w("nf_log", "Unfocused session existed before! It should not happen!");
            return;
        }
        this.mUnfocusedSession = new UnfocusedSession();
        this.mEventHandler.addSession(this.mUnfocusedSession);
        this.mUnfocusedSession.createStartEvent();
        Log.d("nf_log", "Unfocused session start done.");
    }
}
