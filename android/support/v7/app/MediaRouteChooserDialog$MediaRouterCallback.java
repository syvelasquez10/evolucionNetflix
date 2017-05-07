// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.media.MediaRouter$RouteInfo;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter$Callback;

final class MediaRouteChooserDialog$MediaRouterCallback extends MediaRouter$Callback
{
    final /* synthetic */ MediaRouteChooserDialog this$0;
    
    private MediaRouteChooserDialog$MediaRouterCallback(final MediaRouteChooserDialog this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRouteAdded(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.refreshRoutes();
    }
    
    @Override
    public void onRouteChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.refreshRoutes();
    }
    
    @Override
    public void onRouteRemoved(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.refreshRoutes();
    }
    
    @Override
    public void onRouteSelected(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.dismiss();
    }
}
