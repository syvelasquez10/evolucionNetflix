// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.view.View$OnClickListener;
import android.support.v7.mediarouter.R$id;
import android.support.v7.mediarouter.R$layout;
import android.os.Bundle;
import android.support.v7.media.MediaRouter$Callback;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.mediarouter.R$attr;
import android.content.Context;
import android.widget.LinearLayout;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter$RouteInfo;
import android.widget.Button;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.app.Dialog;
import android.widget.SeekBar;
import android.widget.SeekBar$OnSeekBarChangeListener;

class MediaRouteControllerDialog$1 implements SeekBar$OnSeekBarChangeListener
{
    private final Runnable mStopTrackingTouch;
    final /* synthetic */ MediaRouteControllerDialog this$0;
    
    MediaRouteControllerDialog$1(final MediaRouteControllerDialog this$0) {
        this.this$0 = this$0;
        this.mStopTrackingTouch = new MediaRouteControllerDialog$1$1(this);
    }
    
    public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
        if (b) {
            this.this$0.mRoute.requestSetVolume(n);
        }
    }
    
    public void onStartTrackingTouch(final SeekBar seekBar) {
        if (this.this$0.mVolumeSliderTouched) {
            this.this$0.mVolumeSlider.removeCallbacks(this.mStopTrackingTouch);
            return;
        }
        this.this$0.mVolumeSliderTouched = true;
    }
    
    public void onStopTrackingTouch(final SeekBar seekBar) {
        this.this$0.mVolumeSlider.postDelayed(this.mStopTrackingTouch, 250L);
    }
}
