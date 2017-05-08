// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public final class CallbackImpl implements Callback
{
    private final int mCallbackId;
    private final CatalystInstance mCatalystInstance;
    private final ExecutorToken mExecutorToken;
    
    public CallbackImpl(final CatalystInstance mCatalystInstance, final ExecutorToken mExecutorToken, final int mCallbackId) {
        this.mCatalystInstance = mCatalystInstance;
        this.mExecutorToken = mExecutorToken;
        this.mCallbackId = mCallbackId;
    }
    
    @Override
    public void invoke(final Object... array) {
        this.mCatalystInstance.invokeCallback(this.mExecutorToken, this.mCallbackId, Arguments.fromJavaArgs(array));
    }
}
