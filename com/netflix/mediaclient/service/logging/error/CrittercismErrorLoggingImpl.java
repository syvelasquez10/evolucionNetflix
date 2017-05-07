// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import com.netflix.mediaclient.servicemgr.ErrorLogging;

public final class CrittercismErrorLoggingImpl implements ErrorLogging
{
    private LoggingAgent mOwner;
    
    public CrittercismErrorLoggingImpl(final LoggingAgent mOwner) {
        this.mOwner = mOwner;
    }
    
    @Override
    public void logHandledException(final Exception ex) {
        if (this.mOwner.isCrittercismReady()) {
            Crittercism.logHandledException(ex);
        }
    }
    
    @Override
    public void logHandledException(final String s) {
        if (this.mOwner.isCrittercismReady()) {
            this.logHandledException(new Exception(s));
        }
    }
}
