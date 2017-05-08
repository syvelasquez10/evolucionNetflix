// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.provider.Settings$System;
import android.view.View$OnClickListener;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.Activity;
import android.media.AudioManager;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.TimelineSeekBar;
import android.view.OrientationEventListener;
import android.widget.ImageButton;
import android.view.View;
import android.os.Handler;
import android.content.Context;
import android.database.ContentObserver;

class CoppolaControlsDecorator$VolumeContentObserver extends ContentObserver
{
    final /* synthetic */ CoppolaControlsDecorator this$0;
    
    public CoppolaControlsDecorator$VolumeContentObserver(final CoppolaControlsDecorator this$0, final Context context, final Handler handler) {
        this.this$0 = this$0;
        super(handler);
    }
    
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }
    
    public void onChange(final boolean b) {
        super.onChange(b);
        this.this$0.updateUserVolume();
        this.this$0.updateMuteDrawable();
    }
}
