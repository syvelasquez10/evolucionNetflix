// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.components;

public class RetryManager
{
    private int mMaxTapToRetryAttempts;
    private int mTapToRetryAttempts;
    private boolean mTapToRetryEnabled;
    
    public RetryManager() {
        this.init();
    }
    
    public void init() {
        this.mTapToRetryEnabled = false;
        this.mMaxTapToRetryAttempts = 4;
        this.reset();
    }
    
    public void notifyTapToRetry() {
        ++this.mTapToRetryAttempts;
    }
    
    public void reset() {
        this.mTapToRetryAttempts = 0;
    }
    
    public void setTapToRetryEnabled(final boolean mTapToRetryEnabled) {
        this.mTapToRetryEnabled = mTapToRetryEnabled;
    }
    
    public boolean shouldRetryOnTap() {
        return this.mTapToRetryEnabled && this.mTapToRetryAttempts < this.mMaxTapToRetryAttempts;
    }
}
