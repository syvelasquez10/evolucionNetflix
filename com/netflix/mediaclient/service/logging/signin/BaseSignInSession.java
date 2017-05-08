// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.signin;

import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

abstract class BaseSignInSession extends BaseLoggingSession
{
    public static final String CATEGORY = "signIn";
    
    @Override
    public String getCategory() {
        return "signIn";
    }
}
