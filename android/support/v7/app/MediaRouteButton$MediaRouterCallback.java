// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.util.Log;
import android.widget.Toast;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View$MeasureSpec;
import android.graphics.Canvas;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.annotation.NonNull;
import android.graphics.drawable.Drawable$Callback;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.ContextWrapper;
import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v7.mediarouter.R$styleable;
import android.support.v7.mediarouter.R$attr;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.media.MediaRouteSelector;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.support.v7.media.MediaRouter$RouteInfo;
import android.support.v7.media.MediaRouter$ProviderInfo;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter$Callback;

final class MediaRouteButton$MediaRouterCallback extends MediaRouter$Callback
{
    final /* synthetic */ MediaRouteButton this$0;
    
    private MediaRouteButton$MediaRouterCallback(final MediaRouteButton this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onProviderAdded(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
        this.this$0.refreshRoute();
    }
    
    @Override
    public void onProviderChanged(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
        this.this$0.refreshRoute();
    }
    
    @Override
    public void onProviderRemoved(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
        this.this$0.refreshRoute();
    }
    
    @Override
    public void onRouteAdded(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.refreshRoute();
    }
    
    @Override
    public void onRouteChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.refreshRoute();
    }
    
    @Override
    public void onRouteRemoved(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.refreshRoute();
    }
    
    @Override
    public void onRouteSelected(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.refreshRoute();
    }
    
    @Override
    public void onRouteUnselected(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.refreshRoute();
    }
}
