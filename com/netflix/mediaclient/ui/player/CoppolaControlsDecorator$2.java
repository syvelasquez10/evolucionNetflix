// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.provider.Settings$System;
import android.os.Handler;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.media.AudioManager;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.Context;
import com.netflix.mediaclient.util.CoppolaUtils;
import android.view.ViewGroup;
import android.database.ContentObserver;
import com.netflix.mediaclient.android.widget.TimelineSeekBar;
import android.view.OrientationEventListener;
import android.widget.ImageButton;
import android.app.Activity;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View;
import android.view.View$OnClickListener;

class CoppolaControlsDecorator$2 implements View$OnClickListener
{
    final /* synthetic */ CoppolaControlsDecorator this$0;
    
    CoppolaControlsDecorator$2(final CoppolaControlsDecorator this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (ViewUtils.isVisible(this.this$0.extraSeekbarHandler)) {
            this.this$0.extraSeekbarHandler.setVisibility(4);
        }
        final Activity activity = this.this$0.getController().getActivity();
        int requestedOrientation;
        if (this.this$0.currentOrientation == 1) {
            requestedOrientation = 6;
        }
        else {
            requestedOrientation = 7;
        }
        activity.setRequestedOrientation(requestedOrientation);
        this.this$0.subscribeForSensorUpdates();
    }
}
