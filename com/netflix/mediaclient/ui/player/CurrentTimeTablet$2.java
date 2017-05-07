// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import java.util.concurrent.atomic.AtomicBoolean;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.ui.common.CurrentTimeAnimation;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.Log;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class CurrentTimeTablet$2 implements Animation$AnimationListener
{
    final /* synthetic */ CurrentTimeTablet this$0;
    final /* synthetic */ boolean val$updateCurrentTimeLabel;
    
    CurrentTimeTablet$2(final CurrentTimeTablet this$0, final boolean val$updateCurrentTimeLabel) {
        this.this$0 = this$0;
        this.val$updateCurrentTimeLabel = val$updateCurrentTimeLabel;
    }
    
    public void onAnimationEnd(final Animation animation) {
        synchronized (this.this$0.lock) {
            if (this.this$0.lock.animationCanceled) {
                Log.d("screen", "Animation was canceled, do nothing!");
                this.this$0.context.getState().setTimelineExitOnSnap(false);
            }
            else {
                this.this$0.currentTimeAnimationInProgress = false;
                if (Log.isLoggable("screen", 3)) {
                    Log.d("screen", "Movie current time from stopCurrentTime#onAnimationEnd, tablet: " + this.val$updateCurrentTimeLabel);
                }
                this.this$0.restorePlaybackIfSnapOnExit();
            }
            this.this$0.lock.animationCanceled = false;
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
        synchronized (this.this$0.lock) {
            this.this$0.currentTimeAnimationInProgress = true;
            this.this$0.currentTime.setBackgroundResource(2130837633);
            this.this$0.currentTimeExp.setVisibility(4);
        }
    }
}
