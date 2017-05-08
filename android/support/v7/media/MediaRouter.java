// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Looper;
import android.util.Log;
import android.content.Context;
import java.util.ArrayList;

public final class MediaRouter
{
    static final boolean DEBUG;
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
    
    public static MediaRouter getInstance(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        checkCallingThread();
        if (MediaRouter.sGlobal == null) {
            (MediaRouter.sGlobal = new MediaRouter$GlobalMediaRouter(context.getApplicationContext())).start();
        }
        return MediaRouter.sGlobal.getRouter(context);
    }
    
    public void addCallback(final MediaRouteSelector mediaRouteSelector, final MediaRouter$Callback mediaRouter$Callback, int n) {
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
    
    public MediaRouter$RouteInfo getSelectedRoute() {
        checkCallingThread();
        return MediaRouter.sGlobal.getSelectedRoute();
    }
    
    public void removeCallback(final MediaRouter$Callback mediaRouter$Callback) {
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
    
    public void selectRoute(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (mediaRouter$RouteInfo == null) {
            throw new IllegalArgumentException("route must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "selectRoute: " + mediaRouter$RouteInfo);
        }
        MediaRouter.sGlobal.selectRoute(mediaRouter$RouteInfo);
    }
    
    public void unselect(final int n) {
        if (n < 0 || n > 3) {
            throw new IllegalArgumentException("Unsupported reason to unselect route");
        }
        checkCallingThread();
        final MediaRouter$RouteInfo chooseFallbackRoute = MediaRouter.sGlobal.chooseFallbackRoute();
        if (MediaRouter.sGlobal.getSelectedRoute() != chooseFallbackRoute) {
            MediaRouter.sGlobal.selectRoute(chooseFallbackRoute, n);
            return;
        }
        MediaRouter.sGlobal.selectRoute(MediaRouter.sGlobal.getDefaultRoute(), n);
    }
}
