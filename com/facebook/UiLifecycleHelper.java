// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.util.Log;
import android.content.IntentFilter;
import android.os.Bundle;
import com.facebook.internal.LikeActionController;
import com.facebook.internal.NativeProtocol;
import com.facebook.widget.FacebookDialog$PendingCall;
import com.facebook.widget.FacebookDialog;
import android.content.Intent;
import com.facebook.widget.FacebookDialog$Callback;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.facebook.internal.PendingCallStore;
import java.util.UUID;
import android.support.v4.content.LocalBroadcastManager;
import android.app.Activity;

public class UiLifecycleHelper
{
    private static final String ACTIVITY_NULL_MESSAGE = "activity cannot be null";
    private static final String DIALOG_CALL_ID_SAVE_KEY = "com.facebook.UiLifecycleHelper.pendingFacebookDialogCallKey";
    private final Activity activity;
    private AppEventsLogger appEventsLogger;
    private final LocalBroadcastManager broadcastManager;
    private final Session$StatusCallback callback;
    private UUID pendingFacebookDialogCallId;
    private PendingCallStore pendingFacebookDialogCallStore;
    private final BroadcastReceiver receiver;
    
    public UiLifecycleHelper(final Activity activity, final Session$StatusCallback callback) {
        if (activity == null) {
            throw new IllegalArgumentException("activity cannot be null");
        }
        this.activity = activity;
        this.callback = callback;
        this.receiver = new UiLifecycleHelper$ActiveSessionBroadcastReceiver(this, null);
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)activity);
        this.pendingFacebookDialogCallStore = PendingCallStore.getInstance();
        Settings.sdkInitialize((Context)activity);
    }
    
    private void cancelPendingAppCall(final FacebookDialog$Callback facebookDialog$Callback) {
        if (this.pendingFacebookDialogCallId != null) {
            final FacebookDialog$PendingCall pendingCallById = this.pendingFacebookDialogCallStore.getPendingCallById(this.pendingFacebookDialogCallId);
            if (pendingCallById != null) {
                if (facebookDialog$Callback != null) {
                    final Intent requestIntent = pendingCallById.getRequestIntent();
                    final Intent intent = new Intent();
                    intent.putExtra("com.facebook.platform.protocol.CALL_ID", requestIntent.getStringExtra("com.facebook.platform.protocol.CALL_ID"));
                    intent.putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", requestIntent.getStringExtra("com.facebook.platform.protocol.PROTOCOL_ACTION"));
                    intent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", requestIntent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0));
                    intent.putExtra("com.facebook.platform.status.ERROR_TYPE", "UnknownError");
                    FacebookDialog.handleActivityResult((Context)this.activity, pendingCallById, pendingCallById.getRequestCode(), intent, facebookDialog$Callback);
                }
                this.stopTrackingPendingAppCall();
            }
        }
    }
    
    private boolean handleFacebookDialogActivityResult(final int n, final int n2, final Intent intent, final FacebookDialog$Callback facebookDialog$Callback) {
        if (this.pendingFacebookDialogCallId != null) {
            final FacebookDialog$PendingCall pendingCallById = this.pendingFacebookDialogCallStore.getPendingCallById(this.pendingFacebookDialogCallId);
            if (pendingCallById != null && pendingCallById.getRequestCode() == n) {
                if (intent == null) {
                    this.cancelPendingAppCall(facebookDialog$Callback);
                    return true;
                }
                final UUID callIdFromIntent = NativeProtocol.getCallIdFromIntent(intent);
                if (callIdFromIntent != null && this.pendingFacebookDialogCallId.equals(callIdFromIntent)) {
                    FacebookDialog.handleActivityResult((Context)this.activity, pendingCallById, n, intent, facebookDialog$Callback);
                }
                else {
                    this.cancelPendingAppCall(facebookDialog$Callback);
                }
                this.stopTrackingPendingAppCall();
                return true;
            }
        }
        return false;
    }
    
    private void stopTrackingPendingAppCall() {
        this.pendingFacebookDialogCallStore.stopTrackingPendingCall(this.pendingFacebookDialogCallId);
        this.pendingFacebookDialogCallId = null;
    }
    
    public AppEventsLogger getAppEventsLogger() {
        final Session activeSession = Session.getActiveSession();
        if (activeSession == null) {
            return null;
        }
        if (this.appEventsLogger == null || !this.appEventsLogger.isValidForSession(activeSession)) {
            if (this.appEventsLogger != null) {
                AppEventsLogger.onContextStop();
            }
            this.appEventsLogger = AppEventsLogger.newLogger((Context)this.activity, activeSession);
        }
        return this.appEventsLogger;
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        this.onActivityResult(n, n2, intent, null);
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent, final FacebookDialog$Callback facebookDialog$Callback) {
        final Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            activeSession.onActivityResult(this.activity, n, n2, intent);
        }
        if (LikeActionController.handleOnActivityResult((Context)this.activity, n, n2, intent)) {
            return;
        }
        this.handleFacebookDialogActivityResult(n, n2, intent, facebookDialog$Callback);
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
        if (bundle != null) {
            final String string = bundle.getString("com.facebook.UiLifecycleHelper.pendingFacebookDialogCallKey");
            if (string != null) {
                this.pendingFacebookDialogCallId = UUID.fromString(string);
            }
            this.pendingFacebookDialogCallStore.restoreFromSavedInstanceState(bundle);
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
        if (this.pendingFacebookDialogCallId != null) {
            bundle.putString("com.facebook.UiLifecycleHelper.pendingFacebookDialogCallKey", this.pendingFacebookDialogCallId.toString());
        }
        this.pendingFacebookDialogCallStore.saveInstanceState(bundle);
    }
    
    public void onStop() {
        AppEventsLogger.onContextStop();
    }
    
    public void trackPendingDialogCall(final FacebookDialog$PendingCall facebookDialog$PendingCall) {
        if (this.pendingFacebookDialogCallId != null) {
            Log.i("Facebook", "Tracking new app call while one is still pending; canceling pending call.");
            this.cancelPendingAppCall(null);
        }
        if (facebookDialog$PendingCall != null) {
            this.pendingFacebookDialogCallId = facebookDialog$PendingCall.getCallId();
            this.pendingFacebookDialogCallStore.trackPendingCall(facebookDialog$PendingCall);
        }
    }
}
