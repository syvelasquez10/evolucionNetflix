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
import android.app.Activity;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.Context;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.database.ContentObserver;
import com.netflix.mediaclient.android.widget.TimelineSeekBar;
import android.view.OrientationEventListener;
import android.widget.ImageButton;
import android.media.AudioManager;
import android.view.View;
import android.view.View$OnClickListener;

class CoppolaControlsDecorator$3 implements View$OnClickListener
{
    final /* synthetic */ CoppolaControlsDecorator this$0;
    
    CoppolaControlsDecorator$3(final CoppolaControlsDecorator this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final AudioManager audioManager = (AudioManager)this.this$0.getController().getActivity().getSystemService("audio");
        final boolean access$300 = this.this$0.currentVolumeIsMuted();
        this.this$0.updateMuteDrawable(!access$300);
        if (access$300) {
            audioManager.setStreamVolume(3, this.this$0.userVolumeValue, 0);
            return;
        }
        audioManager.setStreamVolume(3, 0, 0);
    }
}
