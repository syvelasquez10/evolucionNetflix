// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import com.facebook.react.bridge.ExecutorToken;

class Timing$Timer
{
    private final int mCallbackID;
    private final ExecutorToken mExecutorToken;
    private final int mInterval;
    private final boolean mRepeat;
    private long mTargetTime;
    
    private Timing$Timer(final ExecutorToken mExecutorToken, final int mCallbackID, final long mTargetTime, final int mInterval, final boolean mRepeat) {
        this.mExecutorToken = mExecutorToken;
        this.mCallbackID = mCallbackID;
        this.mTargetTime = mTargetTime;
        this.mInterval = mInterval;
        this.mRepeat = mRepeat;
    }
}
