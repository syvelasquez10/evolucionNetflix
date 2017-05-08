// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public class DefaultNativeModuleCallExceptionHandler implements NativeModuleCallExceptionHandler
{
    @Override
    public void handleException(final Exception ex) {
        if (ex instanceof RuntimeException) {
            throw (RuntimeException)ex;
        }
        throw new RuntimeException(ex);
    }
}
