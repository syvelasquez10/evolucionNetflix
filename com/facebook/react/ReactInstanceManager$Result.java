// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.infer.annotation.Assertions;

class ReactInstanceManager$Result<T>
{
    private final Exception mException;
    private final T mResult;
    
    private ReactInstanceManager$Result(final Exception mException) {
        this.mException = mException;
        this.mResult = null;
    }
    
    private ReactInstanceManager$Result(final T mResult) {
        this.mException = null;
        this.mResult = mResult;
    }
    
    public static <T> ReactInstanceManager$Result<T> of(final Exception ex) {
        return new ReactInstanceManager$Result<T>(ex);
    }
    
    public static <T, U extends T> ReactInstanceManager$Result<T> of(final U u) {
        return new ReactInstanceManager$Result<T>(u);
    }
    
    public T get() {
        if (this.mException != null) {
            throw this.mException;
        }
        Assertions.assertNotNull(this.mResult);
        return this.mResult;
    }
}
