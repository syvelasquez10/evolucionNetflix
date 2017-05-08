// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Bundle;
import android.media.browse.MediaBrowser$MediaItem;
import java.util.List;

class MediaBrowserCompatApi24$SubscriptionCallbackProxy<T extends MediaBrowserCompatApi24$SubscriptionCallback> extends MediaBrowserCompatApi21$SubscriptionCallbackProxy<T>
{
    public MediaBrowserCompatApi24$SubscriptionCallbackProxy(final T t) {
        super(t);
    }
    
    public void onChildrenLoaded(final String s, final List<MediaBrowser$MediaItem> list, final Bundle bundle) {
        this.mSubscriptionCallback.onChildrenLoaded(s, list, bundle);
    }
    
    public void onError(final String s, final Bundle bundle) {
        this.mSubscriptionCallback.onError(s, bundle);
    }
}
