// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.breadcrumb;

import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import com.netflix.mediaclient.servicemgr.BreadcrumbLogging;

public final class CrittercismBreadcrumbLoggingImpl implements BreadcrumbLogging
{
    private LoggingAgent mOwner;
    
    public CrittercismBreadcrumbLoggingImpl(final LoggingAgent mOwner) {
        this.mOwner = mOwner;
    }
    
    @Override
    public void leaveBreadcrumb(final String s) {
        if (this.mOwner.isCrittercismReady()) {
            Crittercism.leaveBreadcrumb(s);
        }
    }
}
