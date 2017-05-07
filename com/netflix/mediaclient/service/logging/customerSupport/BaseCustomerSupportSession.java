// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerSupport;

import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public abstract class BaseCustomerSupportSession extends BaseLoggingSession
{
    public static final String CATEGORY = "customerSupport";
    
    @Override
    public String getCategory() {
        return "customerSupport";
    }
}
