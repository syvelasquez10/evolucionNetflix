// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.animation.Animator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.animation.Animator$AnimatorListener;

final class AnimationUtils$1 implements Animator$AnimatorListener
{
    final /* synthetic */ ViewPropertyAnimator val$alphaAnimator;
    final /* synthetic */ boolean val$shouldAppear;
    final /* synthetic */ View val$view;
    private boolean wasAnimationCancelled;
    
    AnimationUtils$1(final boolean val$shouldAppear, final View val$view, final ViewPropertyAnimator val$alphaAnimator) {
        this.val$shouldAppear = val$shouldAppear;
        this.val$view = val$view;
        this.val$alphaAnimator = val$alphaAnimator;
    }
    
    public void onAnimationCancel(final Animator animator) {
        Log.i("AnimationUtils", "onAnimationCancel");
        this.wasAnimationCancelled = true;
    }
    
    public void onAnimationEnd(final Animator animator) {
        Log.v("AnimationUtils", "onAnimationEnd");
        if (!this.wasAnimationCancelled) {
            ViewUtils.setVisibleOrGone(this.val$view, this.val$shouldAppear);
        }
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
        if ((!this.val$shouldAppear && (this.val$view.getAlpha() < 1.0f || !this.val$view.isShown())) || (this.val$shouldAppear && this.val$view.getAlpha() > 0.0f && this.val$view.isShown())) {
            Log.w("AnimationUtils", "Skipping view appearance animation - view is already in correct state.");
            ViewUtils.setVisibleOrGone(this.val$view, this.val$shouldAppear);
            this.val$alphaAnimator.cancel();
            return;
        }
        ViewUtils.setVisibleOrGone(this.val$view, true);
        this.wasAnimationCancelled = false;
    }
}
