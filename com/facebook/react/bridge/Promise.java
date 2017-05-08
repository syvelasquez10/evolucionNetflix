// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public interface Promise
{
    @Deprecated
    void reject(final String p0);
    
    void reject(final String p0, final String p1);
    
    void reject(final String p0, final String p1, final Throwable p2);
    
    void reject(final String p0, final Throwable p1);
    
    void reject(final Throwable p0);
    
    void resolve(final Object p0);
}
