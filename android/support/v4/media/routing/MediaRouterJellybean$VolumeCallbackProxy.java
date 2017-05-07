// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.routing;

import android.media.MediaRouter$RouteInfo;
import android.media.MediaRouter$VolumeCallback;

class MediaRouterJellybean$VolumeCallbackProxy<T extends MediaRouterJellybean$VolumeCallback> extends MediaRouter$VolumeCallback
{
    protected final T mCallback;
    
    public MediaRouterJellybean$VolumeCallbackProxy(final T mCallback) {
        this.mCallback = mCallback;
    }
    
    public void onVolumeSetRequest(final MediaRouter$RouteInfo mediaRouter$RouteInfo, final int n) {
        this.mCallback.onVolumeSetRequest(mediaRouter$RouteInfo, n);
    }
    
    public void onVolumeUpdateRequest(final MediaRouter$RouteInfo mediaRouter$RouteInfo, final int n) {
        this.mCallback.onVolumeUpdateRequest(mediaRouter$RouteInfo, n);
    }
}
