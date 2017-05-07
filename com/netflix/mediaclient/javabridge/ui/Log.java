// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

public interface Log
{
    public static final String NAME = "log";
    public static final String PATH = "nrdp.log";
    public static final String logBlobMessageReceived = "logBlobMessageReceived";
    public static final String logBlobReady = "logBlobReady";
    
    void addEventListener(final String p0, final EventListener p1);
    
    void flush();
    
    String getAppId();
    
    String getSessionId();
    
    String getXid();
    
    void log(final LogArguments p0);
    
    void removeEventListener(final String p0, final EventListener p1);
    
    void resetSessionID(final ResetSessionIdCallback p0);
    
    public interface ResetSessionIdCallback
    {
        void sessionCreated(final String p0);
    }
}
