// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.media.browse.MediaBrowser$SubscriptionCallback;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.annotation.TargetApi;

@TargetApi(24)
class MediaBrowserCompatApi24
{
    public static Object createSubscriptionCallback(final MediaBrowserCompatApi24$SubscriptionCallback mediaBrowserCompatApi24$SubscriptionCallback) {
        return new MediaBrowserCompatApi24$SubscriptionCallbackProxy(mediaBrowserCompatApi24$SubscriptionCallback);
    }
    
    public static void subscribe(final Object o, final String s, final Bundle bundle, final Object o2) {
        ((MediaBrowser)o).subscribe(s, bundle, (MediaBrowser$SubscriptionCallback)o2);
    }
    
    public static void unsubscribe(final Object o, final String s, final Object o2) {
        ((MediaBrowser)o).unsubscribe(s, (MediaBrowser$SubscriptionCallback)o2);
    }
}
