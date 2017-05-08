// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.animation.Animator;
import android.view.View;
import android.animation.Animator$AnimatorListener;

final class BottomPanel$3 implements Animator$AnimatorListener
{
    final /* synthetic */ CurrentTime val$currentTime;
    final /* synthetic */ View val$extraSeekbarHandler;
    final /* synthetic */ Runnable val$onFinishRunnable;
    
    BottomPanel$3(final CurrentTime val$currentTime, final View val$extraSeekbarHandler, final Runnable val$onFinishRunnable) {
        this.val$currentTime = val$currentTime;
        this.val$extraSeekbarHandler = val$extraSeekbarHandler;
        this.val$onFinishRunnable = val$onFinishRunnable;
    }
    
    public void onAnimationCancel(final Animator animator) {
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (this.val$currentTime != null) {
            this.val$currentTime.show();
        }
        this.val$extraSeekbarHandler.post(this.val$onFinishRunnable);
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
        if (this.val$currentTime != null) {
            this.val$currentTime.hide();
        }
    }
}
