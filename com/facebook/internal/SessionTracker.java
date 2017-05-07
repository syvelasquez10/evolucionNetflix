// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import com.facebook.SessionState;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.facebook.Session;
import android.support.v4.content.LocalBroadcastManager;

public class SessionTracker
{
    private final LocalBroadcastManager broadcastManager;
    private final Session.StatusCallback callback;
    private boolean isTracking;
    private final BroadcastReceiver receiver;
    private Session session;
    
    public SessionTracker(final Context context, final Session.StatusCallback statusCallback) {
        this(context, statusCallback, null);
    }
    
    SessionTracker(final Context context, final Session.StatusCallback statusCallback, final Session session) {
        this(context, statusCallback, session, true);
    }
    
    public SessionTracker(final Context context, final Session.StatusCallback statusCallback, final Session session, final boolean b) {
        this.isTracking = false;
        this.callback = new CallbackWrapper(statusCallback);
        this.session = session;
        this.receiver = new ActiveSessionBroadcastReceiver();
        this.broadcastManager = LocalBroadcastManager.getInstance(context);
        if (b) {
            this.startTracking();
        }
    }
    
    private void addBroadcastReceiver() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.ACTIVE_SESSION_SET");
        intentFilter.addAction("com.facebook.sdk.ACTIVE_SESSION_UNSET");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    public Session getOpenSession() {
        final Session session = this.getSession();
        if (session != null && session.isOpened()) {
            return session;
        }
        return null;
    }
    
    public Session getSession() {
        if (this.session == null) {
            return Session.getActiveSession();
        }
        return this.session;
    }
    
    public boolean isTracking() {
        return this.isTracking;
    }
    
    public boolean isTrackingActiveSession() {
        return this.session == null;
    }
    
    public void setSession(final Session session) {
        if (session == null) {
            if (this.session != null) {
                this.session.removeCallback(this.callback);
                this.session = null;
                this.addBroadcastReceiver();
                if (this.getSession() != null) {
                    this.getSession().addCallback(this.callback);
                }
            }
            return;
        }
        if (this.session == null) {
            final Session activeSession = Session.getActiveSession();
            if (activeSession != null) {
                activeSession.removeCallback(this.callback);
            }
            this.broadcastManager.unregisterReceiver(this.receiver);
        }
        else {
            this.session.removeCallback(this.callback);
        }
        (this.session = session).addCallback(this.callback);
    }
    
    public void startTracking() {
        if (this.isTracking) {
            return;
        }
        if (this.session == null) {
            this.addBroadcastReceiver();
        }
        if (this.getSession() != null) {
            this.getSession().addCallback(this.callback);
        }
        this.isTracking = true;
    }
    
    public void stopTracking() {
        if (!this.isTracking) {
            return;
        }
        final Session session = this.getSession();
        if (session != null) {
            session.removeCallback(this.callback);
        }
        this.broadcastManager.unregisterReceiver(this.receiver);
        this.isTracking = false;
    }
    
    private class ActiveSessionBroadcastReceiver extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if ("com.facebook.sdk.ACTIVE_SESSION_SET".equals(intent.getAction())) {
                final Session activeSession = Session.getActiveSession();
                if (activeSession != null) {
                    activeSession.addCallback(SessionTracker.this.callback);
                }
            }
        }
    }
    
    private class CallbackWrapper implements StatusCallback
    {
        private final StatusCallback wrapped;
        
        public CallbackWrapper(final StatusCallback wrapped) {
            this.wrapped = wrapped;
        }
        
        @Override
        public void call(final Session session, final SessionState sessionState, final Exception ex) {
            if (this.wrapped != null && SessionTracker.this.isTracking()) {
                this.wrapped.call(session, sessionState, ex);
            }
            if (session == SessionTracker.this.session && sessionState.isClosed()) {
                SessionTracker.this.setSession(null);
            }
        }
    }
}
