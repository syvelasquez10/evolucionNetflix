// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social;

import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public abstract class BaseSocialSession extends BaseLoggingSession
{
    protected static final String CATEGORY = "social";
    
    @Override
    public String getCategory() {
        return "social";
    }
}
