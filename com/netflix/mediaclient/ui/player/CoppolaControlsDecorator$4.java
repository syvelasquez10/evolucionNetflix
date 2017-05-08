// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.provider.Settings$System;
import android.os.Handler;
import android.view.View$OnClickListener;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.Activity;
import android.media.AudioManager;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.util.CoppolaUtils;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.database.ContentObserver;
import com.netflix.mediaclient.android.widget.TimelineSeekBar;
import android.widget.ImageButton;
import android.view.View;
import android.content.Context;
import android.view.OrientationEventListener;

class CoppolaControlsDecorator$4 extends OrientationEventListener
{
    final /* synthetic */ CoppolaControlsDecorator this$0;
    
    CoppolaControlsDecorator$4(final CoppolaControlsDecorator this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
    }
    
    private boolean epsilonCheck(final int n, final int n2, final int n3) {
        return n > n2 - n3 && n < n2 + n3;
    }
    
    public void onOrientationChanged(final int n) {
        final boolean b = true;
        if (this.this$0.getController().isActivityValid()) {
            int n2 = 0;
            Label_0096: {
                if (this.this$0.currentOrientation == 1) {
                    n2 = (b ? 1 : 0);
                    if (this.epsilonCheck(n, 10, 10)) {
                        break Label_0096;
                    }
                    n2 = (b ? 1 : 0);
                    if (this.epsilonCheck(n, 350, 10)) {
                        break Label_0096;
                    }
                }
                if (this.this$0.currentOrientation == 2) {
                    n2 = (b ? 1 : 0);
                    if (this.epsilonCheck(n, 90, 10)) {
                        break Label_0096;
                    }
                    if (this.epsilonCheck(n, 270, 10)) {
                        n2 = (b ? 1 : 0);
                        break Label_0096;
                    }
                }
                n2 = 0;
            }
            if (n2 != 0) {
                this.this$0.getController().getActivity().setRequestedOrientation(4);
                this.this$0.orientationEventListener.disable();
            }
        }
    }
}
