// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.IntentFilter;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.support.v4.content.LocalBroadcastManager;
import android.app.Activity;

public class UiLifecycleHelper
{
    private static final String ACTIVITY_NULL_MESSAGE = "activity cannot be null";
    private final Activity activity;
    private final LocalBroadcastManager broadcastManager;
    private final Session$StatusCallback callback;
    private final BroadcastReceiver receiver;
    
    public UiLifecycleHelper(final Activity activity, final Session$StatusCallback callback) {
        if (activity == null) {
            throw new IllegalArgumentException("activity cannot be null");
        }
        this.activity = activity;
        this.callback = callback;
        this.receiver = new UiLifecycleHelper$ActiveSessionBroadcastReceiver(this, null);
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)activity);
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        final Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            activeSession.onActivityResult(this.activity, n, n2, intent);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        Session session = Session.getActiveSession();
        if (session == null) {
            if (bundle != null) {
                session = Session.restoreSession((Context)this.activity, null, this.callback, bundle);
            }
            Session activeSession;
            if ((activeSession = session) == null) {
                activeSession = new Session((Context)this.activity);
            }
            Session.setActiveSession(activeSession);
        }
    }
    
    public void onDestroy() {
    }
    
    public void onPause() {
        this.broadcastManager.unregisterReceiver(this.receiver);
        if (this.callback != null) {
            final Session activeSession = Session.getActiveSession();
            if (activeSession != null) {
                activeSession.removeCallback(this.callback);
            }
        }
    }
    
    public void onResume() {
        final Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            if (this.callback != null) {
                activeSession.addCallback(this.callback);
            }
            if (SessionState.CREATED_TOKEN_LOADED.equals(activeSession.getState())) {
                activeSession.openForRead(null);
            }
        }
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.ACTIVE_SESSION_SET");
        intentFilter.addAction("com.facebook.sdk.ACTIVE_SESSION_UNSET");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        Session.saveSession(Session.getActiveSession(), bundle);
    }
}
