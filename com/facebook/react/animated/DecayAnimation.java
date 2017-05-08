// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

public class DecayAnimation extends AnimationDriver
{
    private final double mDeceleration;
    private double mFromValue;
    private double mLastValue;
    private long mStartFrameTimeMillis;
    private final double mVelocity;
    
    public DecayAnimation(final ReadableMap readableMap) {
        this.mStartFrameTimeMillis = -1L;
        this.mVelocity = readableMap.getDouble("velocity");
        this.mDeceleration = readableMap.getDouble("deceleration");
    }
    
    @Override
    public void runAnimationStep(long n) {
        n /= 1000000L;
        if (this.mStartFrameTimeMillis == -1L) {
            this.mStartFrameTimeMillis = n - 16L;
            this.mFromValue = this.mAnimatedValue.mValue;
            this.mLastValue = this.mAnimatedValue.mValue;
        }
        final double n2 = (1.0 - Math.exp((n - this.mStartFrameTimeMillis) * -(1.0 - this.mDeceleration))) * (this.mVelocity / (1.0 - this.mDeceleration)) + this.mFromValue;
        if (Math.abs(this.mLastValue - n2) < 0.1) {
            this.mHasFinished = true;
            return;
        }
        this.mLastValue = n2;
        this.mAnimatedValue.mValue = n2;
    }
}
