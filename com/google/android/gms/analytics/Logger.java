// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

public interface Logger
{
    void error(final Exception p0);
    
    void error(final String p0);
    
    int getLogLevel();
    
    void info(final String p0);
    
    void setLogLevel(final int p0);
    
    void verbose(final String p0);
    
    void warn(final String p0);
}
