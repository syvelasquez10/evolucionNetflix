// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import android.content.Intent;

public interface SuspendLogging
{
    void endAllActiveSessions();
    
    void endBackgroundSession();
    
    void endBackgroundingSession();
    
    void endForegroundSession();
    
    void endResumingSession();
    
    void endSuspendSession();
    
    void endUnfocusedSession();
    
    void startBackgroundSession();
    
    void startBackgroundingSession();
    
    void startForegroundSession(final Intent p0);
    
    void startResumingSession();
    
    void startSuspendSession();
    
    void startUnfocusedSession();
}
