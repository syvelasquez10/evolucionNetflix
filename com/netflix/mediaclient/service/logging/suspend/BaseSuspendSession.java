// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend;

import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public abstract class BaseSuspendSession extends BaseLoggingSession
{
    public static final String CATEGORY = "tvuiSuspend";
    
    @Override
    public String getCategory() {
        return "tvuiSuspend";
    }
}
