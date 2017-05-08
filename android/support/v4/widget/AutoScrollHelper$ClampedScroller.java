// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.animation.AnimationUtils;

class AutoScrollHelper$ClampedScroller
{
    private long mDeltaTime;
    private int mDeltaX;
    private int mDeltaY;
    private int mEffectiveRampDown;
    private int mRampDownDuration;
    private int mRampUpDuration;
    private long mStartTime;
    private long mStopTime;
    private float mStopValue;
    private float mTargetVelocityX;
    private float mTargetVelocityY;
    
    AutoScrollHelper$ClampedScroller() {
        this.mStartTime = Long.MIN_VALUE;
        this.mStopTime = -1L;
        this.mDeltaTime = 0L;
        this.mDeltaX = 0;
        this.mDeltaY = 0;
    }
    
    private float getValueAt(final long n) {
        if (n < this.mStartTime) {
            return 0.0f;
        }
        if (this.mStopTime < 0L || n < this.mStopTime) {
            return AutoScrollHelper.constrain((n - this.mStartTime) / this.mRampUpDuration, 0.0f, 1.0f) * 0.5f;
        }
        return AutoScrollHelper.constrain((n - this.mStopTime) / this.mEffectiveRampDown, 0.0f, 1.0f) * this.mStopValue + (1.0f - this.mStopValue);
    }
    
    private float interpolateValue(final float n) {
        return -4.0f * n * n + 4.0f * n;
    }
    
    public void computeScrollDelta() {
        if (this.mDeltaTime == 0L) {
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }
        final long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        final float interpolateValue = this.interpolateValue(this.getValueAt(currentAnimationTimeMillis));
        final long n = currentAnimationTimeMillis - this.mDeltaTime;
        this.mDeltaTime = currentAnimationTimeMillis;
        this.mDeltaX = (int)(n * interpolateValue * this.mTargetVelocityX);
        this.mDeltaY = (int)(n * interpolateValue * this.mTargetVelocityY);
    }
    
    public int getDeltaX() {
        return this.mDeltaX;
    }
    
    public int getDeltaY() {
        return this.mDeltaY;
    }
    
    public int getHorizontalDirection() {
        return (int)(this.mTargetVelocityX / Math.abs(this.mTargetVelocityX));
    }
    
    public int getVerticalDirection() {
        return (int)(this.mTargetVelocityY / Math.abs(this.mTargetVelocityY));
    }
    
    public boolean isFinished() {
        return this.mStopTime > 0L && AnimationUtils.currentAnimationTimeMillis() > this.mStopTime + this.mEffectiveRampDown;
    }
    
    public void requestStop() {
        final long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this.mEffectiveRampDown = AutoScrollHelper.constrain((int)(currentAnimationTimeMillis - this.mStartTime), 0, this.mRampDownDuration);
        this.mStopValue = this.getValueAt(currentAnimationTimeMillis);
        this.mStopTime = currentAnimationTimeMillis;
    }
    
    public void setRampDownDuration(final int mRampDownDuration) {
        this.mRampDownDuration = mRampDownDuration;
    }
    
    public void setRampUpDuration(final int mRampUpDuration) {
        this.mRampUpDuration = mRampUpDuration;
    }
    
    public void setTargetVelocity(final float mTargetVelocityX, final float mTargetVelocityY) {
        this.mTargetVelocityX = mTargetVelocityX;
        this.mTargetVelocityY = mTargetVelocityY;
    }
    
    public void start() {
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mStopTime = -1L;
        this.mDeltaTime = this.mStartTime;
        this.mStopValue = 0.5f;
        this.mDeltaX = 0;
        this.mDeltaY = 0;
    }
}
