// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.breadcrumb;

import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.servicemgr.BreadcrumbLogging;

public final class CrittercismBreadcrumbLoggingImpl implements BreadcrumbLogging
{
    @Override
    public void leaveBreadcrumb(final String s) {
        Crittercism.leaveBreadcrumb(s);
    }
}
