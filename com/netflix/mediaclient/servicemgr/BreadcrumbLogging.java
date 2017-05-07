// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface BreadcrumbLogging
{
    String getLookupName();
    
    void leaveBreadcrumb(final String p0);
    
    void setEnable(final boolean p0);
}
