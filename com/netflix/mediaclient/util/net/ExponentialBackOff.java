// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.net;

import com.netflix.mediaclient.Log;

public class ExponentialBackOff implements BackOff
{
    public static final int DEFAULT_INITIAL_INTERVAL_MS = 1000;
    public static final int DEFAULT_MAX_ELAPSED_TIME_MS = 120000;
    public static final int DEFAULT_MAX_INTERVAL_MS = 60000;
    public static final double DEFAULT_MULTIPLIER = 1.5;
    public static final double DEFAULT_RANDOMIZATION_FACTOR = 0.5;
    protected static final String TAG = "nf_backoff";
    private int mCurrentIntervalInMs;
    private int mInitialIntervalInMs;
    private int mMaxElapsedTimeInMs;
    private int mMaxIntervalInMs;
    private double mMultiplier;
    private double mRandomizationFactor;
    private long mStartTimeInNanos;
    
    public ExponentialBackOff() {
        this.mInitialIntervalInMs = 1000;
        this.mRandomizationFactor = 0.5;
        this.mMultiplier = 1.5;
        this.mMaxIntervalInMs = 60000;
        this.mMaxElapsedTimeInMs = 120000;
        this.reset();
    }
    
    public ExponentialBackOff(final int mInitialIntervalInMs, final double mRandomizationFactor, final double mMultiplier, final int mMaxIntervalInMs, final int mMaxElapsedTimeInMs) {
        this.mInitialIntervalInMs = 1000;
        this.mRandomizationFactor = 0.5;
        this.mMultiplier = 1.5;
        this.mMaxIntervalInMs = 60000;
        this.mMaxElapsedTimeInMs = 120000;
        this.mInitialIntervalInMs = mInitialIntervalInMs;
        if (mRandomizationFactor > 1.0 || mRandomizationFactor <= 0.0) {
            this.mRandomizationFactor = 0.0;
        }
        else {
            this.mRandomizationFactor = mRandomizationFactor;
        }
        this.mMultiplier = mMultiplier;
        this.mMaxIntervalInMs = mMaxIntervalInMs;
        this.mMaxElapsedTimeInMs = mMaxElapsedTimeInMs;
        this.reset();
    }
    
    private static int getRandomValueFromInterval(double n, final double n2, final int n3) {
        if (n <= 0.0) {
            return n3;
        }
        n *= n3;
        final double n4 = n3 - n;
        return (int)((n + n3 - n4 + 1.0) * n2 + n4);
    }
    
    private void incrementCurrentInterval() {
        if (this.mCurrentIntervalInMs >= this.mMaxIntervalInMs / this.mMultiplier) {
            this.mCurrentIntervalInMs = this.mMaxIntervalInMs;
        }
        else {
            this.mCurrentIntervalInMs *= (int)this.mMultiplier;
        }
        if (Log.isLoggable()) {
            Log.d("nf_backoff", "new currentInterval [sec]: " + this.mCurrentIntervalInMs / 1000);
        }
    }
    
    @Override
    public boolean canRetry() {
        final boolean b = this.getElapsedTimeInMs() <= this.mMaxElapsedTimeInMs;
        if (Log.isLoggable()) {
            Log.d("nf_backoff", "canRetry: " + b);
        }
        return b;
    }
    
    public int getCurrentIntervalInMs() {
        return this.mCurrentIntervalInMs;
    }
    
    public final long getElapsedTimeInMs() {
        return (System.nanoTime() - this.mStartTimeInNanos) / 1000000L;
    }
    
    public int getInitialIntervalInMs() {
        return this.mInitialIntervalInMs;
    }
    
    public int getMaxElapsedTimeInMs() {
        return this.mMaxElapsedTimeInMs;
    }
    
    public int getMaxIntervalInMs() {
        return this.mMaxIntervalInMs;
    }
    
    public double getMultiplier() {
        return this.mMultiplier;
    }
    
    public double getRandomizationFactor() {
        return this.mRandomizationFactor;
    }
    
    public long getStartTimeInNs() {
        return this.mStartTimeInNanos;
    }
    
    @Override
    public long nextBackOffInMs() {
        if (!this.canRetry()) {
            return -1L;
        }
        final int randomValueFromInterval = getRandomValueFromInterval(this.mRandomizationFactor, Math.random(), this.mCurrentIntervalInMs);
        this.incrementCurrentInterval();
        if (Log.isLoggable()) {
            Log.d("nf_backoff", "randomizedInterval [sec]: " + randomValueFromInterval / 1000);
        }
        return randomValueFromInterval;
    }
    
    @Override
    public void reset() {
        this.mCurrentIntervalInMs = this.mInitialIntervalInMs;
        this.mStartTimeInNanos = System.nanoTime();
    }
}
