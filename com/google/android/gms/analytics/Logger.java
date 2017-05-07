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
    
    public static class LogLevel
    {
        public static final int ERROR = 3;
        public static final int INFO = 1;
        public static final int VERBOSE = 0;
        public static final int WARNING = 2;
    }
}
