// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface IServiceManagerAccess
{
    int getClientId();
    
    int getRequestId(final ManagerCallback p0);
    
    INetflixService getService();
    
    int getWrappedRequestId(final ManagerCallback p0, final String p1);
}
