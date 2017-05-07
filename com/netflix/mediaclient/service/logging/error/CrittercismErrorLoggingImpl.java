// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import com.netflix.mediaclient.servicemgr.ErrorLogging;

public final class CrittercismErrorLoggingImpl implements ErrorLogging
{
    private static final String NAME = "crittercism";
    
    @Override
    public String getLookupName() {
        return "crittercism";
    }
    
    @Override
    public void logHandledException(final Exception ex) {
        ErrorLoggingManager.logHandledException(ex);
    }
    
    @Override
    public void logHandledException(final String s) {
        ErrorLoggingManager.logHandledException(s);
    }
}
