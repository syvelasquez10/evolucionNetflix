// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.media.browse.MediaBrowser$SubscriptionCallback;
import android.media.browse.MediaBrowser$ConnectionCallback;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.annotation.TargetApi;

@TargetApi(21)
class MediaBrowserCompatApi21
{
    static final String NULL_MEDIA_ITEM_ID = "android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM";
    
    public static void connect(final Object o) {
        ((MediaBrowser)o).connect();
    }
    
    public static Object createBrowser(final Context context, final ComponentName componentName, final Object o, final Bundle bundle) {
        return new MediaBrowser(context, componentName, (MediaBrowser$ConnectionCallback)o, bundle);
    }
    
    public static Object createConnectionCallback(final MediaBrowserCompatApi21$ConnectionCallback mediaBrowserCompatApi21$ConnectionCallback) {
        return new MediaBrowserCompatApi21$ConnectionCallbackProxy(mediaBrowserCompatApi21$ConnectionCallback);
    }
    
    public static Object createSubscriptionCallback(final MediaBrowserCompatApi21$SubscriptionCallback mediaBrowserCompatApi21$SubscriptionCallback) {
        return new MediaBrowserCompatApi21$SubscriptionCallbackProxy(mediaBrowserCompatApi21$SubscriptionCallback);
    }
    
    public static void disconnect(final Object o) {
        ((MediaBrowser)o).disconnect();
    }
    
    public static Bundle getExtras(final Object o) {
        return ((MediaBrowser)o).getExtras();
    }
    
    public static String getRoot(final Object o) {
        return ((MediaBrowser)o).getRoot();
    }
    
    public static ComponentName getServiceComponent(final Object o) {
        return ((MediaBrowser)o).getServiceComponent();
    }
    
    public static Object getSessionToken(final Object o) {
        return ((MediaBrowser)o).getSessionToken();
    }
    
    public static boolean isConnected(final Object o) {
        return ((MediaBrowser)o).isConnected();
    }
    
    public static void subscribe(final Object o, final String s, final Object o2) {
        ((MediaBrowser)o).subscribe(s, (MediaBrowser$SubscriptionCallback)o2);
    }
    
    public static void unsubscribe(final Object o, final String s) {
        ((MediaBrowser)o).unsubscribe(s);
    }
}
