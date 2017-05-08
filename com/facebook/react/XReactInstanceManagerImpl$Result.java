// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.infer.annotation.Assertions;

class XReactInstanceManagerImpl$Result<T>
{
    private final Exception mException;
    private final T mResult;
    
    private XReactInstanceManagerImpl$Result(final Exception mException) {
        this.mException = mException;
        this.mResult = null;
    }
    
    private XReactInstanceManagerImpl$Result(final T mResult) {
        this.mException = null;
        this.mResult = mResult;
    }
    
    public static <T> XReactInstanceManagerImpl$Result<T> of(final Exception ex) {
        return new XReactInstanceManagerImpl$Result<T>(ex);
    }
    
    public static <T, U extends T> XReactInstanceManagerImpl$Result<T> of(final U u) {
        return new XReactInstanceManagerImpl$Result<T>(u);
    }
    
    public T get() {
        if (this.mException != null) {
            throw this.mException;
        }
        Assertions.assertNotNull(this.mResult);
        return this.mResult;
    }
}
