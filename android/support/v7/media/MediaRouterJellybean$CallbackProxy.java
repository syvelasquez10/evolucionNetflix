// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.media.MediaRouter$RouteGroup;
import android.media.MediaRouter$RouteInfo;
import android.media.MediaRouter;
import android.media.MediaRouter$Callback;

class MediaRouterJellybean$CallbackProxy<T extends MediaRouterJellybean$Callback> extends MediaRouter$Callback
{
    protected final T mCallback;
    
    public MediaRouterJellybean$CallbackProxy(final T mCallback) {
        this.mCallback = mCallback;
    }
    
    public void onRouteAdded(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.mCallback.onRouteAdded(mediaRouter$RouteInfo);
    }
    
    public void onRouteChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.mCallback.onRouteChanged(mediaRouter$RouteInfo);
    }
    
    public void onRouteGrouped(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo, final MediaRouter$RouteGroup mediaRouter$RouteGroup, final int n) {
        this.mCallback.onRouteGrouped(mediaRouter$RouteInfo, mediaRouter$RouteGroup, n);
    }
    
    public void onRouteRemoved(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.mCallback.onRouteRemoved(mediaRouter$RouteInfo);
    }
    
    public void onRouteSelected(final MediaRouter mediaRouter, final int n, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.mCallback.onRouteSelected(n, mediaRouter$RouteInfo);
    }
    
    public void onRouteUngrouped(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo, final MediaRouter$RouteGroup mediaRouter$RouteGroup) {
        this.mCallback.onRouteUngrouped(mediaRouter$RouteInfo, mediaRouter$RouteGroup);
    }
    
    public void onRouteUnselected(final MediaRouter mediaRouter, final int n, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.mCallback.onRouteUnselected(n, mediaRouter$RouteInfo);
    }
    
    public void onRouteVolumeChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.mCallback.onRouteVolumeChanged(mediaRouter$RouteInfo);
    }
}
