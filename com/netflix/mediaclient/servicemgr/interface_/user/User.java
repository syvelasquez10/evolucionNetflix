// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.user;

public interface User
{
    String getEmail();
    
    String getFirstName();
    
    String getLastName();
    
    String getUserToken();
    
    boolean isAgeVerified();
}
