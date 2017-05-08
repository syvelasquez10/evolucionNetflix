// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class FrameBasedAnimationDriver extends AnimationDriver
{
    private final double[] mFrames;
    private double mFromValue;
    private long mStartFrameTimeNanos;
    private final double mToValue;
    
    FrameBasedAnimationDriver(final ReadableMap readableMap) {
        this.mStartFrameTimeNanos = -1L;
        final ReadableArray array = readableMap.getArray("frames");
        final int size = array.size();
        this.mFrames = new double[size];
        for (int i = 0; i < size; ++i) {
            this.mFrames[i] = array.getDouble(i);
        }
        this.mToValue = readableMap.getDouble("toValue");
    }
    
    @Override
    public void runAnimationStep(final long mStartFrameTimeNanos) {
        if (this.mStartFrameTimeNanos < 0L) {
            this.mStartFrameTimeNanos = mStartFrameTimeNanos;
            this.mFromValue = this.mAnimatedValue.mValue;
        }
        final int n = (int)((mStartFrameTimeNanos - this.mStartFrameTimeNanos) / 1000000L / 16.666666666666668);
        if (n < 0) {
            throw new IllegalStateException("Calculated frame index should never be lower than 0");
        }
        if (this.mHasFinished) {
            return;
        }
        double mToValue;
        if (n >= this.mFrames.length - 1) {
            this.mHasFinished = true;
            mToValue = this.mToValue;
        }
        else {
            mToValue = this.mFrames[n] * (this.mToValue - this.mFromValue) + this.mFromValue;
        }
        this.mAnimatedValue.mValue = mToValue;
    }
}
