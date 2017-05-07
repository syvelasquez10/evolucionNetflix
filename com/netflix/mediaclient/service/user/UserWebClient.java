// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

public interface UserWebClient
{
    void connectWithFacebook(final String p0, final UserAgentWebCallback p1);
    
    void disconnectFromFacebook(final UserAgentWebCallback p0);
    
    void doDummyWebCall(final UserAgentWebCallback p0);
    
    void fetchAccountData(final UserAgentWebCallback p0);
    
    void fetchProfileData(final String p0, final UserAgentWebCallback p1);
    
    boolean isSynchronous();
    
    void switchWebUserProfile(final String p0, final UserAgentWebCallback p1);
}
