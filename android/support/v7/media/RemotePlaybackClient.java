// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Parcelable;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.util.Log;
import android.app.PendingIntent;
import android.content.Context;

public class RemotePlaybackClient
{
    private static final boolean DEBUG;
    private static final String TAG = "RemotePlaybackClient";
    private final Context mContext;
    private final PendingIntent mItemStatusPendingIntent;
    private final MediaRouter$RouteInfo mRoute;
    private boolean mRouteSupportsQueuing;
    private boolean mRouteSupportsRemotePlayback;
    private boolean mRouteSupportsSessionManagement;
    private String mSessionId;
    private final PendingIntent mSessionStatusPendingIntent;
    private RemotePlaybackClient$StatusCallback mStatusCallback;
    private final RemotePlaybackClient$StatusReceiver mStatusReceiver;
    
    static {
        DEBUG = Log.isLoggable("RemotePlaybackClient", 3);
    }
    
    public RemotePlaybackClient(final Context mContext, final MediaRouter$RouteInfo mRoute) {
        if (mContext == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (mRoute == null) {
            throw new IllegalArgumentException("route must not be null");
        }
        this.mContext = mContext;
        this.mRoute = mRoute;
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED");
        intentFilter.addAction("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED");
        mContext.registerReceiver((BroadcastReceiver)(this.mStatusReceiver = new RemotePlaybackClient$StatusReceiver(this, null)), intentFilter);
        final Intent intent = new Intent("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED");
        intent.setPackage(mContext.getPackageName());
        this.mItemStatusPendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
        final Intent intent2 = new Intent("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED");
        intent2.setPackage(mContext.getPackageName());
        this.mSessionStatusPendingIntent = PendingIntent.getBroadcast(mContext, 0, intent2, 0);
        this.detectFeatures();
    }
    
    private void adoptSession(final String sessionId) {
        if (sessionId != null) {
            this.setSessionId(sessionId);
        }
    }
    
    private static String bundleToString(final Bundle bundle) {
        if (bundle != null) {
            bundle.size();
            return bundle.toString();
        }
        return "null";
    }
    
    private void detectFeatures() {
        final boolean b = true;
        this.mRouteSupportsRemotePlayback = (this.routeSupportsAction("android.media.intent.action.PLAY") && this.routeSupportsAction("android.media.intent.action.SEEK") && this.routeSupportsAction("android.media.intent.action.GET_STATUS") && this.routeSupportsAction("android.media.intent.action.PAUSE") && this.routeSupportsAction("android.media.intent.action.RESUME") && this.routeSupportsAction("android.media.intent.action.STOP"));
        this.mRouteSupportsQueuing = (this.mRouteSupportsRemotePlayback && this.routeSupportsAction("android.media.intent.action.ENQUEUE") && this.routeSupportsAction("android.media.intent.action.REMOVE"));
        this.mRouteSupportsSessionManagement = (this.mRouteSupportsRemotePlayback && this.routeSupportsAction("android.media.intent.action.START_SESSION") && this.routeSupportsAction("android.media.intent.action.GET_SESSION_STATUS") && this.routeSupportsAction("android.media.intent.action.END_SESSION") && b);
    }
    
    private void handleError(final Intent intent, final RemotePlaybackClient$ActionCallback remotePlaybackClient$ActionCallback, final String s, final Bundle bundle) {
        int int1 = 0;
        if (bundle != null) {
            int1 = bundle.getInt("android.media.intent.extra.ERROR_CODE", 0);
        }
        if (RemotePlaybackClient.DEBUG) {
            Log.w("RemotePlaybackClient", "Received error from " + intent.getAction() + ": error=" + s + ", code=" + int1 + ", data=" + bundleToString(bundle));
        }
        remotePlaybackClient$ActionCallback.onError(s, int1, bundle);
    }
    
    private void handleInvalidResult(final Intent intent, final RemotePlaybackClient$ActionCallback remotePlaybackClient$ActionCallback, final Bundle bundle) {
        Log.w("RemotePlaybackClient", "Received invalid result data from " + intent.getAction() + ": data=" + bundleToString(bundle));
        remotePlaybackClient$ActionCallback.onError(null, 0, bundle);
    }
    
    private static String inferMissingResult(final String s, final String s2) {
        if (s2 == null) {
            return s;
        }
        if (s == null || s.equals(s2)) {
            return s2;
        }
        return null;
    }
    
    private static void logRequest(final Intent intent) {
        if (RemotePlaybackClient.DEBUG) {
            Log.d("RemotePlaybackClient", "Sending request: " + intent);
        }
    }
    
    private void performItemAction(final Intent intent, final String s, final String s2, final Bundle bundle, final RemotePlaybackClient$ItemActionCallback remotePlaybackClient$ItemActionCallback) {
        intent.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
        if (s != null) {
            intent.putExtra("android.media.intent.extra.SESSION_ID", s);
        }
        if (s2 != null) {
            intent.putExtra("android.media.intent.extra.ITEM_ID", s2);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        logRequest(intent);
        this.mRoute.sendControlRequest(intent, new RemotePlaybackClient$1(this, s, s2, intent, remotePlaybackClient$ItemActionCallback));
    }
    
    private void performSessionAction(final Intent intent, final String s, final Bundle bundle, final RemotePlaybackClient$SessionActionCallback remotePlaybackClient$SessionActionCallback) {
        intent.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
        if (s != null) {
            intent.putExtra("android.media.intent.extra.SESSION_ID", s);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        logRequest(intent);
        this.mRoute.sendControlRequest(intent, new RemotePlaybackClient$2(this, s, intent, remotePlaybackClient$SessionActionCallback));
    }
    
    private void playOrEnqueue(final Uri uri, final String s, final Bundle bundle, final long n, final Bundle bundle2, final RemotePlaybackClient$ItemActionCallback remotePlaybackClient$ItemActionCallback, final String s2) {
        if (uri == null) {
            throw new IllegalArgumentException("contentUri must not be null");
        }
        this.throwIfRemotePlaybackNotSupported();
        if (s2.equals("android.media.intent.action.ENQUEUE")) {
            this.throwIfQueuingNotSupported();
        }
        final Intent intent = new Intent(s2);
        intent.setDataAndType(uri, s);
        intent.putExtra("android.media.intent.extra.ITEM_STATUS_UPDATE_RECEIVER", (Parcelable)this.mItemStatusPendingIntent);
        if (bundle != null) {
            intent.putExtra("android.media.intent.extra.ITEM_METADATA", bundle);
        }
        if (n != 0L) {
            intent.putExtra("android.media.intent.extra.ITEM_POSITION", n);
        }
        this.performItemAction(intent, this.mSessionId, null, bundle2, remotePlaybackClient$ItemActionCallback);
    }
    
    private boolean routeSupportsAction(final String s) {
        return this.mRoute.supportsControlAction("android.media.intent.category.REMOTE_PLAYBACK", s);
    }
    
    private void throwIfNoCurrentSession() {
        if (this.mSessionId == null) {
            throw new IllegalStateException("There is no current session.");
        }
    }
    
    private void throwIfQueuingNotSupported() {
        if (!this.mRouteSupportsQueuing) {
            throw new UnsupportedOperationException("The route does not support queuing.");
        }
    }
    
    private void throwIfRemotePlaybackNotSupported() {
        if (!this.mRouteSupportsRemotePlayback) {
            throw new UnsupportedOperationException("The route does not support remote playback.");
        }
    }
    
    private void throwIfSessionManagementNotSupported() {
        if (!this.mRouteSupportsSessionManagement) {
            throw new UnsupportedOperationException("The route does not support session management.");
        }
    }
    
    public void endSession(final Bundle bundle, final RemotePlaybackClient$SessionActionCallback remotePlaybackClient$SessionActionCallback) {
        this.throwIfSessionManagementNotSupported();
        this.throwIfNoCurrentSession();
        this.performSessionAction(new Intent("android.media.intent.action.END_SESSION"), this.mSessionId, bundle, remotePlaybackClient$SessionActionCallback);
    }
    
    public void enqueue(final Uri uri, final String s, final Bundle bundle, final long n, final Bundle bundle2, final RemotePlaybackClient$ItemActionCallback remotePlaybackClient$ItemActionCallback) {
        this.playOrEnqueue(uri, s, bundle, n, bundle2, remotePlaybackClient$ItemActionCallback, "android.media.intent.action.ENQUEUE");
    }
    
    public String getSessionId() {
        return this.mSessionId;
    }
    
    public void getSessionStatus(final Bundle bundle, final RemotePlaybackClient$SessionActionCallback remotePlaybackClient$SessionActionCallback) {
        this.throwIfSessionManagementNotSupported();
        this.throwIfNoCurrentSession();
        this.performSessionAction(new Intent("android.media.intent.action.GET_SESSION_STATUS"), this.mSessionId, bundle, remotePlaybackClient$SessionActionCallback);
    }
    
    public void getStatus(final String s, final Bundle bundle, final RemotePlaybackClient$ItemActionCallback remotePlaybackClient$ItemActionCallback) {
        if (s == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        this.throwIfNoCurrentSession();
        this.performItemAction(new Intent("android.media.intent.action.GET_STATUS"), this.mSessionId, s, bundle, remotePlaybackClient$ItemActionCallback);
    }
    
    public boolean hasSession() {
        return this.mSessionId != null;
    }
    
    public boolean isQueuingSupported() {
        return this.mRouteSupportsQueuing;
    }
    
    public boolean isRemotePlaybackSupported() {
        return this.mRouteSupportsRemotePlayback;
    }
    
    public boolean isSessionManagementSupported() {
        return this.mRouteSupportsSessionManagement;
    }
    
    public void pause(final Bundle bundle, final RemotePlaybackClient$SessionActionCallback remotePlaybackClient$SessionActionCallback) {
        this.throwIfNoCurrentSession();
        this.performSessionAction(new Intent("android.media.intent.action.PAUSE"), this.mSessionId, bundle, remotePlaybackClient$SessionActionCallback);
    }
    
    public void play(final Uri uri, final String s, final Bundle bundle, final long n, final Bundle bundle2, final RemotePlaybackClient$ItemActionCallback remotePlaybackClient$ItemActionCallback) {
        this.playOrEnqueue(uri, s, bundle, n, bundle2, remotePlaybackClient$ItemActionCallback, "android.media.intent.action.PLAY");
    }
    
    public void release() {
        this.mContext.unregisterReceiver((BroadcastReceiver)this.mStatusReceiver);
    }
    
    public void remove(final String s, final Bundle bundle, final RemotePlaybackClient$ItemActionCallback remotePlaybackClient$ItemActionCallback) {
        if (s == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        this.throwIfQueuingNotSupported();
        this.throwIfNoCurrentSession();
        this.performItemAction(new Intent("android.media.intent.action.REMOVE"), this.mSessionId, s, bundle, remotePlaybackClient$ItemActionCallback);
    }
    
    public void resume(final Bundle bundle, final RemotePlaybackClient$SessionActionCallback remotePlaybackClient$SessionActionCallback) {
        this.throwIfNoCurrentSession();
        this.performSessionAction(new Intent("android.media.intent.action.RESUME"), this.mSessionId, bundle, remotePlaybackClient$SessionActionCallback);
    }
    
    public void seek(final String s, final long n, final Bundle bundle, final RemotePlaybackClient$ItemActionCallback remotePlaybackClient$ItemActionCallback) {
        if (s == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        this.throwIfNoCurrentSession();
        final Intent intent = new Intent("android.media.intent.action.SEEK");
        intent.putExtra("android.media.intent.extra.ITEM_POSITION", n);
        this.performItemAction(intent, this.mSessionId, s, bundle, remotePlaybackClient$ItemActionCallback);
    }
    
    public void setSessionId(final String mSessionId) {
        if (this.mSessionId != mSessionId && (this.mSessionId == null || !this.mSessionId.equals(mSessionId))) {
            if (RemotePlaybackClient.DEBUG) {
                Log.d("RemotePlaybackClient", "Session id is now: " + mSessionId);
            }
            this.mSessionId = mSessionId;
            if (this.mStatusCallback != null) {
                this.mStatusCallback.onSessionChanged(mSessionId);
            }
        }
    }
    
    public void setStatusCallback(final RemotePlaybackClient$StatusCallback mStatusCallback) {
        this.mStatusCallback = mStatusCallback;
    }
    
    public void startSession(final Bundle bundle, final RemotePlaybackClient$SessionActionCallback remotePlaybackClient$SessionActionCallback) {
        this.throwIfSessionManagementNotSupported();
        final Intent intent = new Intent("android.media.intent.action.START_SESSION");
        intent.putExtra("android.media.intent.extra.SESSION_STATUS_UPDATE_RECEIVER", (Parcelable)this.mSessionStatusPendingIntent);
        this.performSessionAction(intent, null, bundle, remotePlaybackClient$SessionActionCallback);
    }
    
    public void stop(final Bundle bundle, final RemotePlaybackClient$SessionActionCallback remotePlaybackClient$SessionActionCallback) {
        this.throwIfNoCurrentSession();
        this.performSessionAction(new Intent("android.media.intent.action.STOP"), this.mSessionId, bundle, remotePlaybackClient$SessionActionCallback);
    }
}
