// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface IExpiringContentWarning
{
    long getExpirationTime();
    
    String getLocalizedDate();
    
    ExpiringContentType getWarningType();
    
    boolean willExpire();
}
