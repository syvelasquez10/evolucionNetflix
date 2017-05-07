// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public abstract class BaseApmSession extends BaseLoggingSession
{
    public static final String CATEGORY = "uiQOE";
    
    @Override
    public String getCategory() {
        return "uiQOE";
    }
}
