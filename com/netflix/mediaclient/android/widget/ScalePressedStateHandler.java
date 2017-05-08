// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class ScalePressedStateHandler extends PressedStateHandler
{
    private static final long ANIM_DURATION_COMPLETE_MS = 75L;
    private static final long ANIM_DURATION_START_MS = 150L;
    private static final Interpolator PRESSED_INTERPOLATOR;
    private static final float PRESSED_SCALE_FACTOR = 0.93f;
    private static final float RESET_SCALE_FACTOR = 1.0f;
    private static final Interpolator UNPRESSED_INTERPOLATOR;
    private boolean shouldPerformCompleteAnimation;
    private float viewScaleX;
    private float viewScaleY;
    
    static {
        PRESSED_INTERPOLATOR = (Interpolator)new DecelerateInterpolator(1.5f);
        UNPRESSED_INTERPOLATOR = (Interpolator)new AccelerateInterpolator(1.5f);
    }
    
    public ScalePressedStateHandler(final View view) {
        super(view);
        this.viewScaleX = 1.0f;
        this.viewScaleY = 1.0f;
    }
    
    private void performResetAnimation(final View view) {
        this.log("Performing reset animation");
        view.animate().scaleX(this.viewScaleX * 1.0f).scaleY(this.viewScaleY * 1.0f).setDuration(75L).setInterpolator((TimeInterpolator)ScalePressedStateHandler.UNPRESSED_INTERPOLATOR).setListener((Animator$AnimatorListener)new ScalePressedStateHandler$EndAnimationCompleteListener(this, null)).start();
    }
    
    private void resetView(final View view) {
        if (this.shouldPerformCompleteAnimation) {
            this.performResetAnimation(view);
            return;
        }
        this.log("Skipping reset view for now - setting flag to complete later");
        this.shouldPerformCompleteAnimation = true;
    }
    
    @Override
    protected void handlePressCancelled(final View view) {
        this.resetView(view);
    }
    
    @Override
    protected void handlePressComplete(final View view) {
        this.resetView(view);
    }
    
    @Override
    protected void handlePressStarted(final View view) {
        this.shouldPerformCompleteAnimation = false;
        this.viewScaleX = view.getScaleX();
        this.viewScaleY = view.getScaleY();
        view.animate().scaleX(this.viewScaleX * 0.93f).scaleY(this.viewScaleY * 0.93f).setDuration(150L).setInterpolator((TimeInterpolator)ScalePressedStateHandler.PRESSED_INTERPOLATOR).setListener((Animator$AnimatorListener)new ScalePressedStateHandler$StartAnimationCompleteListener(this, view)).start();
    }
}
