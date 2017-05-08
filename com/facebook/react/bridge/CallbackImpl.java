// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public final class CallbackImpl implements Callback
{
    private final int mCallbackId;
    private final CatalystInstance mCatalystInstance;
    private final ExecutorToken mExecutorToken;
    private boolean mInvoked;
    
    public CallbackImpl(final CatalystInstance mCatalystInstance, final ExecutorToken mExecutorToken, final int mCallbackId) {
        this.mCatalystInstance = mCatalystInstance;
        this.mExecutorToken = mExecutorToken;
        this.mCallbackId = mCallbackId;
        this.mInvoked = false;
    }
    
    @Override
    public void invoke(final Object... array) {
        if (this.mInvoked) {
            throw new RuntimeException("Illegal callback invocation from native module. This callback type only permits a single invocation from native code.");
        }
        this.mCatalystInstance.invokeCallback(this.mExecutorToken, this.mCallbackId, Arguments.fromJavaArgs(array));
        this.mInvoked = true;
    }
}
