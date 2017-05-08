// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

class SpringAnimation extends AnimationDriver
{
    private final SpringAnimation$PhysicsState mCurrentState;
    private double mDisplacementFromRestThreshold;
    private double mEndValue;
    private long mLastTime;
    private boolean mOvershootClampingEnabled;
    private final SpringAnimation$PhysicsState mPreviousState;
    private double mRestSpeedThreshold;
    private double mSpringFriction;
    private boolean mSpringStarted;
    private double mSpringTension;
    private double mStartValue;
    private final SpringAnimation$PhysicsState mTempState;
    private double mTimeAccumulator;
    
    SpringAnimation(final ReadableMap readableMap) {
        this.mCurrentState = new SpringAnimation$PhysicsState(null);
        this.mPreviousState = new SpringAnimation$PhysicsState(null);
        this.mTempState = new SpringAnimation$PhysicsState(null);
        this.mTimeAccumulator = 0.0;
        this.mSpringFriction = readableMap.getDouble("friction");
        this.mSpringTension = readableMap.getDouble("tension");
        this.mCurrentState.velocity = readableMap.getDouble("initialVelocity");
        this.mEndValue = readableMap.getDouble("toValue");
        this.mRestSpeedThreshold = readableMap.getDouble("restSpeedThreshold");
        this.mDisplacementFromRestThreshold = readableMap.getDouble("restDisplacementThreshold");
        this.mOvershootClampingEnabled = readableMap.getBoolean("overshootClamping");
    }
    
    private void advance(double velocity) {
        if (!this.isAtRest()) {
            double n = velocity;
            if (velocity > 0.064) {
                n = 0.064;
            }
            this.mTimeAccumulator += n;
            final double mSpringTension = this.mSpringTension;
            final double mSpringFriction = this.mSpringFriction;
            double position = this.mCurrentState.position;
            velocity = this.mCurrentState.velocity;
            double position2 = this.mTempState.position;
            double velocity2 = this.mTempState.velocity;
            while (this.mTimeAccumulator >= 0.001) {
                this.mTimeAccumulator -= 0.001;
                if (this.mTimeAccumulator < 0.001) {
                    this.mPreviousState.position = position;
                    this.mPreviousState.velocity = velocity;
                }
                final double n2 = (this.mEndValue - position2) * mSpringTension - mSpringFriction * velocity;
                final double n3 = velocity + 0.001 * n2 * 0.5;
                final double n4 = (this.mEndValue - (0.001 * velocity * 0.5 + position)) * mSpringTension - mSpringFriction * n3;
                final double n5 = velocity + 0.001 * n4 * 0.5;
                final double n6 = (this.mEndValue - (0.001 * n3 * 0.5 + position)) * mSpringTension - mSpringFriction * n5;
                position2 = position + 0.001 * n5;
                velocity2 = 0.001 * n6 + velocity;
                final double mEndValue = this.mEndValue;
                position += ((n3 + n5) * 2.0 + velocity + velocity2) * 0.16666666666666666 * 0.001;
                velocity += (n2 + (n4 + n6) * 2.0 + ((mEndValue - position2) * mSpringTension - mSpringFriction * velocity2)) * 0.16666666666666666 * 0.001;
            }
            this.mTempState.position = position2;
            this.mTempState.velocity = velocity2;
            this.mCurrentState.position = position;
            this.mCurrentState.velocity = velocity;
            if (this.mTimeAccumulator > 0.0) {
                this.interpolate(this.mTimeAccumulator / 0.001);
            }
            if (this.isAtRest() || (this.mOvershootClampingEnabled && this.isOvershooting())) {
                if (mSpringTension > 0.0) {
                    this.mStartValue = this.mEndValue;
                    this.mCurrentState.position = this.mEndValue;
                }
                else {
                    this.mEndValue = this.mCurrentState.position;
                    this.mStartValue = this.mEndValue;
                }
                this.mCurrentState.velocity = 0.0;
            }
        }
    }
    
    private double getDisplacementDistanceForState(final SpringAnimation$PhysicsState springAnimation$PhysicsState) {
        return Math.abs(this.mEndValue - springAnimation$PhysicsState.position);
    }
    
    private void interpolate(final double n) {
        this.mCurrentState.position = this.mCurrentState.position * n + this.mPreviousState.position * (1.0 - n);
        this.mCurrentState.velocity = this.mCurrentState.velocity * n + this.mPreviousState.velocity * (1.0 - n);
    }
    
    private boolean isAtRest() {
        return Math.abs(this.mCurrentState.velocity) <= this.mRestSpeedThreshold && (this.getDisplacementDistanceForState(this.mCurrentState) <= this.mDisplacementFromRestThreshold || this.mSpringTension == 0.0);
    }
    
    private boolean isOvershooting() {
        return this.mSpringTension > 0.0 && ((this.mStartValue < this.mEndValue && this.mCurrentState.position > this.mEndValue) || (this.mStartValue > this.mEndValue && this.mCurrentState.position < this.mEndValue));
    }
    
    @Override
    public void runAnimationStep(long n) {
        n /= 1000000L;
        if (!this.mSpringStarted) {
            final SpringAnimation$PhysicsState mCurrentState = this.mCurrentState;
            final double mValue = this.mAnimatedValue.mValue;
            mCurrentState.position = mValue;
            this.mStartValue = mValue;
            this.mLastTime = n;
            this.mSpringStarted = true;
        }
        this.advance((n - this.mLastTime) / 1000.0);
        this.mLastTime = n;
        this.mAnimatedValue.mValue = this.mCurrentState.position;
        this.mHasFinished = this.isAtRest();
    }
}
