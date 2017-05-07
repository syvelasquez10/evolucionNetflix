// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;

class CurrentTimeTablet$2 implements Animator$AnimatorListener
{
    final /* synthetic */ CurrentTimeTablet this$0;
    final /* synthetic */ boolean val$updateCurrentTimeLabel;
    
    CurrentTimeTablet$2(final CurrentTimeTablet this$0, final boolean val$updateCurrentTimeLabel) {
        this.this$0 = this$0;
        this.val$updateCurrentTimeLabel = val$updateCurrentTimeLabel;
    }
    
    public void onAnimationCancel(final Animator animator) {
        Log.d("CurrentTimeTablet", "startStopAnimation was canceled");
        this.this$0.playerFragment.getState().setTimelineExitOnSnap(false);
    }
    
    public void onAnimationEnd(final Animator animator) {
        Log.i("CurrentTimeTablet", "startStopAnimation end");
        if (Log.isLoggable()) {
            Log.d("CurrentTimeTablet", "Movie current time from stopCurrentTime#onAnimationEnd, tablet: " + this.val$updateCurrentTimeLabel);
        }
        this.this$0.restorePlaybackIfSnapOnExit();
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
    }
}
