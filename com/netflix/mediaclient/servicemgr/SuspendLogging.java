// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface SuspendLogging
{
    public static final String BACKGROUNDING_ENDED = "com.netflix.mediaclient.intent.action.LOG_SUS_BACKGROUNDING_ENDED";
    public static final String BACKGROUNDING_START = "com.netflix.mediaclient.intent.action.LOG_SUS_BACKGROUNDING_START";
    public static final String BACKGROUND_ENDED = "com.netflix.mediaclient.intent.action.LOG_SUS_BACKGROUND_ENDED";
    public static final String BACKGROUND_START = "com.netflix.mediaclient.intent.action.LOG_SUS_BACKGROUND_START";
    public static final String RESUMING_ENDED = "com.netflix.mediaclient.intent.action.LOG_SUS_RESUMING_ENDED";
    public static final String RESUMING_START = "com.netflix.mediaclient.intent.action.LOG_SUS_RESUMING_START";
    
    void endBackgroundSession();
    
    void endBackgroundingSession();
    
    void endResumingSession();
    
    void startBackgroundSession();
    
    void startBackgroundingSession();
    
    void startResumingSession();
}
