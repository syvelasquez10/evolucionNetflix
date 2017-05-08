// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.media.browse.MediaBrowser$MediaItem;
import java.util.List;
import android.media.browse.MediaBrowser$SubscriptionCallback;

class MediaBrowserCompatApi21$SubscriptionCallbackProxy<T extends MediaBrowserCompatApi21$SubscriptionCallback> extends MediaBrowser$SubscriptionCallback
{
    protected final T mSubscriptionCallback;
    
    public MediaBrowserCompatApi21$SubscriptionCallbackProxy(final T mSubscriptionCallback) {
        this.mSubscriptionCallback = mSubscriptionCallback;
    }
    
    public void onChildrenLoaded(final String s, final List<MediaBrowser$MediaItem> list) {
        this.mSubscriptionCallback.onChildrenLoaded(s, list);
    }
    
    public void onError(final String s) {
        this.mSubscriptionCallback.onError(s);
    }
}
