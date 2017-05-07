// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.animation.TimeInterpolator;
import android.animation.Animator$AnimatorListener;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class ScalePressedStateHandler extends PressedStateHandler
{
    private static final long ANIM_DURATION_COMPLETE_MS = 100L;
    private static final long ANIM_DURATION_START_MS = 200L;
    private static final AccelerateDecelerateInterpolator INTERPOLATOR;
    private static final float PRESSED_SCALE_FACTOR = 0.93f;
    private static final float RESET_SCALE_FACTOR = 1.0f;
    private boolean shouldPerformCompleteAnimation;
    
    static {
        INTERPOLATOR = new AccelerateDecelerateInterpolator();
    }
    
    public ScalePressedStateHandler(final View view) {
        super(view);
    }
    
    private void performResetAnimation(final View view) {
        Log.v("PressedStateHandler", "Performing reset animation");
        view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100L).setListener((Animator$AnimatorListener)null).start();
    }
    
    private void resetView(final View view) {
        if (this.shouldPerformCompleteAnimation) {
            this.performResetAnimation(view);
            return;
        }
        Log.v("PressedStateHandler", "skipping reset view for now - setting flag");
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
        view.animate().scaleX(0.93f).scaleY(0.93f).setDuration(200L).setInterpolator((TimeInterpolator)ScalePressedStateHandler.INTERPOLATOR).setListener((Animator$AnimatorListener)new ScalePressedStateHandler$StartAnimationCompleteListener(this, view)).start();
    }
}
