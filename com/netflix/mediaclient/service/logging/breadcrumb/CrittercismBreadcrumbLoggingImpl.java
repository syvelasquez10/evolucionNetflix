// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.breadcrumb;

import com.netflix.mediaclient.Log;
import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.servicemgr.BreadcrumbLogging;

public final class CrittercismBreadcrumbLoggingImpl implements BreadcrumbLogging
{
    private static final String NAME = "crittercism";
    private static final String TAG = "nf_log";
    private boolean mEnabled;
    
    public CrittercismBreadcrumbLoggingImpl() {
        this.mEnabled = true;
    }
    
    @Override
    public String getLookupName() {
        return "crittercism";
    }
    
    @Override
    public void leaveBreadcrumb(final String s) {
        if (this.mEnabled) {
            Crittercism.leaveBreadcrumb(s);
            return;
        }
        Log.d("nf_log", "Breadcrumb logging disabled");
    }
    
    @Override
    public void setEnable(final boolean mEnabled) {
        this.mEnabled = mEnabled;
    }
}
