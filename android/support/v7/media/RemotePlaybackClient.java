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
    private final MediaRouter.RouteInfo mRoute;
    private boolean mRouteSupportsQueuing;
    private boolean mRouteSupportsRemotePlayback;
    private boolean mRouteSupportsSessionManagement;
    private String mSessionId;
    private final PendingIntent mSessionStatusPendingIntent;
    private StatusCallback mStatusCallback;
    private final StatusReceiver mStatusReceiver;
    
    static {
        DEBUG = Log.isLoggable("RemotePlaybackClient", 3);
    }
    
    public RemotePlaybackClient(final Context mContext, final MediaRouter.RouteInfo mRoute) {
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
        mContext.registerReceiver((BroadcastReceiver)(this.mStatusReceiver = new StatusReceiver()), intentFilter);
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
    
    private void handleError(final Intent intent, final ActionCallback actionCallback, final String s, final Bundle bundle) {
        int int1;
        if (bundle != null) {
            int1 = bundle.getInt("android.media.intent.extra.ERROR_CODE", 0);
        }
        else {
            int1 = 0;
        }
        if (RemotePlaybackClient.DEBUG) {
            Log.w("RemotePlaybackClient", "Received error from " + intent.getAction() + ": error=" + s + ", code=" + int1 + ", data=" + bundleToString(bundle));
        }
        actionCallback.onError(s, int1, bundle);
    }
    
    private void handleInvalidResult(final Intent intent, final ActionCallback actionCallback, final Bundle bundle) {
        Log.w("RemotePlaybackClient", "Received invalid result data from " + intent.getAction() + ": data=" + bundleToString(bundle));
        actionCallback.onError(null, 0, bundle);
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
    
    private void performItemAction(final Intent intent, final String s, final String s2, final Bundle bundle, final ItemActionCallback itemActionCallback) {
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
        this.mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() {
            @Override
            public void onError(final String s, final Bundle bundle) {
                RemotePlaybackClient.this.handleError(intent, (ActionCallback)itemActionCallback, s, bundle);
            }
            
            @Override
            public void onResult(final Bundle bundle) {
                if (bundle != null) {
                    final String access$100 = inferMissingResult(s, bundle.getString("android.media.intent.extra.SESSION_ID"));
                    final MediaSessionStatus fromBundle = MediaSessionStatus.fromBundle(bundle.getBundle("android.media.intent.extra.SESSION_STATUS"));
                    final String access$101 = inferMissingResult(s2, bundle.getString("android.media.intent.extra.ITEM_ID"));
                    final MediaItemStatus fromBundle2 = MediaItemStatus.fromBundle(bundle.getBundle("android.media.intent.extra.ITEM_STATUS"));
                    RemotePlaybackClient.this.adoptSession(access$100);
                    if (access$100 != null && access$101 != null && fromBundle2 != null) {
                        if (RemotePlaybackClient.DEBUG) {
                            Log.d("RemotePlaybackClient", "Received result from " + intent.getAction() + ": data=" + bundleToString(bundle) + ", sessionId=" + access$100 + ", sessionStatus=" + fromBundle + ", itemId=" + access$101 + ", itemStatus=" + fromBundle2);
                        }
                        itemActionCallback.onResult(bundle, access$100, fromBundle, access$101, fromBundle2);
                        return;
                    }
                }
                RemotePlaybackClient.this.handleInvalidResult(intent, (ActionCallback)itemActionCallback, bundle);
            }
        });
    }
    
    private void performSessionAction(final Intent intent, final String s, final Bundle bundle, final SessionActionCallback sessionActionCallback) {
        intent.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
        if (s != null) {
            intent.putExtra("android.media.intent.extra.SESSION_ID", s);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        logRequest(intent);
        this.mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() {
            @Override
            public void onError(final String s, final Bundle bundle) {
                RemotePlaybackClient.this.handleError(intent, (ActionCallback)sessionActionCallback, s, bundle);
            }
            
            @Override
            public void onResult(final Bundle bundle) {
                if (bundle != null) {
                    final String access$100 = inferMissingResult(s, bundle.getString("android.media.intent.extra.SESSION_ID"));
                    final MediaSessionStatus fromBundle = MediaSessionStatus.fromBundle(bundle.getBundle("android.media.intent.extra.SESSION_STATUS"));
                    RemotePlaybackClient.this.adoptSession(access$100);
                    if (access$100 != null) {
                        if (RemotePlaybackClient.DEBUG) {
                            Log.d("RemotePlaybackClient", "Received result from " + intent.getAction() + ": data=" + bundleToString(bundle) + ", sessionId=" + access$100 + ", sessionStatus=" + fromBundle);
                        }
                        try {
                            sessionActionCallback.onResult(bundle, access$100, fromBundle);
                            return;
                        }
                        finally {
                            if (intent.getAction().equals("android.media.intent.action.END_SESSION") && access$100.equals(RemotePlaybackClient.this.mSessionId)) {
                                RemotePlaybackClient.this.setSessionId(null);
                            }
                        }
                    }
                }
                RemotePlaybackClient.this.handleInvalidResult(intent, (ActionCallback)sessionActionCallback, bundle);
            }
        });
    }
    
    private void playOrEnqueue(final Uri uri, final String s, final Bundle bundle, final long n, final Bundle bundle2, final ItemActionCallback itemActionCallback, final String s2) {
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
        this.performItemAction(intent, this.mSessionId, null, bundle2, itemActionCallback);
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
    
    public void endSession(final Bundle bundle, final SessionActionCallback sessionActionCallback) {
        this.throwIfSessionManagementNotSupported();
        this.throwIfNoCurrentSession();
        this.performSessionAction(new Intent("android.media.intent.action.END_SESSION"), this.mSessionId, bundle, sessionActionCallback);
    }
    
    public void enqueue(final Uri uri, final String s, final Bundle bundle, final long n, final Bundle bundle2, final ItemActionCallback itemActionCallback) {
        this.playOrEnqueue(uri, s, bundle, n, bundle2, itemActionCallback, "android.media.intent.action.ENQUEUE");
    }
    
    public String getSessionId() {
        return this.mSessionId;
    }
    
    public void getSessionStatus(final Bundle bundle, final SessionActionCallback sessionActionCallback) {
        this.throwIfSessionManagementNotSupported();
        this.throwIfNoCurrentSession();
        this.performSessionAction(new Intent("android.media.intent.action.GET_SESSION_STATUS"), this.mSessionId, bundle, sessionActionCallback);
    }
    
    public void getStatus(final String s, final Bundle bundle, final ItemActionCallback itemActionCallback) {
        if (s == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        this.throwIfNoCurrentSession();
        this.performItemAction(new Intent("android.media.intent.action.GET_STATUS"), this.mSessionId, s, bundle, itemActionCallback);
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
    
    public void pause(final Bundle bundle, final SessionActionCallback sessionActionCallback) {
        this.throwIfNoCurrentSession();
        this.performSessionAction(new Intent("android.media.intent.action.PAUSE"), this.mSessionId, bundle, sessionActionCallback);
    }
    
    public void play(final Uri uri, final String s, final Bundle bundle, final long n, final Bundle bundle2, final ItemActionCallback itemActionCallback) {
        this.playOrEnqueue(uri, s, bundle, n, bundle2, itemActionCallback, "android.media.intent.action.PLAY");
    }
    
    public void release() {
        this.mContext.unregisterReceiver((BroadcastReceiver)this.mStatusReceiver);
    }
    
    public void remove(final String s, final Bundle bundle, final ItemActionCallback itemActionCallback) {
        if (s == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        this.throwIfQueuingNotSupported();
        this.throwIfNoCurrentSession();
        this.performItemAction(new Intent("android.media.intent.action.REMOVE"), this.mSessionId, s, bundle, itemActionCallback);
    }
    
    public void resume(final Bundle bundle, final SessionActionCallback sessionActionCallback) {
        this.throwIfNoCurrentSession();
        this.performSessionAction(new Intent("android.media.intent.action.RESUME"), this.mSessionId, bundle, sessionActionCallback);
    }
    
    public void seek(final String s, final long n, final Bundle bundle, final ItemActionCallback itemActionCallback) {
        if (s == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        this.throwIfNoCurrentSession();
        final Intent intent = new Intent("android.media.intent.action.SEEK");
        intent.putExtra("android.media.intent.extra.ITEM_POSITION", n);
        this.performItemAction(intent, this.mSessionId, s, bundle, itemActionCallback);
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
    
    public void setStatusCallback(final StatusCallback mStatusCallback) {
        this.mStatusCallback = mStatusCallback;
    }
    
    public void startSession(final Bundle bundle, final SessionActionCallback sessionActionCallback) {
        this.throwIfSessionManagementNotSupported();
        final Intent intent = new Intent("android.media.intent.action.START_SESSION");
        intent.putExtra("android.media.intent.extra.SESSION_STATUS_UPDATE_RECEIVER", (Parcelable)this.mSessionStatusPendingIntent);
        this.performSessionAction(intent, null, bundle, sessionActionCallback);
    }
    
    public void stop(final Bundle bundle, final SessionActionCallback sessionActionCallback) {
        this.throwIfNoCurrentSession();
        this.performSessionAction(new Intent("android.media.intent.action.STOP"), this.mSessionId, bundle, sessionActionCallback);
    }
    
    public abstract static class ActionCallback
    {
        public void onError(final String s, final int n, final Bundle bundle) {
        }
    }
    
    public abstract static class ItemActionCallback extends ActionCallback
    {
        public void onResult(final Bundle bundle, final String s, final MediaSessionStatus mediaSessionStatus, final String s2, final MediaItemStatus mediaItemStatus) {
        }
    }
    
    public abstract static class SessionActionCallback extends ActionCallback
    {
        public void onResult(final Bundle bundle, final String s, final MediaSessionStatus mediaSessionStatus) {
        }
    }
    
    public abstract static class StatusCallback
    {
        public void onItemStatusChanged(final Bundle bundle, final String s, final MediaSessionStatus mediaSessionStatus, final String s2, final MediaItemStatus mediaItemStatus) {
        }
        
        public void onSessionChanged(final String s) {
        }
        
        public void onSessionStatusChanged(final Bundle bundle, final String s, final MediaSessionStatus mediaSessionStatus) {
        }
    }
    
    private final class StatusReceiver extends BroadcastReceiver
    {
        public static final String ACTION_ITEM_STATUS_CHANGED = "android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED";
        public static final String ACTION_SESSION_STATUS_CHANGED = "android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED";
        
        public void onReceive(final Context context, final Intent intent) {
            final String stringExtra = intent.getStringExtra("android.media.intent.extra.SESSION_ID");
            if (stringExtra == null || !stringExtra.equals(RemotePlaybackClient.this.mSessionId)) {
                Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing or invalid session id: sessionId=" + stringExtra);
            }
            else {
                final MediaSessionStatus fromBundle = MediaSessionStatus.fromBundle(intent.getBundleExtra("android.media.intent.extra.SESSION_STATUS"));
                final String action = intent.getAction();
                if (action.equals("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED")) {
                    final String stringExtra2 = intent.getStringExtra("android.media.intent.extra.ITEM_ID");
                    if (stringExtra2 == null) {
                        Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item id.");
                        return;
                    }
                    final MediaItemStatus fromBundle2 = MediaItemStatus.fromBundle(intent.getBundleExtra("android.media.intent.extra.ITEM_STATUS"));
                    if (fromBundle2 == null) {
                        Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item status.");
                        return;
                    }
                    if (RemotePlaybackClient.DEBUG) {
                        Log.d("RemotePlaybackClient", "Received item status callback: sessionId=" + stringExtra + ", sessionStatus=" + fromBundle + ", itemId=" + stringExtra2 + ", itemStatus=" + fromBundle2);
                    }
                    if (RemotePlaybackClient.this.mStatusCallback != null) {
                        RemotePlaybackClient.this.mStatusCallback.onItemStatusChanged(intent.getExtras(), stringExtra, fromBundle, stringExtra2, fromBundle2);
                    }
                }
                else if (action.equals("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED")) {
                    if (fromBundle == null) {
                        Log.w("RemotePlaybackClient", "Discarding spurious media status callback with missing session status.");
                        return;
                    }
                    if (RemotePlaybackClient.DEBUG) {
                        Log.d("RemotePlaybackClient", "Received session status callback: sessionId=" + stringExtra + ", sessionStatus=" + fromBundle);
                    }
                    if (RemotePlaybackClient.this.mStatusCallback != null) {
                        RemotePlaybackClient.this.mStatusCallback.onSessionStatusChanged(intent.getExtras(), stringExtra, fromBundle);
                    }
                }
            }
        }
    }
}
