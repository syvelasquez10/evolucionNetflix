// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.ViewGroup$LayoutParams;
import android.util.Log;
import android.view.View;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.content.Context;
import android.support.v7.media.MediaRouteSelector;
import android.support.v4.view.ActionProvider;
import android.support.v7.media.MediaRouter$RouteInfo;
import android.support.v7.media.MediaRouter$ProviderInfo;
import android.support.v7.media.MediaRouter;
import java.lang.ref.WeakReference;
import android.support.v7.media.MediaRouter$Callback;

final class MediaRouteActionProvider$MediaRouterCallback extends MediaRouter$Callback
{
    private final WeakReference<MediaRouteActionProvider> mProviderWeak;
    
    public MediaRouteActionProvider$MediaRouterCallback(final MediaRouteActionProvider mediaRouteActionProvider) {
        this.mProviderWeak = new WeakReference<MediaRouteActionProvider>(mediaRouteActionProvider);
    }
    
    private void refreshRoute(final MediaRouter mediaRouter) {
        final MediaRouteActionProvider mediaRouteActionProvider = this.mProviderWeak.get();
        if (mediaRouteActionProvider != null) {
            mediaRouteActionProvider.refreshRoute();
            return;
        }
        mediaRouter.removeCallback(this);
    }
    
    @Override
    public void onProviderAdded(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
        this.refreshRoute(mediaRouter);
    }
    
    @Override
    public void onProviderChanged(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
        this.refreshRoute(mediaRouter);
    }
    
    @Override
    public void onProviderRemoved(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
        this.refreshRoute(mediaRouter);
    }
    
    @Override
    public void onRouteAdded(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.refreshRoute(mediaRouter);
    }
    
    @Override
    public void onRouteChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.refreshRoute(mediaRouter);
    }
    
    @Override
    public void onRouteRemoved(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.refreshRoute(mediaRouter);
    }
}
