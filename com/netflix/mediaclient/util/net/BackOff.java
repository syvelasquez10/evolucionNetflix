// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.net;

public interface BackOff
{
    public static final long STOP = -1L;
    public static final BackOff STOP_BACKOFF = new BackOff$2();
    public static final BackOff ZERO_BACKOFF = new BackOff$1();
    
    boolean canRetry();
    
    long nextBackOffInMs();
    
    void reset();
}
