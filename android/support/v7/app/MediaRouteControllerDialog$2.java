// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.KeyEvent;
import android.widget.FrameLayout;
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
import android.app.Dialog;
import android.view.View;
import android.view.View$OnClickListener;

class MediaRouteControllerDialog$2 implements View$OnClickListener
{
    final /* synthetic */ MediaRouteControllerDialog this$0;
    
    MediaRouteControllerDialog$2(final MediaRouteControllerDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.mRoute.isSelected()) {
            this.this$0.mRouter.getDefaultRoute().select();
        }
        this.this$0.dismiss();
    }
}
