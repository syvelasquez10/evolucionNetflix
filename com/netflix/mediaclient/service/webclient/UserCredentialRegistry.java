// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

public interface UserCredentialRegistry
{
    String getNetflixID();
    
    String getNetflixIdName();
    
    String getSecureNetflixID();
    
    String getSecureNetflixIdName();
    
    boolean updateUserCredentials(final String p0, final String p1);
}
