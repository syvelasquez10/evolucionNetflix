// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

public enum TargetStateManager$TargetState
{
    StateBadPair(TargetStateManager$StateId.StateBadPair, "badpair", 0, 0, 1000), 
    StateHasError(TargetStateManager$StateId.StateHasError, "haserror", 0, 0, 1000), 
    StateHasPair(TargetStateManager$StateId.StateHasPair, "haspair", 4, 8000, 1000), 
    StateLaunched(TargetStateManager$StateId.StateLaunched, "launched", 0, 0, 1000), 
    StateNeedHandShake(TargetStateManager$StateId.StateNeedHandShake, "needhandshake", 4, 8000, 1000), 
    StateNeedLaunched(TargetStateManager$StateId.StateNeedLaunched, "needlaunch", 1, 64000, 7000), 
    StateNeedRegPair(TargetStateManager$StateId.StateNeedRegPair, "needregpair", 3, 32000, 4000), 
    StateNoPair(TargetStateManager$StateId.StateNoPair, "nopair", 3, 24000, 3000), 
    StateNoPairNeedRegPair(TargetStateManager$StateId.StateNoPairNeedRegPair, "nopairneedregpair", 0, 0, 1000), 
    StateNotLaunched(TargetStateManager$StateId.StateNotLaunched, "notlaunched", 0, 0, 1000), 
    StateRetryExhausted(TargetStateManager$StateId.StateRetryExhausted, "retryexhausted", 0, 0, 1000), 
    StateSendingMessage(TargetStateManager$StateId.StateSendingMessage, "sendingmessage", 4, 8000, 1000), 
    StateSessionEnd(TargetStateManager$StateId.StateSessionEnd, "sessionend", 0, 8000, 1000), 
    StateSessionReady(TargetStateManager$StateId.StateSessionReady, "sessionready", 0, 0, 1000), 
    StateTimeout(TargetStateManager$StateId.StateTimeout, "timeout", 0, 0, 1000);
    
    private int mBaseRetryIntreval;
    private TargetStateManager$StateId mId;
    private String mName;
    private int mRetry;
    private int mTimeOut;
    
    private TargetStateManager$TargetState(final TargetStateManager$StateId mId, final String mName, final int mRetry, final int mTimeOut, final int mBaseRetryIntreval) {
        this.mRetry = 0;
        this.mBaseRetryIntreval = 0;
        this.mName = mName;
        this.mRetry = mRetry;
        this.mId = mId;
        this.mTimeOut = mTimeOut;
        this.mBaseRetryIntreval = mBaseRetryIntreval;
    }
    
    public TargetStateManager$StateId getId() {
        return this.mId;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public int getRetry() {
        return this.mRetry;
    }
    
    public int getRetryInterval() {
        return this.mBaseRetryIntreval;
    }
    
    public int getTimeOut() {
        return this.mTimeOut;
    }
}
