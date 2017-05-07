// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.suspend.BackgroundSession;
import com.netflix.mediaclient.service.logging.suspend.ResumingSession;
import com.netflix.mediaclient.service.logging.suspend.BackgroundingSession;
import com.netflix.mediaclient.servicemgr.SuspendLogging;

public final class SuspendLoggingImpl implements SuspendLogging
{
    private static final String TAG = "nf_log";
    private BackgroundingSession mBackgroundingSession;
    private EventHandler mEventHandler;
    private ResumingSession mResumingSession;
    private BackgroundSession mSessionBackground;
    
    SuspendLoggingImpl(final EventHandler mEventHandler) {
        this.mEventHandler = mEventHandler;
    }
    
    @Override
    public void endAllActiveSessions() {
        synchronized (this) {
            this.endBackgroundingSession();
            this.endBackgroundSession();
            this.endResumingSession();
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
    public void endResumingSession() {
        if (this.mResumingSession != null) {
            Log.d("nf_log", "Resuming session end started");
            this.mEventHandler.removeSession(this.mResumingSession);
            this.mEventHandler.post(this.mResumingSession.createEndedEvent());
            this.mResumingSession = null;
            Log.d("nf_log", "Resuming session end done.");
        }
    }
    
    public boolean handleIntent(final Intent intent) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_BACKGROUND_START".equals(action)) {
            Log.d("nf_log", "BACKGROUND_START");
            this.startBackgroundSession();
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_BACKGROUND_ENDED".equals(action)) {
            Log.d("nf_log", "BACKGROUND_ENDED");
            this.endBackgroundSession();
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_BACKGROUNDING_START".equals(action)) {
            Log.d("nf_log", "BACKGROUNDING_START");
            this.startBackgroundingSession();
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_BACKGROUNDING_ENDED".equals(action)) {
            Log.d("nf_log", "BACKGROUNDING_ENDED");
            this.endBackgroundingSession();
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_RESUMING_START".equals(action)) {
            Log.d("nf_log", "RESUMING_START");
            this.startResumingSession();
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_RESUMING_ENDED".equals(action)) {
            Log.d("nf_log", "RESUMING_ENDED");
            this.endResumingSession();
            return true;
        }
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "We do not support action " + action);
        }
        return false;
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
}
