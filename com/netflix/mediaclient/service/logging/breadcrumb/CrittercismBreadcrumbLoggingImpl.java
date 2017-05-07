// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.breadcrumb;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.BreadcrumbLogging;

public final class CrittercismBreadcrumbLoggingImpl implements BreadcrumbLogging
{
    private static final String NAME = "crittercism";
    
    @Override
    public String getLookupName() {
        return "crittercism";
    }
    
    @Override
    public void leaveBreadcrumb(final String s) {
        ErrorLoggingManager.leaveBreadcrumb(s);
    }
    
    @Override
    public void setEnable(final boolean b) {
    }
}
