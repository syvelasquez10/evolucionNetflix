// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.NativeArray;

class CatalystInstanceImpl$PendingJSCall
{
    public NativeArray mArguments;
    public ExecutorToken mExecutorToken;
    public String mMethod;
    public String mModule;
    
    public CatalystInstanceImpl$PendingJSCall(final ExecutorToken mExecutorToken, final String mModule, final String mMethod, final NativeArray mArguments) {
        this.mExecutorToken = mExecutorToken;
        this.mModule = mModule;
        this.mMethod = mMethod;
        this.mArguments = mArguments;
    }
}
