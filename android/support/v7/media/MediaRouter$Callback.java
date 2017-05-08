// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

public abstract class MediaRouter$Callback
{
    public void onProviderAdded(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
    }
    
    public void onProviderChanged(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
    }
    
    public void onProviderRemoved(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
    }
    
    public void onRouteAdded(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
    
    public void onRouteChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
    
    public void onRoutePresentationDisplayChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
    
    public void onRouteRemoved(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
    
    public void onRouteSelected(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
    
    public void onRouteUnselected(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
    
    public void onRouteUnselected(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo, final int n) {
        this.onRouteUnselected(mediaRouter, mediaRouter$RouteInfo);
    }
    
    public void onRouteVolumeChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
}
