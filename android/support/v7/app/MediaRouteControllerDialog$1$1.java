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
import android.support.v7.media.MediaRouter$Callback;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.mediarouter.R$attr;
import android.content.Context;
import android.widget.SeekBar;
import android.widget.LinearLayout;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter$RouteInfo;
import android.widget.Button;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.app.Dialog;

class MediaRouteControllerDialog$1$1 implements Runnable
{
    final /* synthetic */ MediaRouteControllerDialog$1 this$1;
    
    MediaRouteControllerDialog$1$1(final MediaRouteControllerDialog$1 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        if (this.this$1.this$0.mVolumeSliderTouched) {
            this.this$1.this$0.mVolumeSliderTouched = false;
            this.this$1.this$0.updateVolume();
        }
    }
}
