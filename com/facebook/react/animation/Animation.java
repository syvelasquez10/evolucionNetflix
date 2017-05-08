// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animation;

import android.view.View;

public abstract class Animation
{
    private View mAnimatedView;
    private final int mAnimationID;
    private AnimationListener mAnimationListener;
    private volatile boolean mCancelled;
    private volatile boolean mIsFinished;
    private final AnimationPropertyUpdater mPropertyUpdater;
    
    public final void cancel() {
        if (!this.mIsFinished && !this.mCancelled) {
            this.mCancelled = true;
            if (this.mAnimationListener != null) {
                this.mAnimationListener.onCancel();
            }
        }
    }
    
    public int getAnimationID() {
        return this.mAnimationID;
    }
    
    public abstract void run();
    
    public void setAnimationListener(final AnimationListener mAnimationListener) {
        this.mAnimationListener = mAnimationListener;
    }
    
    public final void start(final View mAnimatedView) {
        this.mAnimatedView = mAnimatedView;
        this.mPropertyUpdater.prepare(mAnimatedView);
        this.run();
    }
}
