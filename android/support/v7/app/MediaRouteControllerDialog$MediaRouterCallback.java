// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.support.v7.mediarouter.R$id;
import android.support.v7.mediarouter.R$layout;
import android.os.Bundle;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.mediarouter.R$attr;
import android.content.Context;
import android.widget.SeekBar;
import android.widget.LinearLayout;
import android.widget.Button;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.app.Dialog;
import android.support.v7.media.MediaRouter$RouteInfo;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter$Callback;

final class MediaRouteControllerDialog$MediaRouterCallback extends MediaRouter$Callback
{
    final /* synthetic */ MediaRouteControllerDialog this$0;
    
    private MediaRouteControllerDialog$MediaRouterCallback(final MediaRouteControllerDialog this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRouteChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.update();
    }
    
    @Override
    public void onRouteUnselected(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.this$0.update();
    }
    
    @Override
    public void onRouteVolumeChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (mediaRouter$RouteInfo == this.this$0.mRoute) {
            this.this$0.updateVolume();
        }
    }
}
