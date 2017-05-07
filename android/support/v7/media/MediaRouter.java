// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.List;
import android.support.annotation.NonNull;
import android.os.Looper;
import android.util.Log;
import android.content.Context;
import java.util.ArrayList;

public final class MediaRouter
{
    public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
    public static final int AVAILABILITY_FLAG_REQUIRE_MATCH = 2;
    public static final int CALLBACK_FLAG_FORCE_DISCOVERY = 8;
    public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
    public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
    public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
    private static final boolean DEBUG;
    private static final String TAG = "MediaRouter";
    static MediaRouter$GlobalMediaRouter sGlobal;
    final ArrayList<MediaRouter$CallbackRecord> mCallbackRecords;
    final Context mContext;
    
    static {
        DEBUG = Log.isLoggable("MediaRouter", 3);
    }
    
    MediaRouter(final Context mContext) {
        this.mCallbackRecords = new ArrayList<MediaRouter$CallbackRecord>();
        this.mContext = mContext;
    }
    
    static void checkCallingThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        }
    }
    
    static <T> boolean equal(final T t, final T t2) {
        return t == t2 || (t != null && t2 != null && t.equals(t2));
    }
    
    private int findCallbackRecord(final MediaRouter$Callback mediaRouter$Callback) {
        for (int size = this.mCallbackRecords.size(), i = 0; i < size; ++i) {
            if (this.mCallbackRecords.get(i).mCallback == mediaRouter$Callback) {
                return i;
            }
        }
        return -1;
    }
    
    public static MediaRouter getInstance(@NonNull final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        checkCallingThread();
        if (MediaRouter.sGlobal == null) {
            (MediaRouter.sGlobal = new MediaRouter$GlobalMediaRouter(context.getApplicationContext())).start();
        }
        return MediaRouter.sGlobal.getRouter(context);
    }
    
    public void addCallback(final MediaRouteSelector mediaRouteSelector, final MediaRouter$Callback mediaRouter$Callback) {
        this.addCallback(mediaRouteSelector, mediaRouter$Callback, 0);
    }
    
    public void addCallback(@NonNull final MediaRouteSelector mediaRouteSelector, @NonNull final MediaRouter$Callback mediaRouter$Callback, int n) {
        final int n2 = 1;
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (mediaRouter$Callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "addCallback: selector=" + mediaRouteSelector + ", callback=" + mediaRouter$Callback + ", flags=" + Integer.toHexString(n));
        }
        final int callbackRecord = this.findCallbackRecord(mediaRouter$Callback);
        MediaRouter$CallbackRecord mediaRouter$CallbackRecord;
        if (callbackRecord < 0) {
            mediaRouter$CallbackRecord = new MediaRouter$CallbackRecord(this, mediaRouter$Callback);
            this.mCallbackRecords.add(mediaRouter$CallbackRecord);
        }
        else {
            mediaRouter$CallbackRecord = this.mCallbackRecords.get(callbackRecord);
        }
        int n3 = 0;
        if ((~mediaRouter$CallbackRecord.mFlags & n) != 0x0) {
            mediaRouter$CallbackRecord.mFlags |= n;
            n3 = 1;
        }
        if (!mediaRouter$CallbackRecord.mSelector.contains(mediaRouteSelector)) {
            mediaRouter$CallbackRecord.mSelector = new MediaRouteSelector$Builder(mediaRouter$CallbackRecord.mSelector).addSelector(mediaRouteSelector).build();
            n = n2;
        }
        else {
            n = n3;
        }
        if (n != 0) {
            MediaRouter.sGlobal.updateDiscoveryRequest();
        }
    }
    
    public void addProvider(@NonNull final MediaRouteProvider mediaRouteProvider) {
        if (mediaRouteProvider == null) {
            throw new IllegalArgumentException("providerInstance must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "addProvider: " + mediaRouteProvider);
        }
        MediaRouter.sGlobal.addProvider(mediaRouteProvider);
    }
    
    public void addRemoteControlClient(@NonNull final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("remoteControlClient must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "addRemoteControlClient: " + o);
        }
        MediaRouter.sGlobal.addRemoteControlClient(o);
    }
    
    @NonNull
    public MediaRouter$RouteInfo getDefaultRoute() {
        checkCallingThread();
        return MediaRouter.sGlobal.getDefaultRoute();
    }
    
    public List<MediaRouter$ProviderInfo> getProviders() {
        checkCallingThread();
        return MediaRouter.sGlobal.getProviders();
    }
    
    public List<MediaRouter$RouteInfo> getRoutes() {
        checkCallingThread();
        return MediaRouter.sGlobal.getRoutes();
    }
    
    @NonNull
    public MediaRouter$RouteInfo getSelectedRoute() {
        checkCallingThread();
        return MediaRouter.sGlobal.getSelectedRoute();
    }
    
    public boolean isRouteAvailable(@NonNull final MediaRouteSelector mediaRouteSelector, final int n) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        checkCallingThread();
        return MediaRouter.sGlobal.isRouteAvailable(mediaRouteSelector, n);
    }
    
    public void removeCallback(@NonNull final MediaRouter$Callback mediaRouter$Callback) {
        if (mediaRouter$Callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "removeCallback: callback=" + mediaRouter$Callback);
        }
        final int callbackRecord = this.findCallbackRecord(mediaRouter$Callback);
        if (callbackRecord >= 0) {
            this.mCallbackRecords.remove(callbackRecord);
            MediaRouter.sGlobal.updateDiscoveryRequest();
        }
    }
    
    public void removeProvider(@NonNull final MediaRouteProvider mediaRouteProvider) {
        if (mediaRouteProvider == null) {
            throw new IllegalArgumentException("providerInstance must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "removeProvider: " + mediaRouteProvider);
        }
        MediaRouter.sGlobal.removeProvider(mediaRouteProvider);
    }
    
    public void removeRemoteControlClient(@NonNull final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("remoteControlClient must not be null");
        }
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "removeRemoteControlClient: " + o);
        }
        MediaRouter.sGlobal.removeRemoteControlClient(o);
    }
    
    public void selectRoute(@NonNull final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (mediaRouter$RouteInfo == null) {
            throw new IllegalArgumentException("route must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "selectRoute: " + mediaRouter$RouteInfo);
        }
        MediaRouter.sGlobal.selectRoute(mediaRouter$RouteInfo);
    }
    
    public void setMediaSession(final Object mediaSession) {
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "addMediaSession: " + mediaSession);
        }
        MediaRouter.sGlobal.setMediaSession(mediaSession);
    }
    
    @NonNull
    public MediaRouter$RouteInfo updateSelectedRoute(@NonNull final MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "updateSelectedRoute: " + mediaRouteSelector);
        }
        MediaRouter$RouteInfo mediaRouter$RouteInfo2;
        final MediaRouter$RouteInfo mediaRouter$RouteInfo = mediaRouter$RouteInfo2 = MediaRouter.sGlobal.getSelectedRoute();
        if (!mediaRouter$RouteInfo.isDefault()) {
            mediaRouter$RouteInfo2 = mediaRouter$RouteInfo;
            if (!mediaRouter$RouteInfo.matchesSelector(mediaRouteSelector)) {
                mediaRouter$RouteInfo2 = MediaRouter.sGlobal.getDefaultRoute();
                MediaRouter.sGlobal.selectRoute(mediaRouter$RouteInfo2);
            }
        }
        return mediaRouter$RouteInfo2;
    }
}
