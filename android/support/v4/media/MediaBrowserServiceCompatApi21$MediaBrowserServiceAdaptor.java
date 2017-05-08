// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Parcel;
import android.media.browse.MediaBrowser$MediaItem;
import java.util.List;
import android.service.media.MediaBrowserService$Result;
import android.service.media.MediaBrowserService$BrowserRoot;
import android.os.Bundle;
import android.content.Context;
import android.service.media.MediaBrowserService;

class MediaBrowserServiceCompatApi21$MediaBrowserServiceAdaptor extends MediaBrowserService
{
    final MediaBrowserServiceCompatApi21$ServiceCompatProxy mServiceProxy;
    
    MediaBrowserServiceCompatApi21$MediaBrowserServiceAdaptor(final Context context, final MediaBrowserServiceCompatApi21$ServiceCompatProxy mServiceProxy) {
        this.attachBaseContext(context);
        this.mServiceProxy = mServiceProxy;
    }
    
    public MediaBrowserService$BrowserRoot onGetRoot(final String s, final int n, final Bundle bundle) {
        final MediaBrowserServiceCompatApi21$BrowserRoot onGetRoot = this.mServiceProxy.onGetRoot(s, n, bundle);
        if (onGetRoot == null) {
            return null;
        }
        return new MediaBrowserService$BrowserRoot(onGetRoot.mRootId, onGetRoot.mExtras);
    }
    
    public void onLoadChildren(final String s, final MediaBrowserService$Result<List<MediaBrowser$MediaItem>> mediaBrowserService$Result) {
        this.mServiceProxy.onLoadChildren(s, new MediaBrowserServiceCompatApi21$ResultWrapper<List<Parcel>>(mediaBrowserService$Result));
    }
}
