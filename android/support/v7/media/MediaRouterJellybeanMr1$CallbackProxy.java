// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.media.MediaRouter$RouteInfo;
import android.media.MediaRouter;

class MediaRouterJellybeanMr1$CallbackProxy<T extends MediaRouterJellybeanMr1$Callback> extends MediaRouterJellybean$CallbackProxy<T>
{
    public MediaRouterJellybeanMr1$CallbackProxy(final T t) {
        super(t);
    }
    
    public void onRoutePresentationDisplayChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.mCallback.onRoutePresentationDisplayChanged(mediaRouter$RouteInfo);
    }
}
