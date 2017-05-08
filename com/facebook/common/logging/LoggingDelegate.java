// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.logging;

public interface LoggingDelegate
{
    void d(final String p0, final String p1);
    
    void e(final String p0, final String p1);
    
    void e(final String p0, final String p1, final Throwable p2);
    
    void i(final String p0, final String p1);
    
    boolean isLoggable(final int p0);
    
    void v(final String p0, final String p1);
    
    void w(final String p0, final String p1);
    
    void w(final String p0, final String p1, final Throwable p2);
    
    void wtf(final String p0, final String p1);
    
    void wtf(final String p0, final String p1, final Throwable p2);
}
