// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.javabridge.ui.Log;
import com.netflix.mediaclient.service.logging.client.LoggingSession;

public interface EventHandler
{
    void addSession(final LoggingSession p0);
    
    boolean canSendEvent(final String p0, final String p1);
    
    void createUserSession(final Log.ResetSessionIdCallback p0);
    
    void executeInBackground(final Runnable p0);
    
    String getApplicationId();
    
    String getUserSessionId();
    
    void post(final Event p0);
    
    void removeSession(final LoggingSession p0);
}
