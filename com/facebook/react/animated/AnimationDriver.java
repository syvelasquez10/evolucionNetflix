// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;

abstract class AnimationDriver
{
    ValueAnimatedNode mAnimatedValue;
    Callback mEndCallback;
    boolean mHasFinished;
    int mId;
    
    AnimationDriver() {
        this.mHasFinished = false;
    }
    
    public abstract void runAnimationStep(final long p0);
}
